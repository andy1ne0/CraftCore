package org.crafttogether.players;

import com.google.common.collect.Lists;
import com.rethinkdb.net.Cursor;
import org.crafttogether.CraftCore;
import org.crafttogether.moderation.Punishment;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.rethinkdb.RethinkDB.r;

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
    private List<Punishment> activePunishments;

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
        this.activePunishments = punishments.stream().filter(punishment -> !punishment.isExpired()).collect(Collectors.toList());
    }

    private List<Punishment> loadPunishments(){
        Cursor cursor = r.table("moderation").filter(row -> row.g("player").eq(id)).run(CraftCore.getInstance().getDatabaseConnection());
        List<Punishment> punishments = Lists.newArrayList();
        cursor.forEach(p -> punishments.add((Punishment)p));
        return punishments;
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

    public List<Punishment> getActivePunishments() {
        return activePunishments;
    }
}
