package ru.velialcult.tnt.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import ru.velialcult.anarchyregions.CultAnarchyRegions;
import ru.velialcult.anarchyregions.manager.RegionManager;
import ru.velialcult.anarchyregions.region.Region;
import ru.velialcult.anarchyregions.region.defend.DefendType;
import ru.velialcult.anarchyregions.type.RegionType;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTMeta;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import ru.velialcult.tnt.custom.manager.CustomTnTManager;
import ru.velialcult.tnt.listener.handler.BlockHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomTnTListener implements Listener {

    private final CustomTnTManager customTnTManager;
    private final RegionManager regionManager;

    private final Map<Block, Location> blockLocationMap = new HashMap<>();

    public CustomTnTListener(CustomTnTManager customTnTManager) {
        this.customTnTManager = customTnTManager;
        this.regionManager = CultAnarchyRegions.getInstance().getRegionManager();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        CustomTnT customTnT = customTnTManager.getCustomTnTByItemStack(itemStack);
        Block block = event.getBlock();
        if (customTnT != null) {
            if (block.getType() == Material.TNT) {
                if (customTnT.getCustomTnTOptions().isAutoIgnite()) {
                    Location location = block.getLocation();
                    block.setType(Material.AIR);
                    Entity entity = location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                    customTnTManager.setMetadata(entity, customTnT);
                    CustomTnTMeta customTnTMeta = customTnT.getMeta();
                    entity.setGravity(!customTnT.getCustomTnTOptions().isGravity());
                    entity.setGlowing(customTnTMeta.isGlow());
                } else {
                    customTnTManager.setMetadata(block, customTnT);
                    this.blockLocationMap.put(block, block.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            Block block = event.getBlock();
            String key = customTnTManager.getMetadata(block);
            CustomTnT customTnT = customTnTManager.getCustomTnTRepository().getByKey(key);
            if (customTnT != null) {
                Location location = block.getLocation();
                location.getWorld().dropItemNaturally(location, customTnT.createItemStack());
                event.getBlock().setType(Material.AIR);
                event.setDropItems(false);
            }
        }
    }

    @EventHandler
    public void onEvent(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof TNTPrimed) {
            Location entityLocation = entity.getLocation();
            Block block = blockLocationMap.entrySet().stream()
                    .filter(map -> map.getValue().distanceSquared(entityLocation) <= 0.5)
                    .findFirst()
                    .map(Map.Entry::getKey)
                    .orElse(null);
            if (block != null) {
                CustomTnT customTnT = customTnTManager.getCustomTnTRepository().getByKey(customTnTManager.getMetadata(block));
                if (customTnT != null) {
                    CustomTnTMeta customTnTMeta = customTnT.getMeta();
                    entity.setGlowing(customTnTMeta.isGlow());
                    entity.setGravity(!customTnT.getCustomTnTOptions().isGravity());
                    this.blockLocationMap.remove(block);
                    customTnTManager.setMetadata(entity, customTnT);
                }
            }
        }
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof TNTPrimed)) {
            return;
        }

        TNTPrimed entity = (TNTPrimed) event.getEntity();
        handleExplosion(entity, event);
    }

    private void handleExplosion(TNTPrimed entity, EntityExplodeEvent event) {
        String tntType = customTnTManager.getMetadata(entity);
        CustomTnT customTnT = customTnTManager.getCustomTnTRepository().getByKey(tntType);
        if (customTnT != null) {
            handleCustomTnT(entity, customTnT, event);
        }
    }

    private void handleCustomTnT(TNTPrimed entity, CustomTnT customTnT, EntityExplodeEvent event) {
        CustomTnTOptions customTnTOptions = customTnT.getCustomTnTOptions();
        int radiusExplosion = customTnTOptions.getRadius_explosion();
        Set<Block> blocks = customTnTManager.calculateBlocks(radiusExplosion, event);
        BlockHandler blockHandler = new BlockHandler(entity, customTnTOptions);

        for (Block block : blocks) {
            handleBlock(block, blockHandler, customTnTOptions);
        }
    }

    private void handleBlock(Block block, BlockHandler blockHandler, CustomTnTOptions customTnTOptions) {
        if (regionManager.regionExistsInLocation(block.getLocation())) {
            Region region = regionManager.getRegionByLocation(block.getLocation());
            if (block.getLocation().equals(region.getCoreLocation())) {
                handleProtectedBlock(block, region, blockHandler, customTnTOptions);
            } else {
                if (region.getRegionData().getRegionDefend().hasDefend(DefendType.TNT)) return;
                blockHandler.handleBlock(block, region);
            }
        } else {
            blockHandler.handleBlock(block, null);
        }
    }

    private void handleProtectedBlock(Block block, Region region, BlockHandler blockHandler, CustomTnTOptions customTnTOptions) {
        RegionType regionType = region.getRegionType();
        if (regionType != null) {
            if (block.getLocation().equals(region.getCoreLocation())) {
                handleRegion(region, customTnTOptions);
            } else {
                blockHandler.handleBlock(block, region);
            }
        }
    }

    private void handleRegion(Region region, CustomTnTOptions customTnTOptions) {
        RegionType regionType = region.getRegionType();
        if (regionType.isUnBreakable() && !customTnTOptions.isBreakUnbreakableRegions()) return;
        if (customTnTOptions.isBreakRegions() && customTnTOptions.getBreakRegionsList().contains(regionType.getRegionKey())) {
            regionManager.delete(region, false, false);
        } else {
            region.decrementDurability();
        }
    }
}
