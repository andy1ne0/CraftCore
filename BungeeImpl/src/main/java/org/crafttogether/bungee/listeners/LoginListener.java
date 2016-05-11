package org.crafttogether.bungee.listeners;

import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by dan on 5/10/16.
 */
public class LoginListener implements Listener {

    /**
     * Ping event
     * @param event event
     */
    @EventHandler
    public void onPing(ProxyPingEvent event){
        event.getResponse().setDescription("CraftTogether"); //use database
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event){
        //Check database if banned
    }
}
