package com.jodexindustries.friendcase;

import com.jodexindustries.donatecase.api.Case;
import com.jodexindustries.donatecase.api.SubCommand;
import com.jodexindustries.donatecase.api.SubCommandType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FriendSubCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                sendHelp(sender);
            } else {
                if (args.length < 3) {
                    if(args.length == 1) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            if (sender.hasPermission("donatecase.admin")) {
                                FriendCase.instance.reloadConfig();
                                sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.ConfigReloaded")));
                            }
                        } else {
                            sendHelp(sender);
                        }
                    }
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    String caseType = args[1];
                    int keys;
                    try {
                        keys = Math.abs(Integer.parseInt(args[2]));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.NumberFormat")));
                        return;
                    }
                    if(Case.hasCaseByName(caseType)) {
                        if(Case.getKeys(caseType, p.getName()) < 1) {
                            if(target != null) {
                                if(target != p) {
                                    Case.removeKeys(caseType, p.getName(), keys);
                                    Case.addKeys(caseType, target.getName(), keys);
                                    target.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.YouReceivedGift")
                                            .replace("%sender%", sender.getName())));
                                    sender.sendMessage(rc(rc(FriendCase.instance.getConfig().getString("Messages.YouSendGift"))
                                            .replace("%target%", target.getName())));
                                } else {
                                    sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.GiftYourself")));
                                }
                            } else {
                                sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.PlayerNotFound")));
                            }
                        } else {
                            sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.MinNumber")));
                        }
                    } else {
                        sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.CaseNotFound")));
                    }
                }
            }
        } else {
            sender.sendMessage(rc(FriendCase.instance.getConfig().getString("Messages.OnlyPlayers")));
        }
    }
    private void sendHelp(CommandSender sender) {
        for (String msg : FriendCase.instance.getConfig().getStringList("Messages.Help")) {
            sender.sendMessage(rc(msg));
        }
    }
    private String rc(String text) {
        return hex(text);
    }

    public static String hex(String text) {
        return ChatColor.translateAlternateColorCodes('&', translateHexColorCodes(text));
    }
    public static final Pattern HEX_PATTERN = Pattern.compile("#([A-Fa-f0-9]{6})");
    public static final char COLOR_CHAR = ChatColor.COLOR_CHAR;
    public static String translateHexColorCodes(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public SubCommandType getType() {
        return SubCommandType.PLAYER;
    }

    @Override
    public String[] getArgs() {
        List<String> argsList = new ArrayList<>();

        for (String args : FriendCase.instance.getConfig().getStringList("Api.Args")) {
            argsList.add(rc(args));
        }

        return argsList.toArray(new String[0]);
    }

    @Override
    public String getDescription() {
        return rc(FriendCase.instance.getConfig().getString("Api.Description"));
    }
}
