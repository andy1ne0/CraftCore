package org.crafttogether.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.crafttogether.CraftCore;
import org.crafttogether.user.PunishmentType;
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
        User user = CraftCore.getInstance().getUserManager().getUser(event.getUniqueId());
        if(user.getPunishments().stream().anyMatch(p -> p.getType() == PunishmentType.BAN)){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, ChatColor.RED + "You are banned. Remaining time: "
                    + user.getActivePunishments().stream().filter(p -> p.getType() == PunishmentType.BAN).findAny().get().getRemainingTimeString());
        }
    }

    /**
     * Handles join event.
     * @param event event.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
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
