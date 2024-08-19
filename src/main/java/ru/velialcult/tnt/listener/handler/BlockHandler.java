package ru.velialcult.tnt.listener.handler;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import ru.velialcult.anarchyregions.api.region.Region;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import ru.velialcult.tnt.listener.handler.spawner.SpawnerHandler;

public class BlockHandler {
    private final Entity entity;
    private final CustomTnTOptions customTnTOptions;

    public BlockHandler(Entity entity, CustomTnTOptions customTnTOptions) {
        this.entity = entity;
        this.customTnTOptions = customTnTOptions;
    }

    public void handleBlock(Block block, Region region) {
        Material blockType = block.getType();
        Block entityBlock = entity.getLocation().getBlock();
        if (blockType != Material.AIR && blockType != Material.BEDROCK) {
            if (entityBlock.getType() == Material.WATER && customTnTOptions.isBreakBlocksInWater()) {

                if (blockType == Material.OBSIDIAN && !customTnTOptions.isBreakObsidian()) {
                    return;
                }

                if (blockType == Material.SPAWNER) {
                    if (!customTnTOptions.isBreakSpawners() && !customTnTOptions.isBreakSpawnersInRegion()) return;
                    new SpawnerHandler().handleSpawnerBlock(block, region, customTnTOptions);
                }

                dropItem(block, blockType);
            }

            if (blockType == Material.SPAWNER) {
                if (!customTnTOptions.isBreakSpawners() && !customTnTOptions.isBreakSpawnersInRegion()) return;
                new SpawnerHandler().handleSpawnerBlock(block, region, customTnTOptions);
            } else if ((blockType != Material.OBSIDIAN || customTnTOptions.isBreakObsidian()) &&
                    (entityBlock.getType() != Material.WATER || customTnTOptions.isBreakBlocksInWater())) {
                dropItem(block, blockType);
            }
        }
    }

    private void dropItem(Block block, Material blockType) {
        ItemStack itemStack = new ItemStack(blockType);
        if (itemStack.getType() != Material.AIR &&
                itemStack.getType() != Material.WATER &&
                itemStack.getType() != Material.LAVA &&
                itemStack.getType() != Material.CAVE_AIR &&
                itemStack.getType() != Material.VOID_AIR) {
            block.setType(Material.AIR);
        }
    }
}
