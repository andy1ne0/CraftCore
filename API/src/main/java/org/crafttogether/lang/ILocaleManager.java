package org.crafttogether.lang;

import com.sun.istack.internal.NotNull;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Andrew Petersen
 * @version 0.1
 */
public interface ILocaleManager {

    /**
     * Register a ResourceBundle.
     * @param bundle The bundle to register.
     */
    void registerBundle(ResourceBundle bundle);

    /**
     * Get the translated version of a string.
     * @param refName The key for the term you're looking for in the resource files.
     * @param locale The lang you need, defaults down to English if not available.
     * @param fallback The string to return, should no valid response be found.
     * @return The translated string - will either be in the requested language, or English.
     */
    String getTranslation(String refName, @NotNull Locale locale, String fallback);

}
