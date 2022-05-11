package eu.andycraftz.joinmessageplus.bukkit;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Files;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @version 3.4
 */
public class ConfigM {

    private final JoinMessagePlus plugin;

    private final File file;
    private FileConfiguration cfg;

    public ConfigM(JoinMessagePlus plugin, String name) {
        this.plugin = plugin;

        this.file = new File(this.plugin.getDataFolder(), name + ".yml");

        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                InputStream is = plugin.getResource(name + "-bukkit.yml");
                OutputStream os = Files.newOutputStream(file.toPath());
                assert is != null;
                ByteStreams.copy(is, os);
            } catch (IOException err) {
                this.plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
            }
        } else {
            FileConfiguration tempcfg = YamlConfiguration.loadConfiguration(file);
            // Convert 3.0 config to 3.3
            if (tempcfg.contains("EnableJoinMessage")) {
                this.plugin.getLogger().log(Level.INFO, "Config: Converting old config ...");

                boolean EnableJoinMessage = tempcfg.getBoolean("EnableJoinMessage");
                boolean EnableQuitMessage = tempcfg.getBoolean("EnableQuitMessage");
                String JoinMessage = tempcfg.getString("JoinMessage");
                assert JoinMessage != null;
                JoinMessage = JoinMessage.replace("%player", "%player_name%").replace("%displayname", "%player_displayname%");
                String QuitMessage = tempcfg.getString("QuitMessage");
                assert QuitMessage != null;
                QuitMessage = QuitMessage.replace("%player", "%player_name%").replace("%displayname", "%player_displayname%");


                file.deleteOnExit();

                try {
                    file.createNewFile();
                    InputStream is = plugin.getResource(name + "-bukkit.yml");
                    OutputStream os = Files.newOutputStream(file.toPath());
                    assert is != null;
                    ByteStreams.copy(is, os);
                } catch (IOException err) {
                    this.plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
                }

                tempcfg = YamlConfiguration.loadConfiguration(file);

                tempcfg.set("JoinMessage.Enabled", EnableJoinMessage);
                tempcfg.set("QuitMessage.Enabled", EnableQuitMessage);
                tempcfg.set("JoinMessage.Message", JoinMessage);
                tempcfg.set("QuitMessage.Message", QuitMessage);

                try {
                    tempcfg.save(file);
                } catch (IOException err) {
                    this.plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
                }

                this.plugin.getLogger().log(Level.INFO, "Config: Complete Converting");
            }
            // Convert 3.1 to 3.3
            else if (!tempcfg.contains("AuthMeSupport.Enabled")) {
                this.plugin.getLogger().log(Level.INFO, "Config: Converting old config ...");

                boolean EnableBungeeSupport = tempcfg.getBoolean("BungeeSupport.Enabled");
                boolean EnableJoinMessage = tempcfg.getBoolean("JoinMessage.Enabled");
                boolean EnableQuitMessage = tempcfg.getBoolean("QuitMessage.Enabled");
                String JoinMessage = tempcfg.getString("JoinMessage.Message");
                String QuitMessage = tempcfg.getString("QuitMessage.Message");

                file.deleteOnExit();

                try {
                    file.createNewFile();
                    InputStream is = plugin.getResource(name + "-bukkit.yml");
                    OutputStream os = Files.newOutputStream(file.toPath());
                    assert is != null;
                    ByteStreams.copy(is, os);
                } catch (IOException err) {
                    this.plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
                }

                tempcfg = YamlConfiguration.loadConfiguration(file);

                tempcfg.set("BungeeSupport.Enabled", EnableBungeeSupport);
                tempcfg.set("JoinMessage.Enabled", EnableJoinMessage);
                tempcfg.set("QuitMessage.Enabled", EnableQuitMessage);
                tempcfg.set("JoinMessage.Message", JoinMessage);
                tempcfg.set("QuitMessage.Message", QuitMessage);

                try {
                    tempcfg.save(file);
                } catch (IOException err) {
                    this.plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
                }

                this.plugin.getLogger().log(Level.INFO, "Config: Complete Converting");
            }
        }
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return cfg;
    }

    public void saveConfig() {
        try {
            cfg.save(file);
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }
    }

    public void reloadConfig() {
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }
}
