package ru.velialcult.tnt.listener.handler.spawner;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.velialcult.anarchyregions.api.region.Region;
import ru.velialcult.tnt.CultAnarchyTnT;
import ru.velialcult.tnt.custom.CustomTnTOptions;

public class SpawnerHandler {
    public void handleSpawnerBlock(Block block, Region region, CustomTnTOptions customTnTOptions) {
        if (region != null) {
            if (customTnTOptions.isBreakSpawnersInRegion()) {
                dropSpawner(block);
            }
        } else {
            dropSpawner(block);
        }
    }

    private void dropSpawner(Block block) {
        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        EntityType entity = spawner.getSpawnedType();

        ItemStack itemStack = new ItemStack(Material.SPAWNER);
        setSpawnerData(entity, itemStack);
        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
        block.setType(Material.AIR);
    }

    private void setSpawnerData(EntityType entityType, ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey(CultAnarchyTnT.getInstance(), "spawnerdata");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, entityType.name());
            itemStack.setItemMeta(meta);
        }
    }
}
