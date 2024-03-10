package net.guwy.sticky_foundations.egg;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public enum Users {
    REDSTONE_STICK("7b11db5a-24b4-4f33-9202-01d0bd9c8d26", true);


    final UUID uuid;
    final boolean dev;  // temporarily make true for testing
    Users(String uuid){
        this(uuid, false);
    }
    Users(String uuid, boolean dev){
        this.uuid = UUID.fromString(uuid);
        this.dev = dev;
    }

    public static boolean checkUUID(Player player, Users toCompare) {
        return player.getUUID().equals(toCompare.uuid)
                || toCompare.dev;
    }
}
