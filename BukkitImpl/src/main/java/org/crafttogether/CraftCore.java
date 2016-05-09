package org.crafttogether;

import com.google.common.collect.ImmutableList;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.crafttogether.listeners.LoginListener;
import org.crafttogether.players.UserManager;
import org.crafttogether.user.IUserManager;

public final class CraftCore extends JavaPlugin {

    private Connection databaseConnection;
    private IUserManager userManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.databaseConnection = RethinkDB.r.connection()
                .hostname(this.getConfig().getString("database.hostname"))
                .port(this.getConfig().getInt("database.port"))
                .user(this.getConfig().getString("database.username"), this.getConfig().getString("database.password"))
                .connect();

        this.userManager = new UserManager();

        ImmutableList<Listener> listeners = ImmutableList.of(new LoginListener());
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    @Override
    public void onDisable() {
        if (this.databaseConnection != null && this.databaseConnection.isOpen()) {
            this.databaseConnection.close();
        }
    }

    /**
     * @return Plugin instance
     */
    public static CraftCore getInstance() {
        return JavaPlugin.getPlugin(CraftCore.class);
    }

    /**
     * @return RethinkDB database connection
     */
    public Connection getDatabaseConnection() {
        return this.databaseConnection;
    }

    /**
     * @return {@link UserManager} instance.
     */
    public IUserManager getUserManager() {
        return this.userManager;
    }
}
