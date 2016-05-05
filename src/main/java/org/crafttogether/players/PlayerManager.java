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

    private PlayerManager(){
    }

    public static ArrayList<CTPlayer> getPlayers() {
        return players;
    }

    public static Optional<CTPlayer> getPlayer(Player player) {
        return PlayerManager.getPlayer(player.getUniqueId());
    }

    public static Optional<CTPlayer> getPlayer(UUID uuid) {
        return players.stream().filter(player -> player.getId().equals(uuid)).findFirst();
    }

}
