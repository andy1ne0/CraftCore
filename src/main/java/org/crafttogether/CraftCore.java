package org.crafttogether;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.crafttogether.listeners.LoginListener;

public final class CraftCore extends JavaPlugin {

    private static CraftCore instance;

    public static CraftCore getInstance() {
        return instance;
    }

    private Connection databaseConnection;

    @Override
    public void onEnable() {
        instance=this;

        this.saveDefaultConfig();

        this.databaseConnection = RethinkDB.r.connection()
                .hostname(this.getConfig().getString("database.hostname"))
                .port(this.getConfig().getInt("database.port"))
                .user(this.getConfig().getString("username"), this.getConfig().getString("database.password"))
                .connect();

        for(Listener listener : new Listener[]{
                new LoginListener()
        }) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    @Override
    public void onDisable() {
        // TODO: Plugin shutdown logic
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }
}
