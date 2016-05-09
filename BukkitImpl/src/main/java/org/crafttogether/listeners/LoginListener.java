package org.crafttogether.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.crafttogether.CraftCore;
import org.crafttogether.user.User;

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
        CraftCore.getInstance().getUserManager().getUser(event.getUniqueId());
    }

    /**
     * Handles join event.
     * @param event event.
     */
    @EventHandler
    public void on(PlayerJoinEvent event) {
        User user = CraftCore.getInstance().getUserManager().getUser(event.getPlayer());
        user.setPlayer(event.getPlayer());
        user.setLastIGN(event.getPlayer().getName());
    }

    /**
     * Quit Handler
     * @param event event
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        User player = CraftCore.getInstance().getUserManager().getUser(event.getPlayer());
        CraftCore.getInstance().getUserManager().removeUser(player);
    }

}
