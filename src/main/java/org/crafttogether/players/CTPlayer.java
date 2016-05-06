package org.crafttogether.players;

import org.crafttogether.moderation.Punishment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Network wide player data.
 *
 * @author theminecoder
 * @version 1.0
 */
public class CTPlayer {

    private UUID id;
    private String currentName;

    private CTRank rank = CTRank.PLAYER;
    private List<Punishment> punishments;

    /**
     * Constructs a CTPlayer.
     *
     * @param id Player UUID
     * @param currentName Current IGN
     */
    public CTPlayer(UUID id, String currentName) {
        this.id = id;
        this.currentName = currentName;
        this.punishments = loadPunishments();
    }

    private List<Punishment> loadPunishments(){
        return new ArrayList<>();
    }

    public List<Punishment> getPunishments() {
        return punishments;
    }

    /**
     * @return Player UUID
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * @return Current IGN
     */
    public String getCurrentName() {
        return this.currentName;
    }

    /**
     * Sets the current IGN
     * @param currentName IGN
     */
    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    /**
     * @return Player Rank
     */
    public CTRank getRank() {
        return this.rank;
    }

    /**
     * Sets the player rank
     * @param rank New rank
     */
    public void setRank(CTRank rank) {
        this.rank = rank;
    }

    public boolean isStaff(){
        return getRank().getPermissionLevel() > 1; //TODO: ?
    }
}
