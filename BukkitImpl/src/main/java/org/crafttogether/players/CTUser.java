package org.crafttogether.players;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.crafttogether.CraftCore;
import org.crafttogether.user.CTRank;
import org.crafttogether.user.Punishment;
import org.crafttogether.user.User;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Network wide user data.
 *
 * @author theminecoder
 * @version 1.0
 */
public class CTUser implements User {

    private UUID id;
    private String currentName;
    private List<String> previousNames;
    private PermissionAttachment permissionAttachment;

    private WeakReference<Player> reference;

    private CTRank rank = CTRank.PLAYER;

    private List<Punishment> punishments;

    /**
     * Constructs a CTPlayer.
     *
     * @param id          Player UUID
     * @param currentName Current IGN
     */
    public CTUser(UUID id, String currentName) {
        this.id = id;
        this.currentName = currentName;
        this.previousNames = Lists.newArrayList();
        this.permissionAttachment = CraftCore.getInstance().getServer().getPlayer(id).addAttachment(CraftCore.getInstance());

        if (this.rank.getPermissionGroup() != null) {
            this.rank.getPermissionGroup().getPermissions().forEach(this::addPermission);
        }
        this.punishments = Lists.newArrayList();
        this.loadPunishments();
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getLastIGN() {
        return this.currentName;
    }

    @Override
    public void setLastIGN(String ign) {
        this.currentName = ign;
    }

    @Override
    public Collection<String> getPreviousIGNs() {
        return this.previousNames;
    }

    @Override
    public void setPreviousIGNs(Collection<String> igns) {
        this.previousNames.clear();
        this.previousNames.addAll(igns);
    }

    @Override
    public void setPreviousIGNs(String... igns) {
        this.previousNames.clear();
        Arrays.stream(igns).forEach(ign -> this.previousNames.add(ign));
    }

    @Override
    public void addPreviousIGNs(Collection<String> igns) {
        this.previousNames.addAll(igns);
    }

    @Override
    public void addPreviousIGNs(String... igns) {
        Arrays.stream(igns).forEach(ign -> this.previousNames.add(ign));
    }

    @Override
    public Player getPlayer() {
        return this.reference.get();
    }

    @Override
    public void setPlayer(Player player) {
        if (this.reference == null) {
            this.reference = new WeakReference<>(player);
        }
    }

    @Override
    public CTRank getRank() {
        return this.rank;
    }

    @Override
    public void setRank(CTRank rank) {
        this.rank = rank;
    }

    @Override
    public void addPermission(String permission) {
        this.permissionAttachment.setPermission(permission.toLowerCase(), true);
    }

    @Override
    public void removePermission(String permission) {
        this.permissionAttachment.unsetPermission(permission.toLowerCase());
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.permissionAttachment.getPermissions().keySet().stream().anyMatch(s -> s.equals(permission.toLowerCase()));
    }

    @Override
    public Collection<String> getPermissions() {
        return ImmutableSet.copyOf(this.permissionAttachment.getPermissions().keySet());
    }

    @Override
    public List<Punishment> getPunishments() {
        return this.punishments;
    }

    @Override
    public List<Punishment> getActivePunishments() {
        return this.punishments.stream().filter(p -> !p.isExpired()).collect(Collectors.toList());
    }

    /**
     * Load the punishments of the user from the database
     */
    private void loadPunishments(){
        //check database
    }
}
