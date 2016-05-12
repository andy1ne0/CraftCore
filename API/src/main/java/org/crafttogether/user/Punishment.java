package org.crafttogether.user;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by dan on 5/9/16.
 */
public interface Punishment {

    /**
     * @return The {@link PunishmentType}
     */
    PunishmentType getType();

    /**
     * @return The {@link Duration} of the punishment
     */
    Duration getDuration();

    /**
     * @return The {@link UUID} of the player punished
     */
    UUID getPlayer();

    /**
     * @return The {@link UUID} of the punisher
     */
    UUID getPunisher();

    /**
     * @return Is the punishment expired
     */
    boolean isExpired();

    /**
     * @return The {@link LocalDateTime} it was created
     */
    LocalDateTime getCreationDate();

    /**
     * @return The end {@link LocalDateTime}
     */
    LocalDateTime getEndDate();

    /**
     * @return The total duration in human readable string form
     */
    String getTotalDurationString();

    /**
     * @return The remaining duration in human readable string form
     */
    String getRemainingTimeString();

}
