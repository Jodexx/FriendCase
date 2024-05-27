package com.jodexindustries.friendcase.bootstrap;

import com.jodexindustries.donatecase.api.CaseManager;
import com.jodexindustries.friendcase.utils.CustomConfig;

import java.io.File;

public interface FriendCase {
    CustomConfig getAddonConfig();
    CaseManager getAPI();
    File getDataFolder();
    void saveResource(String resourcePath, boolean replace);
}
