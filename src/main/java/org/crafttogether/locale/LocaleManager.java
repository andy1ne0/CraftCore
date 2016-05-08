package org.crafttogether.locale;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Locale manager, used to get the translated version of a string or register a new translation object.
 * @author Andrew Petersen
 * @version 0.1
 */

public final class LocaleManager {

    private static final ArrayList<LocaleString> localeStrings = new ArrayList<>();
    private static final Lock localeLock = new ReentrantLock();

    /**
     * Private constructor - it's a static utility class, doesn't need to be instantiated.
     */
    private LocaleManager(){}

    /**
     * @param locale The LocaleString object you wish to register.
     * @return Whether or not the locale was already registered.
     */
    public static void registerNewLocale(LocaleString locale){
        localeLock.lock();
        try {
            localeStrings.add(locale);
        } finally {
            localeLock.unlock();
        }

    }

    /**
     * Get the translation of an English string (should be constant..)
     * @param englishString The English version of the String.
     * @param lang The language needed.
     * @return The translated string. Returns the English string if no translation is available.
     */
    public static String getTranslation(String englishString, LocaleLanguage lang){
        localeLock.lock();
        String toReturn = englishString;
        try {
            if(localeStrings.stream().filter(s -> s.getEnglishString().equals(englishString)).findFirst().isPresent()){
                toReturn = localeStrings.stream().filter
                        (s -> s.getEnglishString().equals(englishString)).findFirst().get().getTranslation(lang);
            }
        } finally {
            localeLock.unlock();
        }
        return toReturn;
    }

    /**
     * Register new language translations here. This should be completed in the LocaleString class.
     * This method is only here if translations must be dynamically registered. Avoid this method when possible.
     * @param englishTranslation The English translation of the string.
     * @param lang The language of the translation being registered.
     * @param translatedString The translated string.
     * @see org.crafttogether.locale.LocaleString#registerTranslation(LocaleLanguage, String)
     * @deprecated Only use if translations must be dynamically added - otherwise, avoid.
     */
    @Deprecated
    public static void registerNewTranslation(String englishTranslation, LocaleLanguage lang, String translatedString){
        localeLock.lock();
        try {
            if(localeStrings.stream().filter
                    (s -> s.getEnglishString().equals(englishTranslation)).findFirst().isPresent()){
                localeStrings.stream().filter(s -> s.getEnglishString().equals(englishTranslation)).findFirst().get()
                        .registerTranslation(lang, translatedString);
            }
        } finally {
            localeLock.unlock();
        }

    }

    /**
     * @param englishString The English version of the string - should be a constant.
     * @return The LocaleString instance, should it be registered.
     */
    public static Optional<LocaleString> getLocale(String englishString){
        localeLock.lock();
        Optional<LocaleString> toReturn;
        try {
            toReturn = localeStrings.stream().filter(ls -> ls.getEnglishString().equals(englishString)).findFirst();
        } finally {
            localeLock.unlock();
        }
        return toReturn;
    }

    /**
     * @param englishVersion The English translation of the LocaleString, should be a constant field.
     * @return Whether or not a LocaleString matching the English translation has been registered.
     */
    public static boolean isLocaleRegistered(String englishVersion){
        localeLock.lock();
        boolean isRegistered = false;
        try {
            isRegistered = localeStrings.stream().filter
                    (s -> s.getEnglishString().equals(englishVersion)).findFirst().isPresent();
        } finally {
            localeLock.unlock();
        }
        return isRegistered;

    }


}
