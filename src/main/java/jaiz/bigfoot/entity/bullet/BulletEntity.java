package jaiz.bigfoot.entity.bullet;

import jaiz.bigfoot.entity.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BulletEntity extends AbstractBulletEntity implements FlyingItemEntity {
    private static final float MAX_RENDER_DISTANCE_WHEN_NEWLY_SPAWNED = MathHelper.square(3.5F);

    public BulletEntity(EntityType<? extends AbstractBulletEntity> entityType, World world) {
        super(entityType, world);
    }

    public BulletEntity(PlayerEntity player, World world, double x, double y, double z) {
        super(ModEntities.BULLET, world, player, x, y, z);
    }

    @Override
    public void tick() {
        super.tick();
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getEntityWorld().isClient()) {
            Entity entity = entityHitResult.getEntity();
            entity.serverDamage(entity.getDamageSources().thrown(this, this.getOwner()), 7.5F);
            this.getEntityWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
            super.onEntityHit(entityHitResult);
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        return (this.age >= 2 || !(distance < MAX_RENDER_DISTANCE_WHEN_NEWLY_SPAWNED)) && super.shouldRender(distance);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient()) {
            this.getEntityWorld().sendEntityStatus(this,
                    EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }



    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = ParticleTypes.SMOKE;;

            for (int i = 0; i < 2; i++) {
                this.getEntityWorld().addParticleClient(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
}