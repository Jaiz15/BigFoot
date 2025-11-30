package jaiz.bigfoot.entity.bullet;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBulletEntity extends ExplosiveProjectileEntity implements FlyingItemEntity {

    public AbstractBulletEntity(EntityType<? extends  AbstractBulletEntity> entityType, World world) {
        super(entityType, world);
        this.accelerationPower = 0.0;
    }

    public AbstractBulletEntity(EntityType<? extends  AbstractBulletEntity> type, World world, Entity owner, double x, double y, double z) {
        super(type, x, y, z, world);
        this.setOwner(owner);
        this.accelerationPower = 0.0;
    }

    AbstractBulletEntity(EntityType<? extends  AbstractBulletEntity> entityType, double d, double e, double f, Vec3d vec3d, World world) {
        super(entityType, d, e, f, vec3d, world);
        this.accelerationPower = 0.0;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return !(other instanceof AbstractBulletEntity) && super.collidesWith(other);
    }

    @Override
    protected boolean canHit(Entity entity) {
        if (entity instanceof  AbstractBulletEntity) {
            return false;
        } else {
            return entity.getType() != EntityType.END_CRYSTAL && super.canHit(entity);
        }
    }


    @Override
    public void addVelocity(double deltaX, double deltaY, double deltaZ) {
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getEntityWorld().isClient()) {
            Vec3i vec3i = blockHitResult.getSide().getVector();
            Vec3d vec3d = Vec3d.of(vec3i).multiply(0.25, 0.25, 0.25);
            Vec3d vec3d2 = blockHitResult.getPos().add(vec3d);
            this.discard();
        }
    }

    @Override
    protected boolean isBurning() {
        return false;
    }

    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }

    @Override
    protected float getDrag() {
        return 1.0F;
    }

    @Override
    protected float getDragInWater() {
        return this.getDrag();
    }

    @Nullable
    @Override
    protected ParticleEffect getParticleType() {
        return null;
    }

}
