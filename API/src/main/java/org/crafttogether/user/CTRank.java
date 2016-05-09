package org.crafttogether.user;

/**
 * {@linkplain User} ranks
 *
 * @author theminecoder
 * @version 1.0
 */
public enum CTRank {

    PLAYER("Player", "", 0);

    private String name;
    private String prefix;
    private int permissionLevel;

    /**
     * Constructs a new rank.
     *
     * @param name Rank display name
     * @param prefix Chat and Tag prefix
     * @param permissionLevel Comparable permission level
     */
    CTRank(String name, String prefix, int permissionLevel) {
        this.name = name;
        this.prefix = prefix;
        this.permissionLevel = permissionLevel;
    }

    /**
     * @return Rank display name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Rank prefix
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * @return Rank permission level
     */
    public int getPermissionLevel() {
        return this.permissionLevel;
    }

    /**
     * Checks if rank can access rank
     * @param rank {@link CTRank}
     * @return boolean.
     */
    public boolean has(CTRank rank) {
        return this.compareTo(rank) >= 0;
    }
}
