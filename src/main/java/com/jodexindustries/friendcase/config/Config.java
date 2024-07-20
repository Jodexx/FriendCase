package com.jodexindustries.friendcase.config;

import com.jodexindustries.friendcase.bootstrap.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private final File configFile;
    private YamlConfiguration config;

    public Config(Main main) {
        this.configFile = new File(main.getDataFolder(), "config.yml");
        if(!configFile.exists()) main.saveResource("config.yml", false);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }
}