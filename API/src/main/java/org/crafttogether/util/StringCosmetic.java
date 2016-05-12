package org.crafttogether.util;

import org.bukkit.ChatColor;

import java.util.function.Function;

/**
 * @author Mislav
 * @since 5/12/2016
 */
public class StringCosmetic {
    /**
     * No purpose on its own. Check {@link org.crafttogether.user.CTRank#getName(StringCosmetic...)} for an example usage.
     */
    public static final StringCosmetic ORIGINAL = new StringCosmetic("original", s -> s);
    /**
     * Makes the input String bold.
     */
    public static final StringCosmetic BOLD = new StringCosmetic("bold", s -> ChatColor.BOLD + s);
    /**
     * Makes the input String cursive.
     */
    public static final StringCosmetic CURSIVE = new StringCosmetic("cursive", s -> ChatColor.ITALIC + s);
    /**
     * Makes the input String strikethrough.
     */
    public static final StringCosmetic STRIKETHROUGH = new StringCosmetic("strikethrough", s -> net.md_5.bungee.api.ChatColor.STRIKETHROUGH + s);
    /**
     * Makes the input String uppercase.
     */
    public static final StringCosmetic UPPERCASE = new StringCosmetic("uppercase", String::toUpperCase);
    /**
     * Makes the input String lowercase
     */
    public static final StringCosmetic LOWERCASE = new StringCosmetic("lowercase", String::toLowerCase);

    private String name;
    private Function<String, String> function;

    /**
     * @param name Cosmetic name
     * @param function Function to execute
     */
    public StringCosmetic(String name, Function<String, String> function) {
        this.name = name;
        this.function = function;
    }

    /**
     * @return Cosmetic name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Applies the effect to the input String
     *
     * @param input Effecting String
     * @return String result
     */
    public String apply(String input) {
        return this.function.apply(input);
    }
}
