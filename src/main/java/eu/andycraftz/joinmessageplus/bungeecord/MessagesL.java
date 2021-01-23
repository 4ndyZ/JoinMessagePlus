package eu.andycraftz.joinmessageplus.bungeecord;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @category Bungee Plugin
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

        JoinMessage = this.plugin.cfg.getConfig().getString("GlobalJoinMessage.Message").replace("&", "§");
        QuitMessage = this.plugin.cfg.getConfig().getString("GlobalQuitMessage.Message").replace("&", "§");

        JoinMessageEnabled = this.plugin.cfg.getConfig().getBoolean("GlobalJoinMessage.Enabled");
        QuitMessageEnabled = this.plugin.cfg.getConfig().getBoolean("GlobalQuitMessage.Enabled");
    }

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        if (JoinMessageEnabled) {
            plugin.getProxy().broadcast(new TextComponent(JoinMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
        }
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        if (QuitMessageEnabled) {
            plugin.getProxy().broadcast(new TextComponent(QuitMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
        }
    }
}
