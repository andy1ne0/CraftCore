package org.crafttogether.players;

/**
 * {@linkplain CTPlayer} ranks
 *
 * @author theminecoder
 * @version 1.0
 */
public enum CTRank {

    PLAYER("Player", "", 0);

    private String name;
    private String prefix;
    private int permissionLevel;

    CTRank(String name, String prefix, int permissionLevel) {
        this.name = name;
        this.prefix = prefix;
        this.permissionLevel = permissionLevel;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
