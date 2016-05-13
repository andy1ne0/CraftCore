package org.crafttogether.user;

import org.bukkit.entity.Player;
import org.crafttogether.permissions.PermissionHolder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author Erik Rosemberg
 * @since 07.05.2016
 */
public interface User extends PermissionHolder {

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
     * Gets the previous usernames of the user.
     * @return Collection of users previous used names on the network.
     */
    Collection<String> getPreviousIGNs();

    /**
     * Sets the previous usernames of the user.
     * @param igns Collection of strings to set.
     */
    void setPreviousIGNs(Collection<String> igns);

    /**
     * Sets the previous usernames of the user.
     * @param igns Array of strings to set.
     */
    void setPreviousIGNs(String... igns);

    /**
     * Adds a name to the list of previous usernames.
     * @param igns Collection of strings to add.
     */
    void addPreviousIGNs(Collection<String> igns);

    /**
     * Adds a name to the list of previous usernames.
     * @param igns Array of strings to add.
     */
    void addPreviousIGNs(String... igns);

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

    /**
     * Gets a list of {@link Punishment}s the user has
     * @return {@link List} of punishments
     */
    List<Punishment> getPunishments();

    /**
     * @return a {@link List} of active punishments (not expired)
     */
    List<Punishment> getActivePunishments();

    /**
     * Send the player a localized message.
     * @param refName The key of the string -  used to look for the string in the key-set ResourceBundles.
     * @param fallback The string to return, should no appropriate translation be found.
     */
    void sendLocalizedMessage(String refName, String fallback);

}
