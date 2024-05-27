package com.jodexindustries.friendcase.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class CustomConfig {
    private File configFile;
    private YamlConfiguration config;
    private final File dataFolder;

    public CustomConfig(File dataFolder) {
        this.configFile = new File(dataFolder, "config.yml");
        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.dataFolder = dataFolder;
    }
    public void setup() {
        configFile = new File(dataFolder, "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public FileConfiguration getConfig() {
        return config;
    }
}