package jaiz.bigfoot.util;

import jaiz.bigfoot.entity.ModEntities;
import jaiz.bigfoot.entity.bigfoot.BigFootEntity;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;

public class SpawnThings {
    public static void addPlacedSpawn() {
        SpawnRestriction.register(ModEntities.BIGFOOT, SpawnRestriction.getLocation(ModEntities.BIGFOOT),
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BigFootEntity::canMobSpawn);
    }
}
