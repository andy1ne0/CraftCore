package org.crafttogether.permissions;

import java.util.Collection;

/**
 * @author Mislav
 * @since 5/9/2016
 */
public interface PermissionHolder {

    /**
     * Adds a permission node to the {@link PermissionHolder}
     * @param permission Permission to add
     */
    void addPermission(String permission);

    /**
     * Removes a permission node from the {@link PermissionHolder}
     * @param permission Permission to remove
     */
    void removePermission(String permission);

    /**
     * Checks if the {@link PermissionHolder} has a permission
     * @param permission Permission to check
     * @return boolean
     */
    boolean hasPermission(String permission);

    /**
     * @return Collection of permission nodes associated with the {@link PermissionHolder}
     */
    Collection<String> getPermissions();
}
