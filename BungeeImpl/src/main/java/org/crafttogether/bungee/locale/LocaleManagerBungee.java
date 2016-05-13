package org.crafttogether.bungee.locale;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.crafttogether.bungee.CraftBungee;
import org.crafttogether.lang.ILocaleManager;

import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Andrew Petersen
 * @version 0.1
 */
public class LocaleManagerBungee implements ILocaleManager {

    private Set<ResourceBundle> resources = new HashSet<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void registerBundle(ResourceBundle bundle){

        this.lock.writeLock().lock();
        try {
            this.resources.add(bundle);
        } finally {
            this.lock.writeLock().unlock();
        }

    }

    @Override
    public String getTranslation(String refName, Locale locale, String fallback) {
        this.lock.readLock().lock();
        String toReturn = fallback;
        boolean found = false;
        boolean engFallbackLocated = false;
        String engFallback = fallback;
        try {
            for(ResourceBundle r : this.resources){
                if(r.containsKey(refName)){
                    if(r.getLocale().getLanguage().equals(locale.getLanguage())){
                        found = true;
                        toReturn = r.getString(refName);
                    } else if(r.getLocale().getLanguage().equalsIgnoreCase("en")){
                        engFallbackLocated = true;
                        engFallback = r.getString(refName);
                    }
                }
            }
            if(!found && engFallbackLocated){
                toReturn = engFallback;
                found = true;
            }
            if(!found){
                CraftBungee.getInstance().getLogger().warning("A translation was requested, but could not be found. ");
                CraftBungee.getInstance().getLogger().warning("Fallback String: \""+fallback+"\"");
                CraftBungee.getInstance().getLogger().warning("Locale requested: \""+locale+"\"");
                CraftBungee.getInstance().getLogger().warning("Requested from: "+Thread.currentThread().getStackTrace()[2]);
            }
        } finally {
            this.lock.readLock().unlock();
        }
        return toReturn;
    }

    /**
     * Returns the localized version of the specified message for the player.
     * This method simply exists for convenience - {@link this#getTranslation(String, Locale, String)} should be used instead.
     * @param refName The key to look for in the locale files.
     * @param pl The target player for the message - to get their locale.
     * @param fallback The string to return, should no corresponding translation be found.
     * @return The translation - either in the requested locale, or in English.
     */
    public String getTranslation(String refName, ProxiedPlayer pl, String fallback){
        return this.getTranslation(refName, pl.getLocale(), fallback);
    }

}
