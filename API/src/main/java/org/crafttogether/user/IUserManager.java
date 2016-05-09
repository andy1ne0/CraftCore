package org.crafttogether.user;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Erik Rosemberg
 * @since 07.05.2016
 */
public interface IUserManager {

    /**
     * Gets a user from that current objects
     * @param player {@link Player}
     * @return {@link User}
     */
    User getUser(Player player);

    /**
     * Gets a user from that current objects
     * @param uuid player's {@link UUID}
     * @return {@link User}
     */
    User getUser(UUID uuid);

    /**
     * @return An unmodifiable list of players
     */
    Collection<User> getUsers();

    /**
     * Removes a player from the lists.
     * @param player {@link User}
     */
    void removeUser(User player);

}
