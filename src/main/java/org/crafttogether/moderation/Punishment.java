package org.crafttogether.moderation;

import java.time.Duration;
import java.util.UUID;

/**
 * Created by dan on 5/5/16.
 */
public class Punishment {
    private PunishmentType type;
    private UUID player;
    private UUID punisher;
    private Duration duration;

    public Punishment(PunishmentType type, UUID punisher, UUID player, Duration duration) {
        this.type = type;
        this.punisher = punisher;
        this.player = player;
        this.duration = duration;
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
}
