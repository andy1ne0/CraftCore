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

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        CTPlayer player = new CTPlayer(event.getUniqueId(), event.getName());
        PlayerManager.getPlayers().add(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CTPlayer player = PlayerManager.getPlayer(event.getPlayer()).orElseThrow(() -> new RuntimeException("Player must be loaded to be able to run logout actions"));
        PlayerManager.getPlayers().remove(player);
    }

}
