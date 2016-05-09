package org.crafttogether.user;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Erik Rosemberg
 * @since 07.05.2016
 */
public interface User {

    /**
     * Gets the global unique identifier of the user.
     * @return User's uuid.
     */
    UUID getId();

    /**
     * Gets the last known username of the user.
     * @return User's last/current ign.
     */
    String getLastIGN();

    /**
     * Sets the ign of the player to be stored.
     * @param ign Current ign.
     */
    void setLastIGN(String ign);

    /**
     * Gets the {@link Player} for the User.
     * @return {@link Player}
     */
    Player getPlayer();

    /**
     * Sets the player's reference.
     * @param player Player to be set.
     */
    void setPlayer(Player player);

    /**
     * Gets the {@link CTRank} of the User.
     * @return {@link CTRank}
     */
    CTRank getRank();

    /**
     * Sets the rank of the user.
     * @param rank the rank to be set.
     */
    void setRank(CTRank rank);

}
