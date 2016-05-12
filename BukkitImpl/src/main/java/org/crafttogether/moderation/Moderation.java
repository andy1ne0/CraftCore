package org.crafttogether.moderation;

import org.crafttogether.CraftCore;

/**
 * @author Dan
 */
public final class Moderation {
    /**
     * nothing
     */
    private Moderation(){}

    /**
     * register the commands
     */
    public static void init(){
        CraftCore.getInstance().getServer().getCommandMap().register("ban", new CmdBan("ban"));
        CraftCore.getInstance().getServer().getCommandMap().register("kick", new CmdKick("kick"));
        CraftCore.getInstance().getServer().getCommandMap().register("mute", new CmdMute("mute"));
        CraftCore.getInstance().getServer().getCommandMap().register("bandata", new CmdBanData("bandata"));
    }
}
