package org.crafttogether.moderation;

import com.evilmidget38.NameFetcher;
import com.evilmidget38.UUIDFetcher;
import com.google.common.collect.Lists;
import com.rethinkdb.net.Cursor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.crafttogether.CraftCore;
import org.crafttogether.players.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.rethinkdb.RethinkDB.r;

/**
 * Created by dan on 5/7/16.
 */
public class CmdBanData extends Command {
    public CmdBanData(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        boolean staff;
        if(commandSender instanceof ConsoleCommandSender){
            staff = true;
        } else {
            staff = PlayerManager.getPlayer((Player)commandSender).get().isStaff();
        }
        if(staff){
            if(args.length != 0){
                String player = args[0];
                ArrayList<String> players = Lists.newArrayList();
                players.add(player);
                UUID playerId;
                try{
                    playerId = new UUIDFetcher(players).call().get(player);
                } catch (Exception e){
                    e.printStackTrace();
                    commandSender.sendMessage(ChatColor.RED + "Could not load ban data.");
                    return true;
                }
                Cursor cursor = r.table("moderation").filter(row -> row.g("player").eq(playerId.toString())).run(CraftCore.getInstance().getDatabaseConnection());
                List<Punishment> punishments = Lists.newArrayList();
                cursor.forEach(p -> punishments.add((Punishment)p));
                commandSender.sendMessage(ChatColor.GOLD + "BanData for " + args[0] + ":");
                punishments.forEach(punishment -> {
                    ArrayList<UUID> ids = Lists.newArrayList();
                    ids.add(punishment.getPunisher());
                    try {
                        commandSender.sendMessage(ChatColor.GOLD + punishment.getType().toString() + " by " + ((punishment.getPunisher() == null) ? "CONSOLE" : new NameFetcher(ids).call().get(punishment.getPunisher())));
                        commandSender.sendMessage(ChatColor.GOLD + "    DURATION: " + punishment.getDurationString());
                        commandSender.sendMessage(ChatColor.GOLD + "    CREATED ON: " + punishment.getCreationDate().toString());
                        commandSender.sendMessage(ChatColor.GOLD + "    ENDS ON: " + punishment.getEndDate().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        commandSender.sendMessage(ChatColor.RED + "Could not load ban data.");
                    }
                });
                return true;
            }
            commandSender.sendMessage(ChatColor.RED + "Do /bandata <player>");
            return true;
        }
        commandSender.sendMessage(ChatColor.RED + "You must be staff!");
        return true;
    }
}
