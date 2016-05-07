package org.crafttogether.players;

import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author theminecoder
 * @version 1.0
 */
public final class PlayerManager {

    private static final List<CTPlayer> players = new ArrayList<>();
    private static final Lock playersLock = new ReentrantLock();

    /**
     * Blank
     */
    private PlayerManager(){
    }

    /**
     * @return An unmodifiable list of players
     */
    public static List<CTPlayer> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Gets a player from that current objects
     * @param player {@link Player}
     * @return {@link Optional} of {@link CTPlayer}
     */
    public static Optional<CTPlayer> getPlayer(Player player) {
        return PlayerManager.getPlayer(player.getUniqueId());
    }

    /**
     * Gets a player from the current player objects
     * @param uuid {@link Player}'s UUID
     * @return {@link Optional} of {@link CTPlayer}
     */
    public static Optional<CTPlayer> getPlayer(UUID uuid) {
        playersLock.lock();
        Optional<CTPlayer> ctPlayer;
        try {
            ctPlayer = players.stream().filter(player -> player.getId().equals(uuid)).findFirst();
        } finally {
            playersLock.unlock();
        }

        return ctPlayer;
    }

    /**
     * Adds {@link CTPlayer} to players list
     * @param player to add
     */
    public static void addPlayer(CTPlayer player) {
        playersLock.lock();
        try {
            players.add(player);
        } finally {
            playersLock.unlock();
        }
    }

    /**
     * Removes {@link CTPlayer} from players list
     * @param player to remove
     */
    public static void removePlayer(CTPlayer player) {
        playersLock.lock();
        try {
            players.remove(player);
        } finally {
            playersLock.unlock();
        }
    }



}
