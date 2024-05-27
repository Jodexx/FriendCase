package com.jodexindustries.friendcase.bootstrap;

import com.jodexindustries.donatecase.api.CaseManager;
import com.jodexindustries.friendcase.utils.CustomConfig;
import com.jodexindustries.friendcase.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class FriendCasePlugin extends JavaPlugin implements FriendCase {
    private CustomConfig config;
    private CaseManager api;
    @Override
    public void onEnable() {
        api = new CaseManager(this);
        config = Utils.load(this);
    }

    @Override
    public void onDisable() {
        Utils.unload(this);
    }

    @Override
    public CustomConfig getAddonConfig() {
        return config;
    }

    @Override
    public CaseManager getAPI() {
        return api;
    }
}
