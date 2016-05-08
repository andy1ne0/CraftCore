package org.crafttogether.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.crafttogether.moderation.PunishmentType;
import org.crafttogether.players.PlayerManager;

/**
 * Created by dan on 5/5/16.
 */
public class ModerationListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        PlayerManager.getPlayer(event.getPlayer()).ifPresent(ctPlayer -> {
            ctPlayer.getPunishments().stream().filter(punishment -> punishment.getType() == PunishmentType.MUTE && !punishment.isExpired()).findAny().ifPresent(punishment -> {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You have been muted for " + punishment.getDuration().toHours() + " hours!");
            });
        });
    }
}
