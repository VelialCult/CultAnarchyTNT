package ru.velialcult.tnt.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import ru.velialcult.library.bukkit.inventory.PlayerInputHandler;
import ru.velialcult.library.bukkit.inventory.menu.ChangeListStringMenu;
import ru.velialcult.library.bukkit.utils.InventoryUtil;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.library.java.text.ReplaceData;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTMeta;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.impl.SuppliedItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingCustomTnTMetaMenu {

    protected static void generateInventory(Player player, CustomTnT customTnT) {
        CustomTnTMeta customTnTMeta = customTnT.getMeta();
        SuppliedItem setLore = new SuppliedItem(() ->
                                                        s -> VersionAdapter.getItemBuilder()
                                                                .setType(XMaterial.BOOK.parseMaterial())
                                                                .setDisplayName("&#14d65cУ&#1cd861с&#23d965т&#2bdb6aа&#33dc6fн&#3bde73о&#42df78в&#4ae17dи&#52e281т&#59e486ь &#61e68bо&#69e78fп&#71e994и&#78ea99с&#80ec9eа&#88eda2н&#90efa7и&#97f1acе &#9ff2b0п&#a7f4b5р&#aef5baе&#b6f7beд&#bef8c3м&#c6fac8е&#cdfbccт&#d5fdd1а")
                                                                .setLore(VersionAdapter.TextUtil().setReplaces(new ArrayList<>(Arrays.asList(
                                                                        "",
                                                                        "§7С помощью данной настройки вы можете установить",
                                                                        "§7Описание предмета, которое будет отображатсья у предмета",
                                                                        "",
                                                                        " §8▪ §fТекущий текст:",
                                                                        "",
                                                                        "{text}",
                                                                        "",
                                                                        "§8➩ §aНажмите, чтобы установить"
                                                                )), new ReplaceData("{text}", String.join("\n", InventoryUtil.getColoredLore(customTnTMeta.getLore(), "§8◦ ", "")))))
                                                                .build(), click -> {
            ChangeListStringMenu.generateInventory(player, customTnT.getMeta(), () -> generateInventory(player, customTnT));
            return true;
        });

        SuppliedItem closeButton = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("&#14d65cЗ&#27da68а&#3bde73к&#4ee27fр&#61e68bы&#75ea97т&#88eda2ь &#9bf1aeм&#aef5baе&#c2f9c5н&#d5fdd1ю")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "&8➩ &aНажмите, чтобы закрыть данное",
                                                                            "     &aменю"
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M1ZmQ0MTJiY2VjNDhmMjk4MDIyOWY0NGFkNzg1ZTlkMjQ3ZjY2YjZhOTFlZWY3YTk4ZDc2NmJkMTFkNGExOSJ9fX0=")
                                                                    .build(), click -> {
            SettingCustomTnTMenu.generateInventory(player, customTnT);
            return true;
        });

        SuppliedItem setDisplayName = new SuppliedItem(() ->
                                                       s -> VersionAdapter.getSkullBuilder()
                                                               .setDisplayName("&#14d65cУ&#1bd760с&#21d964т&#28da68а&#2fdb6cн&#35dd70о&#3cde74в&#43df78и&#49e17cт&#50e280ь &#57e384о&#5de588т&#64e68cо&#6be790б&#71e994р&#78ea99а&#7eec9dж&#85eda1а&#8ceea5е&#92f0a9м&#99f1adо&#a0f2b1е &#a6f4b5н&#adf5b9а&#b4f6bdз&#baf8c1в&#c1f9c5а&#c8fac9н&#cefccdи&#d5fdd1е")
                                                               .setLore(new ArrayList<>(Arrays.asList(
                                                                       "",
                                                                       "§7Используйте данную настройку, чтобы установить",
                                                                       "§7отображаемое название предмета",
                                                                       "",
                                                                       " §8▪ §fТекущее название: §a" + (customTnTMeta.getDisplayName().isEmpty() ? "§cНет" : customTnTMeta.getDisplayName()),
                                                                       "",
                                                                       "§8➩ §aНажмите, что установить новое название"
                                                               )))
                                                               .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU1ZDEwZjlmMzI0NjU5OTY1OGUwYzZkMDQ0NGU4NzRmZmFjMDE0MTA0NDBjNWNmZWM2ZjE5ZDNhYTg4Zjk0NSJ9fX0=")
                                                               .build(), click -> {
            VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c➤ §fВведите, пожалуйста, введите новое отображамое название в чат");
            player.closeInventory();
            PlayerInputHandler.addPlayer(player, (str) -> {
                customTnTMeta.setDisplayName(str);
                generateInventory(player, customTnT);
            });
            return true;
        });

        SuppliedItem setGlow = InventoryUtil.createBooleanSettingItem(
                "&#43d65fП&#4fd869о&#5adb73д&#66dd7dс&#72e087в&#7de290е&#89e59aт&#94e7a4к&#a0eaaeа &#acecb8д&#b7eec2и&#c3f1ccн&#cff3d6а&#daf6dfм&#e6f8e9и&#f1fbf3т&#fdfdfdа",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7подсветку динамита."),
                customTnTMeta,
                CustomTnTMeta::isGlow,
                CustomTnTMeta::setGlow);

        SuppliedItem setCustomModelData = new SuppliedItem(() ->
                s -> VersionAdapter.getItemBuilder()
                        .setDisplayName("&#43d65fК&#50d96aа&#5edc76с&#6bde81т&#78e18cо&#85e497м&#93e7a3н&#a0eaaeа&#adecb9я &#bbefc5м&#c8f2d0о&#d5f5dbд&#e2f7e6е&#f0faf2л&#fdfdfdь")
                        .setLore(new ArrayList<>(Arrays.asList(
                                "",
                                "§7Используйте данную настройку, чтобы установить",
                                "§7custom model data",
                                "",
                                " §8▪ §fТекущее значение: §a" + customTnTMeta.getCustomModelData(),
                                "",
                                "§8➩ §aНажмите, что установить новое название"
                        )))
                        .setType(XMaterial.ACACIA_BUTTON.parseMaterial())
                        .build(), click -> {
            VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c➤ §fВведите, пожалуйста, введите значение в чат");
            player.closeInventory();
            PlayerInputHandler.addPlayer(player, (str) -> {
                int value;
                try {
                    value = Integer.parseInt(str);
                    customTnTMeta.setCustomModelData(value);
                } catch (NumberFormatException e) {
                    VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c➤ §fВы ввели неверное числовое значение");
                }
                generateInventory(player, customTnT);
            });
            return true;
        });

        Gui gui = Gui.normal()
                .setStructure(
                        "b b b b b b b b b",
                        "b q . w . e . r b",
                        "b . . . . . . . b",
                        "b . . . . . . . b",
                        "b b b b b b b b b",
                        ". . . . c . . . . "
                )
                .addIngredient('c', closeButton)
                .addIngredient('b', XMaterial.GRAY_STAINED_GLASS_PANE.parseItem())
                .addIngredient('q', setLore)
                .addIngredient('w', setDisplayName)
                .addIngredient('i', setLore)
                .addIngredient('e', setGlow)
                .addIngredient('r', setCustomModelData)
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("§8Настройка метаданных динамита")
                .setGui(gui)
                .build();
        window.open();
    }
}
