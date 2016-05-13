package org.crafttogether.user;

import org.crafttogether.permissions.PermissionGroup;
import org.crafttogether.util.StringCosmetic;

/**
 * {@linkplain User} ranks
 *
 * @author theminecoder
 * @version 1.0
 */
public enum CTRank {

    PLAYER("Player", "", 0, false, null),
    STAFF("Staff", "", 1, true, null);

    private String name;
    private String prefix;
    private int permissionLevel;
    private PermissionGroup permissionGroup;
    private boolean staff;

    /**
     * Constructs a new rank.
     *
     * @param name Rank display name
     * @param prefix Chat and Tag prefix
     * @param permissionLevel Comparable permission level
     */
    CTRank(String name, String prefix, int permissionLevel, boolean staff, PermissionGroup permissionGroup) {
        this.name = name;
        this.prefix = prefix;
        this.permissionLevel = permissionLevel;
        this.staff = staff;
        this.permissionGroup = permissionGroup;
    }

    /**
     * @return Rank display name
     */
    public String getName(StringCosmetic... stringCosmetics) {
        String res = this.name;
        for (StringCosmetic current : stringCosmetics) {
            if (current.getName().equals("original")) {
                res = this.name;
                break;
            }
            res = current.apply(res);
        }
        return res;
    }

    /**
     * @return Rank prefix
     */
    public String getPrefix(StringCosmetic... stringCosmetics) {
        String res = this.prefix;
        for (StringCosmetic current : stringCosmetics) {
            if (current.getName().equals("original")) {
                res = this.prefix;
                break;
            }
            res = current.apply(res);
        }
        return res;
    }


    /**
     * @return Rank permission level
     */
    public int getPermissionLevel() {
        return this.permissionLevel;
    }

    public PermissionGroup getPermissionGroup() {
        return this.permissionGroup;
    }

    /**
     * @return If the rank is staff
     */
    public boolean isStaff() {
        return this.staff;
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
