package com.jodexindustries.friendcase.utils;

import com.jodexindustries.friendcase.FriendSubCommand;
import com.jodexindustries.friendcase.bootstrap.FriendCase;

import java.io.File;

public class Utils {
    public static CustomConfig load(FriendCase plugin) {
        CustomConfig config = new CustomConfig(plugin.getDataFolder());
        if (!(new File(plugin.getDataFolder(), "config.yml")).exists()) {
            plugin.saveResource("config.yml", false);
        }
        config.setup();
        plugin.getAPI().getSubCommandManager().registerSubCommand("gift", new FriendSubCommand(plugin));
        return config;
    }
    public static void unload(FriendCase plugin) {
        plugin.getAPI().getSubCommandManager().unregisterSubCommand("gift");
    }
}
