package net.guwy.sticky_foundations.egg;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public enum Users {
    REDSTONE_STICK("7b11db5a-24b4-4f33-9202-01d0bd9c8d26", "77f2d061-c9f1-3980-a689-6d047aee96c2");


    final UUID uuid;
    final UUID backupUuid;
    final boolean dev;  // temporarily make true for testing
    Users(String uuid){
        this(uuid, null, false);
    }
    Users(String uuid, String backupUuid){
        this(uuid, backupUuid, false);
    }
    Users(String uuid, String backupUuid, boolean dev){
        this.uuid = UUID.fromString(uuid);
        this.backupUuid = UUID.fromString(backupUuid);;
        this.dev = dev;
    }

    public static boolean checkUUID(Player player, Users toCompare) {
        return player.getUUID().equals(toCompare.uuid)
                || player.getUUID().equals(toCompare.backupUuid)
                || toCompare.dev;
    }
}
