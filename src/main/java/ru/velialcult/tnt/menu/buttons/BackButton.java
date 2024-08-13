package ru.velialcult.tnt.menu.buttons;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import ru.velialcult.library.core.VersionAdapter;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

import java.util.ArrayList;
import java.util.List;

public class BackButton extends PageItem {

    public BackButton() {
        super(false);
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        ItemStack itemStack;
        String url = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWM0OTFkYmZhMzcyZGY3OTk5MjYyY2JmYTg5MTY1MThhYjNlMzU5NWJkNmJkZGY5ZjdkMTk1ZGYzZjc4ODViZCJ9fX0=";
        String displayName = "§6Назад";
        List<String> lore = new ArrayList<>(List.of(
                "§7Нажмите, чтобы перейти на предудыющую",
                "§7Страницу меню"
        ));

        itemStack = VersionAdapter.getSkullBuilder()
                .setDisplayName(displayName)
                .setLore(lore)
                .setTexture(url)
                .build();

        return new ItemBuilder(itemStack);
    }

}
