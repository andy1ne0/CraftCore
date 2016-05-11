package org.crafttogether.permissions;


import java.util.Collection;

/**
 * @author Mislav
 * @since 5/11/2016
 */
public interface PermissionGroup extends PermissionHolder {

    /**
     * @return Group name
     */
    String getName();

    /**
     * Adds a group to the list of inheritance
     * @param permissionGroup Group to add
     */
    void addInheritingGroup(PermissionGroup permissionGroup);

    /**
     * Removes a group from the list of inheritance
     * @param permissionGroup Group to remove
     */
    void removeInheritingGroup(String permissionGroup);

    /**
     * @return Collection of inheriting groups
     */
    Collection<PermissionGroup> getInheritingGroups();
}
