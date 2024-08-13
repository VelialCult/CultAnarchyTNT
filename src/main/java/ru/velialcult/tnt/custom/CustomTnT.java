package ru.velialcult.tnt.custom;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.tnt.CultAnarchyTnT;

public class CustomTnT {

    private String key;
    private final CustomTnTMeta meta;

    // Настройки тнт
    private final CustomTnTOptions customTnTOptions;

    public CustomTnT(String key,
                     CustomTnTMeta meta,
                     CustomTnTOptions customTnTOptions) {
        this.key = key;
        this.meta = meta;
        this.customTnTOptions = customTnTOptions;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CustomTnT() {
        this("", new CustomTnTMeta(), new CustomTnTOptions());
    }

    public ItemStack createItemStack() {
        ItemStack itemStack = VersionAdapter.getItemBuilder()
                .setType(XMaterial.TNT.parseMaterial())
                .setDisplayName(meta.getDisplayName())
                .setLore(meta.getLore())
                .setCustomModelData(meta.getCustomModelData())
                .build();

        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey(CultAnarchyTnT.getInstance(), "cult_custom_tnt");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, this.key);
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    public CustomTnTOptions getCustomTnTOptions() {
        return customTnTOptions;
    }

    public CustomTnTMeta getMeta() {
        return meta;
    }

    public String getKey() {
        return key;
    }
}
