package org.crafttogether.moderation;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.crafttogether.CraftCore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.rethinkdb.RethinkDB.r;

/**
 * Created by dan on 5/5/16.
 */
public class Punishment {
    private PunishmentType type;
    private UUID player;
    private UUID punisher;
    private Duration duration;
    private boolean expired;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private String durationString;

    public Punishment(PunishmentType type, UUID punisher, UUID player, Duration duration, boolean expired, LocalDateTime creationDate) {
        this.type = type;
        this.punisher = punisher;
        this.player = player;
        this.duration = duration;
        this.expired = expired;
        this.creationDate = creationDate;
        this.endDate = creationDate.plus(duration);
        if(type == PunishmentType.BAN && Bukkit.getPlayer(player).isOnline() && !expired) Bukkit.getPlayer(player).kickPlayer(ChatColor.RED + "You have been banned for " + getDuration().toHours() + " hours!");
        r.table("moderation").insert(this).run(CraftCore.getInstance().getDatabaseConnection());
        this.durationString = DurationFormatUtils.formatDuration(duration.toMillis(), "H:mm:ss", true);
    }

    public PunishmentType getType() {
        return type;
    }

    public Duration getDuration() {
        return duration;
    }

    public UUID getPlayer() {
        return player;
    }

    public UUID getPunisher() {
        return punisher;
    }

    public boolean isExpired() {
        return expired;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDurationString() {
        return durationString;
    }
}
