package com.jodexindustries.friendcase.bootstrap;

import com.jodexindustries.donatecase.api.CaseManager;
import com.jodexindustries.donatecase.api.addon.internal.InternalJavaAddon;
import com.jodexindustries.friendcase.utils.CustomConfig;
import com.jodexindustries.friendcase.utils.Utils;

public final class FriendCaseAddon extends InternalJavaAddon implements FriendCase {
    private CustomConfig config;
    private CaseManager api;
    @Override
    public void onEnable() {
        api = getCaseAPI();
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
