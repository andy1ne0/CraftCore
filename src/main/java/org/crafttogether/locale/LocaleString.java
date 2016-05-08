package org.crafttogether.locale;

import java.util.HashMap;

/**
 * Locale object, used to store translations for an individual string.
 * @author Andrew Petersen
 * @version 0.1
*/

public abstract class LocaleString {

    private HashMap<LocaleLanguage, String> translations = new HashMap<>();

    /**
     * Get the English translation of the string.
     * @return The English translation.
     */
    public String getEnglishString() {
        return this.translations.get(LocaleLanguage.ENGLISH);
    }

    /**
     * The English string is required in the constructor as the default, should no other options be available.
     * This should be a constant.
     * @param englishString The English translation of the string - the default if a translation is not provided.
     */
    public LocaleString(String englishString){
        this.translations.put(LocaleLanguage.ENGLISH, englishString);
    }

    /**
     * This method is called in the constructor.
     * Register any translations here, by calling the {@link #registerTranslation}.
     * {@code this.registerTranslation(LocaleLanguage.PIRATE, "Ahoy, seven seas!"); }
     */
    public abstract void registerAllTranslations();

    /**
     * @param lang The language of the new translation.
     * @param translated The string to assign to the said language.
     * @return Whether the translation was successfully registered or not.
     */
    public boolean registerTranslation(LocaleLanguage lang, String translated){
        if(this.translations.keySet().contains(lang)){
            return false;
        } else {
            this.translations.put(lang, translated);
            return true;
        }
    }

    /**
     * Get the translation of this string.
     * @param lang The language of the translation to return.
     * @return The translated string. Defaults to English if the specified language is not registered.
     */
    public String getTranslation(LocaleLanguage lang){
        if(this.translations.containsKey(lang)) {
            return this.translations.get(lang);
        } else {
            return this.translations.get(LocaleLanguage.ENGLISH);
        }
    }

}
