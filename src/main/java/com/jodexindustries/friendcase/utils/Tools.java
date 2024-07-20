package com.jodexindustries.friendcase.utils;

import com.jodexindustries.friendcase.FriendSubCommand;
import com.jodexindustries.friendcase.bootstrap.Main;
import com.jodexindustries.friendcase.config.Config;

public class Tools {
    private final Main main;
    private final Config config;

    public Tools(Main main) {
        this.main = main;
        this.config = new Config(main);
    }

    public void load() {
        main.getCaseAPI().getSubCommandManager().registerSubCommand("gift",
                new FriendSubCommand(this));
    }

    public void unload() {
        main.getCaseAPI().getSubCommandManager().unregisterSubCommand("gift");
    }

    public Config getConfig() {
        return config;
    }
}
