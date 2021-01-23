package eu.andycraftz.joinmessageplus.bukkit;

import fr.xephi.authme.api.v3.AuthMeApi;

import java.util.logging.Level;

import org.bstats.bukkit.MetricsLite;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 3.3
 */
public class JoinMessagePlus extends JavaPlugin {

    // Config
    public Config cfg;
    // PlaceHoldereAPI
    public boolean placeholderapi;
    // AuthMeAPI
    public boolean authmeapi;
    public AuthMeApi authmeapiinstance;
    // BungeeCordSupport
    public boolean bungeesupport;

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();
        // Message
        getLogger().log(Level.INFO, "[#]==========< JoinMessagePlus >==========[#]");
        getLogger().log(Level.INFO, "Version: {0}", getDescription().getVersion());
        getLogger().log(Level.INFO, "Web: https://dev.bukkit.org/bukkit-plugins/join-message-plus/");
        // Metrics
        MetricsLite metrics = new MetricsLite(this, 3040);
        // Config
        cfg = new Config(this);
        // PlaceholderAPI
        placeholderapi = pm.isPluginEnabled("PlaceholderAPI");
	// AuthMeAPI
	authmeapi = pm.isPluginEnabled("AuthMe") && cfg.getConfig().getBoolean("AuthMeSupport.Enabled");
	String authme = authmeapi ? " + AuthMe" : "";
        // BungeeSupport && Mode
        boolean spigot = false;
	boolean bungeesupportcfg = cfg.getConfig().getBoolean("BungeeSupport.Enabled");
        try {
            Bukkit.getServer().spigot();
            spigot = true;
        } catch (NoSuchMethodError err) {
        }
	bungeesupport = false;
        if (spigot) {
	    bungeesupport = Bukkit.getServer().spigot().getConfig().getBoolean("settings.bungeecord") && bungeesupportcfg;
	    // Mode
	    getLogger().log(Level.INFO, "Mode: {0}", (bungeesupport ? "BungeeCord" : (!bungeesupportcfg ? "BungeeCord + Standalone" : "Standalone")) + authme);
	    
	} else {
	    // Mode
	    getLogger().log(Level.INFO, "Mode: Standalone{0}", authme);
	}
	// AuhMeSupport
	if (authmeapi) {
	    authmeapiinstance = AuthMeApi.getInstance();
	}
        // Message
        getLogger().log(Level.INFO, "Plugin by AndyCraftz");
        // Listener
        pm.registerEvents(new MessagesL(this), this);
    }
}
