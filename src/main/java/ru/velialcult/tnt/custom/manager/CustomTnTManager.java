package ru.velialcult.tnt.custom.manager;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.velialcult.tnt.CultAnarchyTnT;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTMeta;
import ru.velialcult.tnt.custom.repository.CustomTnTRepository;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CustomTnTManager {

    private static final String METADATA_KEY = "cult_custom_tnt";

    private final CustomTnTRepository customTnTRepository;
    private final CultAnarchyTnT cultAnarchyTnT;

    public CustomTnTManager(CultAnarchyTnT cultAnarchyTnT) {
        this.cultAnarchyTnT = cultAnarchyTnT;
        this.customTnTRepository = new CustomTnTRepository(cultAnarchyTnT);
    }

    public void initialize() {
        customTnTRepository.loadCustomTnT();
    }

    public CustomTnT getCustomTnTByItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(cultAnarchyTnT, "cult_custom_tnt");
            if (persistentDataContainer.has(key, PersistentDataType.STRING)) {
                String customTnTKey = persistentDataContainer.get(key, PersistentDataType.STRING);
                return customTnTRepository.getByKey(customTnTKey);
            }
        }
        return null;
    }

    public void setMetadata(Metadatable metadatable, CustomTnT customTnT) {
        metadatable.setMetadata(METADATA_KEY, new FixedMetadataValue(cultAnarchyTnT, customTnT.getKey()));
    }

    public String getMetadata(Metadatable metadatable) {
        List<MetadataValue> values = metadatable.getMetadata(METADATA_KEY);
        for (MetadataValue value : values) {
            if (value.getOwningPlugin() == cultAnarchyTnT) {
                return (String) value.value();
            }
        }
        return null;
    }

    public Set<Block> calculateBlocks(int radius, EntityExplodeEvent event) {
        event.blockList().clear();
        Set<Block> uniqueBlocks = Collections.synchronizedSet(new HashSet<>());
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int x = -radius; x <= radius; x++) {
            final int finalX = x;
            executor.submit(() -> {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        double distanceSquared = finalX * finalX + y * y + z * z;
                        if (distanceSquared <= radius * radius) {
                            Block block = event.getLocation().clone().add(finalX, y, z).getBlock();
                            uniqueBlocks.add(block);
                        }
                    }
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uniqueBlocks;
    }

    private double calculateDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int dz = z2 - z1;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public CustomTnTRepository getCustomTnTRepository() {
        return customTnTRepository;
    }
}
