package org.crafttogether.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.crafttogether.CraftCore;
import org.crafttogether.user.PunishmentType;

/**
 * @author dan
 */
public class ModerationListener implements Listener {

    /**
     * handles chat events
     * @param event event
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(CraftCore.getInstance().getUserManager().getUser(event.getPlayer()).getPunishments().stream().anyMatch(p -> p.getType() == PunishmentType.KICK)){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You are muted! Remaining time: " +
                    CraftCore.getInstance().getUserManager().getUser(event.getPlayer()).getActivePunishments().stream().filter(p -> p.getType() == PunishmentType.MUTE).findAny().get().getRemainingTimeString());
        }
    }
}
