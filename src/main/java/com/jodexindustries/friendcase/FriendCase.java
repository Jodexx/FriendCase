package com.jodexindustries.friendcase;

import com.jodexindustries.donatecase.api.SubCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FriendCase extends JavaPlugin {
    public static FriendCase instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        SubCommandManager.registerSubCommand("gift", new FriendSubCommand());
    }

    @Override
    public void onDisable() {
        SubCommandManager.unregisterSubCommand("gift");
    }
}
