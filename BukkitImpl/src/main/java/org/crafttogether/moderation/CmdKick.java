package org.crafttogether.moderation;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.crafttogether.CraftCore;
import org.crafttogether.user.PunishmentType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author dan
 */
public class CmdKick extends Command {
    private static final String USAGE = ChatColor.RED + "/kick <player>";

    /**
     * @param name command name
     */
    public CmdKick(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        boolean staff;
        if(commandSender instanceof ConsoleCommandSender){
            staff = true;
        } else {
            staff = CraftCore.getInstance().getUserManager().getUser((Player)commandSender).getRank().isStaff();
        }

        if(staff){
            if(args.length < 1){
                commandSender.sendMessage(USAGE);
                return true;
            }
            if(!Bukkit.getPlayer(args[0]).isOnline()){
                commandSender.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }
            String reason = StringUtils.join(args, " ");
            reason = reason.replaceFirst(args[0] + " ", "");
            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "Kicked by " + commandSender.getName() + " for " + reason);
            commandSender.sendMessage(ChatColor.GREEN + "Kicked " + args[0]);
            if(commandSender instanceof Player){
                new CTPunishment(PunishmentType.KICK, ((Player)commandSender).getUniqueId(), UUID.randomUUID(), Duration.ZERO, true, LocalDateTime.now());
            } else {
                new CTPunishment(PunishmentType.KICK, null, null, Duration.ZERO, true, LocalDateTime.now());
            }
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You are not staff.");
        return true;
    }
}
