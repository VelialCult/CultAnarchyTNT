package ru.velialcult.tnt.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.velialcult.library.bukkit.utils.PlayerUtil;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.library.java.text.ReplaceData;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.manager.CustomTnTManager;
import ru.velialcult.tnt.file.MessagesFile;
import ru.velialcult.tnt.menu.CustomTnTListMenu;

import java.util.List;
import java.util.stream.Collectors;

public class CultAnarchyTnTCommand implements CommandExecutor, TabCompleter {

    private final MessagesFile messagesFile;
    private final CustomTnTManager customTnTManager;

    public CultAnarchyTnTCommand(MessagesFile messagesFile,
                                 CustomTnTManager customTnTManager) {
        this.messagesFile = messagesFile;
        this.customTnTManager = customTnTManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("nighttnt.give")) {
            if (args.length == 0) {
                VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.help"));
                return true;
            } else {
                String cmd = args[0].toUpperCase();

                switch (cmd) {

                    case "GIVE": {
                        if (args.length != 4) {
                            VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.give.usage"));
                            return true;
                        }

                        Player player = Bukkit.getPlayer(args[1]);

                        if (player == null) {
                            VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.player-not-found"));
                            return true;
                        }

                        CustomTnT customTnT = customTnTManager.getCustomTnTRepository().getByKey(args[2]);
                        if (customTnT == null) {
                            VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.give.not-found",
                                                                                                                         new ReplaceData("{value}", args[2])
                            ));
                            return true;
                        }

                        int count = 0;
                        try {
                            count = Integer.parseInt(args[3]);
                        } catch (NumberFormatException e) {
                            VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.wrong-value",
                                                                                                                         new ReplaceData("{value}", args[2])
                            ));
                        }

                        ItemStack itemStack = customTnT.createItemStack();
                        itemStack.setAmount(count);
                        PlayerUtil.giveItem(player, itemStack);
                        VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.give.give",
                                                                                                                     new ReplaceData("{count}", count),
                                                                                                                     new ReplaceData("{tnt}", customTnT.getMeta().getDisplayName()),
                                                                                                                     new ReplaceData("{player}", player.getName())
                        ));
                        VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.give.info",
                                                                                                                     new ReplaceData("{count}", count),
                                                                                                                     new ReplaceData("{tnt}", customTnT.getMeta().getDisplayName())));

                        break;
                    }

                    case "SETTINGS": {
                        if (PlayerUtil.senderIsPlayer(sender)) {
                            if (args.length != 1) {
                                VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.settings.usage"));
                                return true;
                            }

                            CustomTnTListMenu.generateInventory((Player) sender);
                        }
                        break;
                    }

                    default: {
                        VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.help"));
                        return true;
                    }
                }
            }
        } else {
            VersionAdapter.MessageUtils().sendMessage(sender, messagesFile.getFileOperations().getString("messages.commands.dont-have-permissions"));
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("give", "settings");
        } else if (args.length == 2) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        } else if (args.length == 3) {
            return customTnTManager.getCustomTnTRepository().getCustomTnTList().stream().map(CustomTnT::getKey).collect(Collectors.toList());
        } else if (args.length == 4) {
            return List.of("Введите количество");
        }
        return null;
    }
}
