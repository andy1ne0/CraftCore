package org.crafttogether.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.crafttogether.players.CTPlayer;
import org.crafttogether.players.PlayerManager;

/**
 * @author Jackson
 * @version 1.0
 */
public class LoginListener implements Listener {

    /**
     * Pre-Login Handler
     * @param event event
     */
    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        CTPlayer player = new CTPlayer(event.getUniqueId(), event.getName());
        PlayerManager.addPlayer(player);
    }

    /**
     * Quit Handler
     * @param event event
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CTPlayer player = PlayerManager.getPlayer(event.getPlayer()).orElseThrow(() -> new RuntimeException("Player must be loaded to be able to run logout actions"));
        PlayerManager.removePlayer(player);
    }

}
