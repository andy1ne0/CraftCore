package org.crafttogether.moderation;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.crafttogether.players.PlayerManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Created by dan on 5/5/16.
 */
public class CmdBan extends Command {
    public CmdBan(String name) {
        super(name);
    }

    private final String USAGE = ChatColor.RED + "/ban <player> <duration><m,h,d,y>";

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        boolean staff;
        if(commandSender instanceof ConsoleCommandSender){
            staff = true;
        } else {
            staff = PlayerManager.getPlayer((Player)commandSender).get().isStaff();
        }

        if(staff){
            if(args.length != 2){
                commandSender.sendMessage(USAGE);
                return true;
            }
            if(StringUtils.isNumeric(args[1].substring(0, args[1].length()-1))){
                int length = Integer.parseInt(args[1].substring(0, args[1].length()-1));
                Duration duration;
                switch(args[1].charAt(args[1].length()-1)){
                    case 'y':
                        duration = Duration.of(length, ChronoUnit.YEARS);
                        break;
                    case 'm':
                        duration = Duration.of(length, ChronoUnit.MINUTES);
                        break;
                    case 'h':
                        duration = Duration.of(length, ChronoUnit.HOURS);
                        break;
                    case 's':
                        duration = Duration.of(length, ChronoUnit.SECONDS);
                        break;
                    default:
                        duration = Duration.of(length, ChronoUnit.HOURS);
                        break;
                }
                if(commandSender instanceof Player){
                    new Punishment(PunishmentType.BAN, ((Player)commandSender).getUniqueId(), UUID.randomUUID(), duration, false, LocalDateTime.now());
                } else {
                    new Punishment(PunishmentType.BAN, null, null, duration, false, LocalDateTime.now());
                }
                return true;
            }
            commandSender.sendMessage(USAGE);
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You are not staff.");
        return true;
    }
}
