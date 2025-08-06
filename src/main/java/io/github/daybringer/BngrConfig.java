package io.github.daybringer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

/**
 * Abstract class for handling custom YAML configuration files in a Spigot plugin.
 * <p>
 * Subclasses must implement {@link #setupDefaults()} to initialize default values.
 * Useful for managing additional config files beyond the default {@code config.yml}.
 */
public abstract class BngrConfig
{
    protected final JavaPlugin plugin;
    protected FileConfiguration config;
    protected File file;


    /**
     * Creates and loads a custom config file.
     *
     * @param plugin   the plugin instance
     * @param fileName the name of the config file (e.g., "data.yml")
     */
    public BngrConfig(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Gets the current loaded configuration.
     *
     * @return the configuration object
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Saves the configuration to disk.
     * Logs an error if the operation fails.
     */
    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config file: " + file.getName());
            e.printStackTrace();
        }
    }

    /**
     * Reloads the configuration from disk, overwriting any in-memory changes.
     */
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Called once during construction to set default values if needed.
     * Subclasses must define this behavior.
     */
    public abstract void setupDefaults();
}
