package eu.andycraftz.joinmessageplus.bungeecord;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.logging.Level;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bungee Plugin
 * @version 3.4
 */
public class ConfigM {

    private final JoinMessagePlus plugin;

    private final File file;
    private Configuration cfg;

    public ConfigM(JoinMessagePlus plugin, String name) {
        this.plugin = plugin;

        this.file = new File(this.plugin.getDataFolder(), name + ".yml");

        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                InputStream is = plugin.getResourceAsStream(name + "-bungee.yml");
                OutputStream os = new FileOutputStream(file);
                ByteStreams.copy(is, os);
            } catch (IOException err) {
                plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
            }
        }
        try {
            this.cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }
    }

    public Configuration getConfig() {
        return this.cfg;
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }
    }

    public void reloadConfig() {
        try {
            this.cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }
    }

}
