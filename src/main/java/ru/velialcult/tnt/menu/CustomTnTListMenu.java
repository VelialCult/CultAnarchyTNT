package ru.velialcult.tnt.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.tnt.CultAnarchyTnT;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTOptions;
import ru.velialcult.tnt.custom.manager.CustomTnTManager;
import ru.velialcult.tnt.custom.repository.CustomTnTRepository;
import ru.velialcult.tnt.menu.buttons.BackButton;
import ru.velialcult.tnt.menu.buttons.ForwardButton;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.impl.SuppliedItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTnTListMenu {

    public static void generateInventory(Player player) {
        CustomTnTManager customTnTManager = CultAnarchyTnT.getInstance().getCustomTnTManager();
        CustomTnTRepository customTnTRepository = customTnTManager.getCustomTnTRepository();

        SuppliedItem closeButton = new SuppliedItem(() ->
                                                            s -> VersionAdapter.getSkullBuilder()
                                                                    .setDisplayName("§6Закрыть меню")
                                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                            "§7Нажмите, чтобы закрыть данное",
                                                                            "§7меню."
                                                                    )))
                                                                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M1ZmQ0MTJiY2VjNDhmMjk4MDIyOWY0NGFkNzg1ZTlkMjQ3ZjY2YjZhOTFlZWY3YTk4ZDc2NmJkMTFkNGExOSJ9fX0=")
                                                                    .build(), click -> {
            player.closeInventory();
            return true;
        });

        List<Item> items = customTnTRepository.getCustomTnTList().stream().map(customTnT -> {
            CustomTnTOptions customTnTOptions = customTnT.getCustomTnTOptions();
            return new SuppliedItem(() ->
                                            s -> VersionAdapter.getItemBuilder().setType(XMaterial.TNT.parseMaterial())
                                                    .setDisplayName("&#14d65c" + customTnT.getKey())
                                                    .setLore(new ArrayList<>(Arrays.asList(
                                                                     "",
                                                                     " §8▪ §fРадиус взрыва: §e" + customTnTOptions.getRadius_explosion(),
                                                                     " §8▪ §fИнтервал взрыва: §e" + customTnTOptions.getExplosionInterval(),
                                                                     " §8▪ §fВзрывает ли регионы: " + (customTnTOptions.isBreakRegions() ? "§aДа" : "§cНет"),
                                                                     " §8▪ §fВзрывает ли спавнеры: " + (customTnTOptions.isBreakSpawners() ? "§aДа" : "§cНет"),
                                                                     " §8▪ §fВзрывает ли блоки в воде: " + (customTnTOptions.isBreakBlocksInWater() ? "§aДа" : "§cНет"),
                                                                     " §8▪ §fВзрывает ли спавнеры в регионе: " + (customTnTOptions.isBreakSpawnersInRegion() ? "§aДа" : "§cНет"),
                                                                     " §8▪ §fВзрывает ли обсидиан: " + (customTnTOptions.isBreakObsidian() ? "§aДа" : "§cНет"),
                                                                     " §8▪ §fВзрывает ли неразрушимые регионы: " + (customTnTOptions.isBreakUnbreakableRegions() ? "§aДа" : "§cНет"),
                                                                     "",
                                                                     "§8➩ §aИзменить"
                                                             ))
                                                    ).build(),
                                    click -> {
                                        SettingCustomTnTMenu.generateInventory(player, customTnT);
                                        return true;
                                    });
        }).collect(Collectors.toList());

        SuppliedItem addTnT = new SuppliedItem(() ->
                                                        s -> VersionAdapter.getSkullBuilder()
                                                                .setDisplayName("&#14d65c&&#2cdb6b#&#44e0791&#5ce5884&#75ea97d&#8deea56&#a5f3b45&#bdf8c2c&#d5fdd1Г")
                                                                .setLore(VersionAdapter.TextUtil().setReplaces(new ArrayList<>(Arrays.asList(
                                                                        "",
                                                                        "§7В данном меню вы можете создавать новые,",
                                                                        "§7кастомные динамиты",
                                                                        "",
                                                                        "§8➩ §aДобавить"
                                                                ))))
                                                                .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjlmMjc3NzNmMDhkY2M2ODU0NzNhMjFmNjQ3NmZlYjZhYWQ4OWFjMjRhMTNmMDRlZjYzNjkyYjlhYzlmZWE5MCJ9fX0=")
                                                                .build(), click -> {
            SettingCustomTnTMenu.generateInventory(player, new CustomTnT());
            return true;
        });

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
                .addIngredient('u', addTnT)
                .setContent(items)
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("§8CultAnarchyTnT by VelialCult")
                .setGui(gui)
                .build();
        window.open();
    }
}
