package org.crafttogether.players;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * @author theminecoder
 * @version 1.0
 */
public final class PlayerManager {

    private static final ArrayList<CTPlayer> players = new ArrayList<>();

    /**
     * Blank
     */
    private PlayerManager(){
    }

    /**
     * @return The current player objects
     */
    public static ArrayList<CTPlayer> getPlayers() {
        return players;
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
        return players.stream().filter(player -> player.getId().equals(uuid)).findFirst();
    }

}
