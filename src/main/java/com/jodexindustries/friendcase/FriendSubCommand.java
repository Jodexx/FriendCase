package com.jodexindustries.friendcase;

import com.jodexindustries.donatecase.api.Case;
import com.jodexindustries.donatecase.api.SubCommand;
import com.jodexindustries.donatecase.api.SubCommandType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FriendSubCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                sendHelp(sender);
            } else {
                if (args.length < 3) {
                    sendHelp(sender);
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    String caseType = args[1];
                    int keys;
                    try {
                        keys = Math.abs(Integer.parseInt(args[2]));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(rc("&cNumber format exception"));
                        return;
                    }
                    if(Case.hasCaseByName(caseType)) {
                        if(Case.getKeys(caseType, p.getName()) < 1) {
                            if(target != null) {
                                if(target != p) {
                                    Case.removeKeys(caseType, p.getName(), keys);
                                    Case.addKeys(caseType, target.getName(), keys);
                                    target.sendMessage(rc("&aYou successfully received gift from player " + sender.getName()));
                                    sender.sendMessage(rc("&aYou successfully send gift for player " + target.getName()));
                                } else {
                                    sender.sendMessage(rc("&cYou can't send gift for yourself"));
                                }
                            } else {
                                sender.sendMessage(rc("&cPlayer not online"));
                            }
                        } else {
                            sender.sendMessage(rc("&cNumber of your keys less than 1"));
                        }
                    } else {
                        sender.sendMessage(rc("&cCase not found!"));
                    }
                }
            }
        } else {
            // only from player
            sender.sendMessage(rc("&cYou can use this command only from player!"));
        }
    }
    private void sendHelp(CommandSender sender) {
        sender.sendMessage(rc("&9/dc gift &7(&aPlayer&7) (&aCase type&7) (&aKeys&7)"));
    }
    private String rc(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
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
        return new String[] {"&7(&aPlayer&7)", "(&aCase type&7)", "(&aKeys&7)"};
    }

    @Override
    public String getDescription() {
        return "&2Send gift for another player";
    }
}
