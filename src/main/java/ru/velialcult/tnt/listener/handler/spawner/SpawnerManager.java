package ru.velialcult.tnt.listener.handler.spawner;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.velialcult.tnt.CultAnarchyTnT;

public class SpawnerManager {

    private final CultAnarchyTnT cultAnarchyTnT;

    public SpawnerManager(CultAnarchyTnT cultAnarchyTnT) {
        this.cultAnarchyTnT = cultAnarchyTnT;
    }

    public EntityType getSpawnerType(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(cultAnarchyTnT, "spawnerdata");
            if (persistentDataContainer.has(key, PersistentDataType.STRING)) {
                String entityType = persistentDataContainer.get(key, PersistentDataType.STRING);
                return EntityType.valueOf(entityType);
            }
        }

        return null;
    }
}
