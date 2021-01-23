package eu.andycraftz.joinmessageplus.bungeecord;

import net.md_5.bungee.config.Configuration;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bungee Plugin
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

    public Configuration getConfig() {
        return this.cfgm.getConfig();
    }

}
