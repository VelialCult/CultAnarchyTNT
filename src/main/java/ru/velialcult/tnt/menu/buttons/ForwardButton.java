package ru.velialcult.tnt.menu.buttons;

import org.bukkit.inventory.ItemStack;
import ru.velialcult.library.core.VersionAdapter;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

import java.util.ArrayList;
import java.util.List;

public class ForwardButton extends PageItem {

    public ForwardButton() {
        super(true);
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        ItemStack itemStack;
        String url = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRhMzYzYWZkYTE5MjJmYWM4MTA3YjZiMGE1MWViNmY2YzU0MTU3NWUyZGE1NjIwOGY2NWFlY2RlZDI2MzE1YSJ9fX0=";
        String displayName = "§6Вперёд";
        List<String> lore = new ArrayList<>(List.of(
                "§7Нажмите, чтобы перейти на следующую",
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
