package org.crafttogether.moderation;

import org.crafttogether.CraftCore;

/**
 * Created by dan on 5/5/16.
 */
public class Moderation {
    public static void init(){
        CraftCore.getInstance().getServer().getCommandMap().register("ban", new CmdBan("ban"));
        CraftCore.getInstance().getServer().getCommandMap().register("kick", new CmdKick("kick"));
        CraftCore.getInstance().getServer().getCommandMap().register("mute", new CmdMute("mute"));
    }
}
