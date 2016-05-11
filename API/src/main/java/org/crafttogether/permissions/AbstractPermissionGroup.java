package org.crafttogether.permissions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Mislav
 * @since 5/9/2016
 */
public abstract class AbstractPermissionGroup implements PermissionGroup {

    private String name;
    private Set<String> permissions;
    private Map<String, PermissionGroup> inheritingGroups;

    /**
     * @param name Group name
     * @param inheritingGroups Groups to inherit
     */
    public AbstractPermissionGroup(String name, PermissionGroup... inheritingGroups) {
        this.name = name;
        this.permissions = Sets.newConcurrentHashSet();
        this.inheritingGroups = Maps.newHashMap();
        if (inheritingGroups != null) {
            Arrays.stream(inheritingGroups).forEach(group -> this.inheritingGroups.put(group.getName(), group));
        }
        this.updatePermissions();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addPermission(String permission) {
        this.permissions.add(permission.toLowerCase());
    }

    @Override
    public void removePermission(String permission) {
        this.permissions.remove(permission.toLowerCase());
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.permissions.contains(permission.toLowerCase());
    }

    @Override
    public Collection<String> getPermissions() {
        return ImmutableList.copyOf(this.permissions);
    }

    @Override
    public void addInheritingGroup(PermissionGroup permissionGroup) {
        this.inheritingGroups.put(permissionGroup.getName(), permissionGroup);
        this.updatePermissions();
    }

    @Override
    public Set<PermissionGroup> getInheritingGroups() {
        return ImmutableSet.copyOf(this.inheritingGroups.values());
    }

    /**
     * Updates local and inheriting permissions
     */
    public final void updatePermissions() {
        if (this.inheritingGroups != null && this.inheritingGroups.size() > 0) {
            this.inheritingGroups.values().forEach(permissionGroup ->
                    permissionGroup.getPermissions().forEach(this.permissions::add)
            );
        }
    }

}
