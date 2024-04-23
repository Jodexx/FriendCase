package com.jodexindustries.friendcase;

import com.jodexindustries.donatecase.api.CaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FriendCase extends JavaPlugin {
    public static FriendCase instance;
    private CaseManager api;
    @Override
    public void onEnable() {
        instance = this;
        api = new CaseManager(this);
        saveDefaultConfig();
        api.getSubCommandManager().registerSubCommand("gift", new FriendSubCommand());
    }

    @Override
    public void onDisable() {
        api.getSubCommandManager().unregisterSubCommand("gift");
    }
}
