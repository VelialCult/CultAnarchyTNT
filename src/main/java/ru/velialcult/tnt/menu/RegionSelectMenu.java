package ru.velialcult.tnt.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import ru.velialcult.anarchyregions.CultAnarchyRegions;
import ru.velialcult.anarchyregions.repository.RegionTypeRepository;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import ru.velialcult.tnt.menu.buttons.BackButton;
import ru.velialcult.tnt.menu.buttons.ForwardButton;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.impl.AutoUpdateItem;
import xyz.xenondevs.invui.item.impl.SuppliedItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegionSelectMenu {

    protected static void generateInventory(Player player, CustomTnT customTnT) {
        CustomTnTOptions customTnTOptions = customTnT.getCustomTnTOptions();
        RegionTypeRepository regionTypeRepository = CultAnarchyRegions.getInstance().getRegionManager().getRegionTypeRepository();

        SuppliedItem closeButton = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("&#14d65cЗ&#27da68а&#3bde73к&#4ee27fр&#61e68bы&#75ea97т&#88eda2ь &#9bf1aeм&#aef5baе&#c2f9c5н&#d5fdd1ю")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "&8➩ &aНажмите, чтобы закрыть данное",
                                                                            "     &aменю"
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M1ZmQ0MTJiY2VjNDhmMjk4MDIyOWY0NGFkNzg1ZTlkMjQ3ZjY2YjZhOTFlZWY3YTk4ZDc2NmJkMTFkNGExOSJ9fX0=")
                                                                    .build(), click -> {
            SettingCustomTnTOptionsMenu.generateInventory(player, customTnT);
            return true;
        });

        List<Item> items = regionTypeRepository.getRegionTypes().stream().map(regionType -> {
            return new AutoUpdateItem(20, () ->
                                            s -> {
                if (customTnTOptions.getBreakRegionsList().contains(regionType.getRegionKey())) {
                    return VersionAdapter.getItemBuilder().setType(regionType.getItemStack().getType())
                            .setLore(new ArrayList<>(Arrays.asList(
                                             "",
                                             "§8➩ §aНажмите, чтобы удалить"
                                     ))
                            )
                            .addEnchant(Enchantment.ARROW_DAMAGE, 1)
                            .addFlag(ItemFlag.HIDE_ENCHANTS).build();
                } else {
                    return VersionAdapter.getItemBuilder().setType(regionType.getItemStack().getType())
                            .setLore(new ArrayList<>(Arrays.asList(
                                    "",
                                    "§8➩ §aНажмите, чтобы добавить"
                                     ))
                            ).build();
                }
                }) {
                @Override
                public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
                    if (customTnTOptions.getBreakRegionsList().contains(regionType.getRegionKey())) {
                        customTnTOptions.getBreakRegionsList().remove(regionType.getRegionKey());
                    } else {
                        customTnTOptions.getBreakRegionsList().add(regionType.getRegionKey());
                    }
                }
            };
        }).collect(Collectors.toList());

        Gui gui = PagedGui.items()
                .setStructure(
                        "f f f f f f f f f",
                        "f x x x x x x x f",
                        "f x x x x x x x f",
                        "f x x x x x x x f",
                        "f f f f f f f f f",
                        ". u . b c n . . . ")
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('n', new ForwardButton())
                .addIngredient('b', new BackButton())
                .addIngredient('f', XMaterial.GRAY_STAINED_GLASS_PANE.parseItem())
                .addIngredient('c', closeButton)
                .setContent(items)
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("§8Добавление регионов")
                .setGui(gui)
                .build();
        window.open();
    }
}
