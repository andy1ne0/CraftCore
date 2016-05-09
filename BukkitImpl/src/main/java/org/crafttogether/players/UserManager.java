package org.crafttogether.players;

import org.bukkit.entity.Player;
import org.crafttogether.user.IUserManager;
import org.crafttogether.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author theminecoder
 * @author Erik Rosemberg
 * @version 1.0
 */
public final class UserManager implements IUserManager {

    private final List<User> players = new ArrayList<>();
    private final ReadWriteLock playersLock = new ReentrantReadWriteLock();

    @Override
    public Collection<User> getUsers() {
        return Collections.unmodifiableList(this.players);
    }

    @Override
    public void removeUser(User player) {
        this.playersLock.writeLock().lock();
        try {
            this.players.remove(player);
        } finally {
            this.playersLock.writeLock().unlock();
        }
    }

    @Override
    public User getUser(Player player) {
        return this.getUser(player.getUniqueId());
    }

    @Override
    public User getUser(UUID uuid) {
        this.playersLock.readLock().lock();
        User ctPlayer;
        try {
            ctPlayer = this.players.stream().filter(player -> player.getId().equals(uuid)).findFirst().get();

            if (ctPlayer == null) {
                ctPlayer = new CTUser(uuid, null);

                this.players.add(ctPlayer);
            }
        } finally {
            this.playersLock.readLock().unlock();
        }

        return ctPlayer;
    }
}
