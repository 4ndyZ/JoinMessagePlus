package eu.andycraftz.joinmessageplus.bukkit;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 3.3
 */
public class Config {

    private final JoinMessagePlus plugin;

    private final ConfigM cfgm;

    public Config(JoinMessagePlus plugin) {
        this.plugin = plugin;
        this.cfgm = new ConfigM(this.plugin, "config");
    }

    public void saveConfig() {
        this.cfgm.saveConfig();
    }

    public FileConfiguration getConfig() throws NullPointerException {
        return this.cfgm.getConfig();
    }
}
