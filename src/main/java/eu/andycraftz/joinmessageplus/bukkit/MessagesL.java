package eu.andycraftz.joinmessageplus.bukkit;

import fr.xephi.authme.events.LoginEvent;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bukkit Plugin
 * @version 3.3
 */
public class MessagesL implements Listener {

    private final JoinMessagePlus plugin;

    private final String JoinMessage;
    private final String QuitMessage;

    private final boolean JoinMessageEnabled;
    private final boolean QuitMessageEnabled;

    public MessagesL(JoinMessagePlus plugin) {
        this.plugin = plugin;

        if (this.plugin.bungeesupport) {
            JoinMessage = "";
            QuitMessage = "";
        } else {
            JoinMessage = this.plugin.cfg.getConfig().getString("JoinMessage.Message").replace("&", "§");
            QuitMessage = this.plugin.cfg.getConfig().getString("QuitMessage.Message").replace("&", "§");
        }

        JoinMessageEnabled = this.plugin.cfg.getConfig().getBoolean("JoinMessage.Enabled");
        QuitMessageEnabled = this.plugin.cfg.getConfig().getBoolean("QuitMessage.Enabled");
	
	if (this.plugin.authmeapi) {
	    PluginManager pm = Bukkit.getPluginManager();
	    pm.registerEvents(new AuthMeL(), this.plugin);
	}
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        if (!plugin.bungeesupport) {
            if (JoinMessageEnabled) {
                if (plugin.authmeapi) {
                    return;
                }
                if (plugin.placeholderapi) {
                    Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), JoinMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
                } else {
                    Bukkit.broadcastMessage(JoinMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName()));
                }
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        if (!plugin.bungeesupport) {
            if (QuitMessageEnabled) {
                if (plugin.authmeapi) {
                    if (!plugin.authmeapiinstance.isAuthenticated(e.getPlayer())) {
                        return;
                    }
                }
                if (plugin.placeholderapi) {
                    Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), QuitMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
                } else {
                    Bukkit.broadcastMessage(QuitMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName()));
                }
            }
        }
    }

    public class AuthMeL implements Listener {
	@EventHandler
	public void onLogin(LoginEvent e) {
	    if (!plugin.bungeesupport) {
		if (JoinMessageEnabled) {
		    if (!plugin.authmeapi) {
			return;
		    }
		    if (plugin.placeholderapi) {
			Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), JoinMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
		    } else {
			Bukkit.broadcastMessage(JoinMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName()));
		    }
		}
	    }
	}
    }

}
