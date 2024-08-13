package ru.velialcult.tnt.listener.handler.spawner;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import ru.velialcult.tnt.CultAnarchyTnT;

public class SpawnerListener implements Listener {

    private final SpawnerManager spawnerManager;

    public SpawnerListener(CultAnarchyTnT cultAnarchyTnT) {
        this.spawnerManager = new SpawnerManager(cultAnarchyTnT);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.SPAWNER) {

            ItemStack itemStack = event.getItemInHand();
            EntityType entityType = spawnerManager.getSpawnerType(itemStack);

            if (entityType != null) {
                CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
                creatureSpawner.setSpawnedType(entityType);
                creatureSpawner.update();
            }
        }
    }
}
