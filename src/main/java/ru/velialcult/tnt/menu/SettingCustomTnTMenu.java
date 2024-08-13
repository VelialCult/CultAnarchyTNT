package ru.velialcult.tnt.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import ru.velialcult.library.bukkit.inventory.PlayerInputHandler;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.tnt.CultAnarchyTnT;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import ru.velialcult.tnt.custom.manager.CustomTnTManager;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.impl.AutoUpdateItem;
import xyz.xenondevs.invui.item.impl.SuppliedItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingCustomTnTMenu {

    protected static void generateInventory(Player player, CustomTnT customTnT) {

        CustomTnTManager customTnTManager = CultAnarchyTnT.getInstance().getCustomTnTManager();
        CustomTnTOptions customTnTOptions = customTnT.getCustomTnTOptions();

        SuppliedItem closeButton = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("&#14d65cЗ&#27da68а&#3bde73к&#4ee27fр&#61e68bы&#75ea97т&#88eda2ь &#9bf1aeм&#aef5baе&#c2f9c5н&#d5fdd1ю")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "&8➩ &aНажмите, чтобы закрыть данное",
                                                                            "     &aменю"
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M1ZmQ0MTJiY2VjNDhmMjk4MDIyOWY0NGFkNzg1ZTlkMjQ3ZjY2YjZhOTFlZWY3YTk4ZDc2NmJkMTFkNGExOSJ9fX0=")
                                                                    .build(), click -> {
            CustomTnTListMenu.generateInventory(player);
            return true;
        });

        SuppliedItem setTag = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("&#14d65cУ&#1cd861с&#25d966т&#2ddb6bа&#36dd70н&#3ede75о&#46e07bв&#4fe280и&#57e485т&#60e58aь &#68e78fу&#70e994н&#79ea99и&#81ec9eк&#89eea3а&#92efa8л&#9af1adь&#a3f3b2н&#abf5b8ы&#b3f6bdй &#bcf8c2к&#c4fac7л&#cdfbccю&#d5fdd1ч")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "",
                                                                            "§7Используйте данную настройку, чтобы установить",
                                                                            "§7уникальный ключ для данного динамита, который",
                                                                            "§7будет служить его идентификатором",
                                                                            "",
                                                                            " §8▪ §fТекущий ключ: §a" + (customTnT.getKey().isEmpty() ? "§cНет" : customTnT.getKey()),
                                                                            "",
                                                                            "§8➩ §aНажмите, что установить новый интервал"
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU1ZDEwZjlmMzI0NjU5OTY1OGUwYzZkMDQ0NGU4NzRmZmFjMDE0MTA0NDBjNWNmZWM2ZjE5ZDNhYTg4Zjk0NSJ9fX0=")
                                                                    .build(), click -> {
            VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c➤ §fВведите, пожалуйста, введите новый уникальный идентификатор в чат");
            player.closeInventory();
            PlayerInputHandler.addPlayer(player, (str) -> {
                customTnT.setKey(str);
                generateInventory(player, customTnT);
            });
            return true;
        });

        SuppliedItem save = new AutoUpdateItem(20, () ->
                s -> {
                    if (customTnT.getKey().isEmpty()) {
                        List<String> nullParam = new ArrayList<>();
                        if (customTnT.getKey().isEmpty()) nullParam.add("Идентификатор");
                        return VersionAdapter.getItemBuilder()
                                .setDisplayName("&#14d65cС&#2cdb6bо&#44e079х&#5ce588р&#75ea97а&#8deea5н&#a5f3b4и&#bdf8c2т&#d5fdd1ь")
                                .setLore(new ArrayList<>(Arrays.asList(
                                        "",
                                        "§7Используйте данную кнопку для сохранения",
                                        "§7Динамита",
                                        "",
                                        "§cВнимание! Один или несколько параметров не указаны,",
                                        "§cУкажите их, чтобы сохранить настройку: §l" + String.join(", ", nullParam),
                                        "",
                                        "§8➩ §2Сохранение невозможно"
                                )))
                                .setType(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial())
                                .build();
                    }
                    else {
                        return VersionAdapter.getItemBuilder()
                                .setDisplayName("&#14d65cС&#2cdb6bо&#44e079х&#5ce588р&#75ea97а&#8deea5н&#a5f3b4и&#bdf8c2т&#d5fdd1ь")
                                .setLore(new ArrayList<>(Arrays.asList(
                                        "",
                                        "§7Используйте данную кнопку для сохранения",
                                        "§7Динамита",
                                        "",
                                        "§8➩ §aНажмите, чтобы сохранить"
                                )))
                                .setType(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial())
                                .build();
                    }
                }) {
            @Override
            public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
                if (customTnT.getKey().isEmpty()) return;
                customTnTManager.getCustomTnTRepository().saveCustomTnT(customTnT);
                CustomTnTListMenu.generateInventory(player);
            }
        };

        SuppliedItem delete = new SuppliedItem(() ->
                                                       s -> VersionAdapter.getSkullBuilder()
                                                               .setDisplayName("&#14d65cУ&#34dd70д&#54e383а&#75ea97л&#95f0aaи&#b5f7beт&#d5fdd1ь")
                                                               .setLore(new ArrayList<>(Arrays.asList(
                                                                       "",
                                                                       "§7Используйте данную кнопку для удаления",
                                                                       "§7Задачи",
                                                                       "",
                                                                       "§cВнимание! После удаления задания, восстановить",
                                                                       "§cЕё будет &lневозможно",
                                                                       "",
                                                                       "§8➩ §aНажмите, чтобы удалить задачу"
                                                               )))
                                                               .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDczZmQ4YTA2ZTZlYTgyMDc5NGNkYTIxNGZjNDZiNGMzMjlmYTlhZjMyNGU0NGVhYjQ0OTZjMmQ5ZjViYTZmZCJ9fX0=")
                                                               .build(), click -> {
            customTnTManager.getCustomTnTRepository().deleteCustomTnT(customTnT);
            CustomTnTListMenu.generateInventory(player);
            return true;
        });

        SuppliedItem setOptions = new SuppliedItem(() ->
                                                       s -> VersionAdapter.getSkullBuilder()
                                                               .setDisplayName("&#14d65cН&#1dd862а&#26da67с&#30dc6dт&#39dd72р&#42df78о&#4be17dй&#54e383к&#5ee589а &#67e78eо&#70e994п&#79ea99ц&#82ec9fи&#8beea4й &#95f0aaд&#9ef2b0и&#a7f4b5н&#b0f6bbа&#b9f7c0м&#c3f9c6и&#ccfbcbт&#d5fdd1а")
                                                               .setLore(new ArrayList<>(Arrays.asList(
                                                                       "",
                                                                       "§7Используйте данную кнопку для перехода к",
                                                                       "§7установке опций динамита",
                                                                       "",
                                                                       "§8➩ §aНажмите, чтобы перейти"
                                                               )))
                                                               .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTk0OWExOGNiNTJjMjkzZmU3ZGU3YmExMDE0NjcxMzQwZWQ3ZmY4ZTVkNzA1YjJkNjBiZjg0ZDUzMTQ4ZTA0In19fQ==")
                                                               .build(), click -> {
            SettingCustomTnTOptionsMenu.generateInventory(player, customTnT);
            return true;
        });

        SuppliedItem setMeta = new SuppliedItem(() ->
                                                           s -> VersionAdapter.getSkullBuilder()
                                                                   .setDisplayName("&#14d65cН&#1bd861а&#23d965с&#2adb6aт&#32dc6eр&#39de73о&#41df77й&#48e17cк&#4fe280а &#57e485м&#5ee589е&#66e78eт&#6de892а&#75ea97д&#7ceb9bа&#83eda0н&#8beea4н&#92f0a9ы&#9af1adх &#a1f3b2д&#a8f4b6и&#b0f6bbн&#b7f7bfа&#bff9c4м&#c6fac8и&#cefccdт&#d5fdd1а")
                                                                   .setLore(new ArrayList<>(Arrays.asList(
                                                                           "",
                                                                           "§7Используйте данную кнопку для перехода к",
                                                                           "§7установке метаданных предмета динамита",
                                                                           "",
                                                                           "§8➩ §aНажмите, чтобы перейти"
                                                                   )))
                                                                   .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VjOWM5ODg2MTI2YTkwMzg5MDM4YWY2ZGY3NDYyZjA3OTc0NmI4ZWFlYTJlNTExZTEwZjY2ZTBhNTljN2QyMiJ9fX0=")
                                                                   .build(), click -> {
            SettingCustomTnTMetaMenu.generateInventory(player, customTnT);
            return true;
        });

        Gui gui = Gui.normal()
                .setStructure(
                        "b b b b b b b b b",
                        "b q . w . e . u b",
                        "b . . . . . . . b",
                        "b . . . t . . . b",
                        "b b b b b b b b b",
                        ". . . . c . . . . "
                )
                .addIngredient('c', closeButton)
                .addIngredient('b', XMaterial.GRAY_STAINED_GLASS_PANE.parseItem())
                .addIngredient('u', delete)
                .addIngredient('q', setTag)
                .addIngredient('w', setOptions)
                .addIngredient('e', setMeta)
                .addIngredient('t', save)
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("§8Настройка кастомного динамита")
                .setGui(gui)
                .build();
        window.open();
    }
}
