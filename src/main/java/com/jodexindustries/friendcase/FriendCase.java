package com.jodexindustries.friendcase;

import com.jodexindustries.donatecase.api.SubCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FriendCase extends JavaPlugin {

    @Override
    public void onEnable() {

        SubCommandManager.registerSubCommand("gift", new FriendSubCommand());
    }

    @Override
    public void onDisable() {
        SubCommandManager.unregisterSubCommand("gift");
    }
}
