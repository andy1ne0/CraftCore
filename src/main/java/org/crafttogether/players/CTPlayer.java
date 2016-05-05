package org.crafttogether.players;

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

    public CTPlayer(UUID id, String currentName) {
        this.id = id;
        this.currentName = currentName;
    }

    public UUID getId() {
        return id;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public CTRank getRank() {
        return rank;
    }

    public void setRank(CTRank rank) {
        this.rank = rank;
    }
}
