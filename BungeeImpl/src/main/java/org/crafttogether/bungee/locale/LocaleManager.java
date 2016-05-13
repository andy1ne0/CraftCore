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
 * All contents of this file, except where specified elsewhere, is the copyrighted property of Andrew Petersen.
 * Any works derived from this must only be done so if permission is given beforehand by the author (Andrew Petersen).
 * Failure to receive proper permission to use the works provided will result in termination of any agreements between the third party and Andrew Petersen.
 * If permission to use the contents of this file are granted to you, the client party, you must not take credit and/or claim these works as your own.
 * Doing so will void any contracts, or permission given, by Andrew Petersen, unless notified otherwise.
 * This file is provided as-is, with absolutely no warranty provided. By using this file, you understand this
 * - regardless of if you have been warned or not prior to using this file, any liabilities or harm caused by this file is your problem.
 * If this file is available for public download, by intention of the author, derirative works without the consent of Andrew Petersen are allowed.
 * However, once again, you must give credit to the author of the code.
 * If this has been downloaded or distributed without the permission of the author, all permissions granted above are voided.
 * It is also required that in these circumstances, you cease to use the file/contents.
 * Last edited: 13/05/2016 (dd/mm/yy), 07:26
 * Package Name: org.crafttogether.bungee.locale
 * Project Name: CraftCore
 *
 * @author Andrew Petersen
 */
public class LocaleManager implements ILocaleManager {

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
