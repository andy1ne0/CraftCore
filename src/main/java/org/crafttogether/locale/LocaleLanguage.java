package org.crafttogether.locale;

/**
 * Locale object, used to store translations for an individual string.
 * @author Andrew Petersen
 * @version 0.1
 */

public enum LocaleLanguage {

    ENGLISH(0), GERMAN(1), FRENCH(2), PIRATE(3); // Register more languages as needed.

    private final int id;

    /**
     * The enum constructor..
     * @param i The ID for the said language.
     */
    LocaleLanguage(int i){
        this.id = i;
    }

    /**
     * Get the Language ID. Should be used for database storage.
     * @return The Language ID.
     */
    public int getId(){
        return this.id;
    }

}
