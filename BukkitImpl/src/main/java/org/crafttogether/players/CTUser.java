package org.crafttogether.players;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.crafttogether.user.CTRank;
import org.crafttogether.user.User;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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

    private WeakReference<Player> reference;

    private CTRank rank = CTRank.PLAYER;

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
}
