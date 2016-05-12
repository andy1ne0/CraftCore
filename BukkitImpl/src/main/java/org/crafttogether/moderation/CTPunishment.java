package org.crafttogether.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.crafttogether.CraftCore;
import org.crafttogether.user.Punishment;
import org.crafttogether.user.PunishmentType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.rethinkdb.RethinkDB.r;

/**
 * @author Dan
 */
public class CTPunishment implements Punishment {
    private PunishmentType type;
    private UUID player;
    private UUID punisher;
    private Duration duration;
    private boolean expired;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;

    /**
     *
     * @param type The {@link PunishmentType}
     * @param punisher The {@link UUID} of the punisher
     * @param player The {@link UUID} of the player punished
     * @param duration The {@link Duration} of the punishment
     * @param expired If the punishment is expired
     * @param creationDate The {@link LocalDateTime} the punishment was created
     */
    public CTPunishment(PunishmentType type, UUID punisher, UUID player, Duration duration, boolean expired, LocalDateTime creationDate) {
        this.type = type;
        this.punisher = punisher;
        this.player = player;
        this.duration = duration;
        this.expired = expired;
        this.creationDate = creationDate;
        this.endDate = creationDate.plus(duration);
        if(this.type == PunishmentType.BAN && Bukkit.getPlayer(player).isOnline() && !this.expired){ Bukkit.getPlayer(player).kickPlayer(ChatColor.RED + "You have been banned for " + this.getDuration().toHours() + " hours!");}
        r.table("moderation").insert(this).run(CraftCore.getInstance().getDatabaseConnection());
    }

    /**
     * @return the {@link PunishmentType} of the punishment
     */
    public PunishmentType getType() {
        return this.type;
    }

    /**
     * @return the total duration
     */
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * @return the UUID of the punished player
     */
    public UUID getPlayer() {
        return this.player;
    }

    /**
     * @return the UUID of the punisher
     */
    public UUID getPunisher() {
        return this.punisher;
    }

    /**
     * @return is it expired
     */
    public boolean isExpired() {
        return this.expired;
    }

    /**
     * @return creation date
     */
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @return end date
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * @return Readable string of total duration
     */
    public String getTotalDurationString() {
        LocalDateTime fromDateTime = this.creationDate;
        LocalDateTime toDateTime = this.endDate;

        LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

        long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( minutes );

        long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS);

        return years + " years " +
                months + " months " +
                days + " days " +
                hours + " hours " +
                minutes + " minutes " +
                seconds + " seconds.";
    }

    /**
     * @return Readable string of remaining duration
     */
    public String getRemainingTimeString() {
        LocalDateTime fromDateTime = LocalDateTime.now();
        LocalDateTime toDateTime = this.endDate;

        LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

        long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( minutes );

        long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS);

        return years + " years " +
                months + " months " +
                days + " days " +
                hours + " hours " +
                minutes + " minutes " +
                seconds + " seconds.";
        //http://stackoverflow.com/questions/25747499/java-8-calculate-difference-between-two-localdatetime
    }
}
