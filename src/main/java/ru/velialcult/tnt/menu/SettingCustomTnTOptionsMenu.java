package ru.velialcult.tnt.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import ru.velialcult.library.bukkit.inventory.PlayerInputHandler;
import ru.velialcult.library.bukkit.utils.InventoryUtil;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.impl.SuppliedItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingCustomTnTOptionsMenu {

    protected static void generateInventory(Player player, CustomTnT customTnT) {

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
            SettingCustomTnTMenu.generateInventory(player, customTnT);
            return true;
        });

        SuppliedItem setBreakRegions = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("&#14d65cД&#22d964о&#30dc6dб&#3dde75а&#4be17dв&#59e486и&#67e78eт&#75ea97ь &#82ec9fр&#90efa7е&#9ef2b0г&#acf5b8и&#b9f7c0о&#c7fac9н&#d5fdd1ы")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "",
                                                                            "§7Используйте данное меню, чтобы редактировать",
                                                                            "§7Какие типы регионов могут разрушаться этим",
                                                                            "§7Типом динамита",
                                                                            "",
                                                                            "&8➩ §aИзменить"
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjU3ZDhhZDgxOGY2ZjNiYTgzNmM1MGIxMjc1NzlhOWI4OGIyZGVmOTJlZTY5NTI4MWMwMTczOGY1ZWMxYjMyYSJ9fX0=")
                                                                    .build(), click -> {
            RegionSelectMenu.generateInventory(player, customTnT);
            return true;
        });

        SuppliedItem setRadius = new SuppliedItem(() ->
                                                          s -> VersionAdapter.getSkullBuilder()
                                                                  .setDisplayName("&#14d65cУ&#1dd862с&#26da67т&#30dc6dа&#39dd72н&#42df78о&#4be17dв&#54e383и&#5ee589т&#67e78eь &#70e994р&#79ea99а&#82ec9fд&#8beea4и&#95f0aaу&#9ef2b0с &#a7f4b5в&#b0f6bbз&#b9f7c0р&#c3f9c6ы&#ccfbcbв&#d5fdd1а")
                                                                  .setLore(new ArrayList<>(Arrays.asList(
                                                                          "",
                                                                          "§7Используйте данную настройку, чтобы установить",
                                                                          "§7динамиту радиус взрыва.",
                                                                          "",
                                                                          " §8▪ §fТекущий радиус взрыва: §a" + customTnTOptions.getRadius_explosion(),
                                                                          "",
                                                                          "§8➩ §aИзменить"
                                                                  )))
                                                                  .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjgzMWQ1NzIxOTUwZWZhOWFhNzk5NjdlYjE5MTZhYmViNjRjOTc4ZWE4NTkzYTBjNjgzMzU0ODA5YzZjMzYxZCJ9fX0=")
                                                                  .build(), click -> {
            VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c➤ §fВведите, пожалуйста, введите новый радиус взрыва в чат");
            player.closeInventory();
            PlayerInputHandler.addPlayer(player, (str) -> {
                try {
                    int radius = Integer.parseInt(str);
                    customTnTOptions.setRadius_explosion(radius);
                    generateInventory(player, customTnT);
                } catch (Exception exception) {
                    VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c✘ §fВы ввели неверное значение");
                }
            });
            return true;
        });

        SuppliedItem breakObsidian = InventoryUtil.createBooleanSettingItem(
                "&#14d65cВ&#1fd863з&#2bdb6aр&#36dd71ы&#41df78в&#4de17eа&#58e485е&#63e68cт &#6fe893л&#7aeb9aи &#86eda1о&#91efa8б&#9cf2afс&#a8f4b5и&#b3f6bcд&#bef8c3и&#cafbcaа&#d5fdd1н",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит взврывать блоки обсидиана."),
                customTnTOptions,
                CustomTnTOptions::isBreakObsidian,
                CustomTnTOptions::setBreakObsidian
        );

        SuppliedItem breakRegions = InventoryUtil.createBooleanSettingItem(
                "§&#14d65cВ&#20d863з&#2cdb6bр&#38dd72ы&#44e079в&#50e281а&#5ce588е&#68e78fт &#75ea97л&#81ec9eи &#8deea5р&#99f1acе&#a5f3b4г&#b1f6bbи&#bdf8c2о&#c9fbcaн&#d5fdd1ы",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит взврывать регионы."),
                customTnTOptions,
                CustomTnTOptions::isBreakRegions,
                CustomTnTOptions::setBreakRegions
        );

        SuppliedItem breakBlocksInWater = InventoryUtil.createBooleanSettingItem(
                "&#14d65cВ&#20d863з&#2cdb6bр&#38dd72ы&#44e079в&#50e281а&#5ce588е&#68e78fт&#75ea97с&#81ec9eя &#8deea5л&#99f1acи &#a5f3b4в &#b1f6bbв&#bdf8c2о&#c9fbcaд&#d5fdd1е",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит взрывать блоки в воде."),
                customTnTOptions,
                CustomTnTOptions::isBreakBlocksInWater,
                CustomTnTOptions::setBreakBlocksInWater
        );

        SuppliedItem breakSpawners = InventoryUtil.createBooleanSettingItem(
                "&#14d65cВ&#1fd863з&#2bdb6aр&#36dd71ы&#41df78в&#4de17eа&#58e485е&#63e68cт &#6fe893л&#7aeb9aи &#86eda1с&#91efa8п&#9cf2afа&#a8f4b5в&#b3f6bcн&#bef8c3е&#cafbcaр&#d5fdd1ы",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит взрывать спавнеры."),
                customTnTOptions,
                CustomTnTOptions::isBreakSpawners,
                CustomTnTOptions::setBreakSpawners
        );

        SuppliedItem breakSpawnersInRegion = InventoryUtil.createBooleanSettingItem(
                "&#14d65cВ&#1cd861з&#23d965р&#2bdb6aы&#33dc6fв&#3bde73а&#42df78е&#4ae17dт &#52e281л&#59e486и &#61e68bс&#69e78fп&#71e994а&#78ea99в&#80ec9eн&#88eda2е&#90efa7р&#97f1acы &#9ff2b0в &#a7f4b5р&#aef5baе&#b6f7beг&#bef8c3и&#c6fac8о&#cdfbccн&#d5fdd1е",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит взрывать спавнеры даже в регионе."),
                customTnTOptions,
                CustomTnTOptions::isBreakSpawnersInRegion,
                CustomTnTOptions::setBreakSpawnersInRegion
        );

        SuppliedItem breakUnbreakableRegions = InventoryUtil.createBooleanSettingItem(
                "&#14d65cВ&#1bd760з&#22d964р&#29da69ы&#30dc6dв&#36dd71а&#3dde75е&#44e079т &#4be17dл&#52e382и &#59e486н&#60e58aе&#67e78eр&#6ee892а&#75ea97з&#7beb9bр&#82ec9fу&#89eea3ш&#90efa7и&#97f0abм&#9ef2b0ы&#a5f3b4е &#acf5b8р&#b3f6bcе&#b9f7c0г&#c0f9c4и&#c7fac9о&#cefccdн&#d5fdd1ы",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит взрывать неразуршимые регионы"),
                customTnTOptions,
                CustomTnTOptions::isBreakUnbreakableRegions,
                CustomTnTOptions::setBreakUnbreakableRegions
        );

        SuppliedItem setInterval = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("&#14d65cУ&#1cd861с&#23d965т&#2bdb6aа&#33dc6fн&#3bde73о&#42df78в&#4ae17dи&#52e281т&#59e486ь &#61e68bи&#69e78fн&#71e994т&#78ea99е&#80ec9eр&#88eda2в&#90efa7а&#97f1acл &#9ff2b0д&#a7f4b5о &#aef5baв&#b6f7beз&#bef8c3р&#c6fac8ы&#cdfbccв&#d5fdd1а")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "",
                                                                            "§7Используйте данную настройку, чтобы установить",
                                                                            "§7динамиту интервал до взрыва.",
                                                                            "",
                                                                            " §8▪ §fТекущий интервал: §a" + customTnTOptions.getExplosionInterval(),
                                                                            "",
                                                                            "§8➩ §aИзменить"
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmU1ZDEwZjlmMzI0NjU5OTY1OGUwYzZkMDQ0NGU4NzRmZmFjMDE0MTA0NDBjNWNmZWM2ZjE5ZDNhYTg4Zjk0NSJ9fX0=")
                                                                    .build(), click -> {
            VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c➤ §fВведите, пожалуйста, введите новый интервал до взрыва в чат");
            player.closeInventory();
            PlayerInputHandler.addPlayer(player, (str) -> {
                try {
                    int interval = Integer.parseInt(str);
                    customTnTOptions.setExplosionInterval(interval);
                    generateInventory(player, customTnT);
                } catch (Exception exception) {
                    VersionAdapter.MessageUtils().sendMessage(player, "&#14d65c✘ §fВы ввели неверное значение");
                }
            });
            return true;
        });

        SuppliedItem autoIgnite = InventoryUtil.createBooleanSettingItem(
                "&#14d65cА&#1cd861в&#25d966т&#2ddb6bо&#36dd70м&#3ede75а&#46e07bт&#4fe280и&#57e485ч&#60e58aе&#68e78fс&#70e994к&#79ea99о&#81ec9eе &#89eea3п&#92efa8о&#9af1adд&#a3f3b2ж&#abf5b8и&#b3f6bdг&#bcf8c2а&#c4fac7н&#cdfbccи&#d5fdd1е",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли динамит сразу зажигаться"),
                customTnTOptions,
                CustomTnTOptions::isAutoIgnite,
                CustomTnTOptions::setAutoIgnite
        );

        SuppliedItem gravity = InventoryUtil.createBooleanSettingItem(
                "&#14d65cГ&#29da69р&#3fdf76а&#54e383в&#6ae790и&#7fec9dт&#95f0aaа&#aaf4b7ц&#c0f9c4и&#d5fdd1я",
                List.of("§7Используйте данную настройку, чтобы установить",
                        "§7будет ли влиять физика на поведение динамита"),
                customTnTOptions,
                CustomTnTOptions::isGravity,
                CustomTnTOptions::setGravity
        );

        Gui gui = Gui.normal()
                .setStructure(
                        "b b b b b b b b b",
                        "b q . w . e . r b",
                        "b y . u . i . o b",
                        "b p . a . t . . b",
                        "b b b b b b b b b",
                        ". . . . c . . . . "
                )
                .addIngredient('c', closeButton)
                .addIngredient('b', XMaterial.GRAY_STAINED_GLASS_PANE.parseItem())
                .addIngredient('q', setRadius)
                .addIngredient('w', setInterval)
                .addIngredient('e', breakObsidian)
                .addIngredient('r', breakBlocksInWater)
                .addIngredient('t', breakRegions)
                .addIngredient('y', breakSpawnersInRegion)
                .addIngredient('u', breakUnbreakableRegions)
                .addIngredient('i', breakSpawners)
                .addIngredient('o', setBreakRegions)
                .addIngredient('p', autoIgnite)
                .addIngredient('a', gravity)
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("§8Настройка опций динамита")
                .setGui(gui)
                .build();
        window.open();
    }
}
