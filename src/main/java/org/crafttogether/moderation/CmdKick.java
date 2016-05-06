package org.crafttogether.moderation;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.crafttogether.players.PlayerManager;

/**
 * Created by dan on 5/5/16.
 */
public class CmdKick extends Command {
    public CmdKick(String name) {
        super(name);
    }

    private final String USAGE = ChatColor.RED + "/kick <player>";

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        boolean staff;
        if(commandSender instanceof ConsoleCommandSender){
            staff = true;
        } else {
            staff = PlayerManager.getPlayer((Player)commandSender).get().isStaff();
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
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You are not staff.");
        return true;
    }
}
