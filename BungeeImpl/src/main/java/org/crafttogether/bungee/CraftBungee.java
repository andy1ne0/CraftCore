package org.crafttogether.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import org.crafttogether.bungee.locale.LocaleManagerBungee;

/**
 * Created by dan on 5/10/16.
 */
public class CraftBungee extends Plugin {

    private static CraftBungee instance;
    private LocaleManagerBungee localeManager;

    @Override
    public void onEnable(){
        instance = this;
        this.localeManager = new LocaleManagerBungee();
        //enable stuff here
    }

    /**
     * Get the CraftBungee instance.
     * @return The CraftBungee instance.
     */
    public static CraftBungee getInstance(){
        return CraftBungee.instance;
    }

    /**
     * Get the Locale Manager instance.
     * @return The Locale Manager instance.
     */
    public LocaleManagerBungee getLocaleManager(){
        return this.localeManager;
    }
}
