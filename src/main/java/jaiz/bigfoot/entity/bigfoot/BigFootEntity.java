package jaiz.bigfoot.entity.bigfoot;

import jaiz.bigfoot.sound.ModSounds;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;


public class BigFootEntity extends PathAwareEntity {


    public BigFootEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static boolean canMobSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos.down();
        return (SpawnReason.isAnySpawner(spawnReason) || world.getBlockState(blockPos).allowsSpawning(world, blockPos, type))
                && random.nextInt(75) == 0
                && pos.getY() >= 64;
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        if(this.getHealth() > 7){
            for (int i = 0; i < this.getRandom().nextInt(12) + 6; i++) {
                this.getEntityWorld().addParticleClient(ParticleTypes.PALE_OAK_LEAVES,
                        this.getParticleX(1.2),
                        this.getRandomBodyY(),
                        this.getParticleZ(1.2), 0.0, 0.0, 0.0);
            }}
        super.onDamaged(damageSource);
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setVariant(BigFootVariant.byId(view.getInt("Variant", 0)));
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Variant", this.getTypeVariant());
    }


    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    @Override
    protected void applyDamage(ServerWorld world, DamageSource source, float amount) {
        super.applyDamage(world, source, amount);
        if(this.getHealth() > 7){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 60, 0, false, false));
        }
    }

    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(BigFootEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        BigFootVariant variant = Util.getRandom(BigFootVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public Object getVariant() {
        return BigFootVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(VARIANT);
    }

    private void setVariant(BigFootVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId() & 255);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.1d, false));
        this.targetSelector.add(3, new RevengeGoal(this).setGroupRevenge());
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 30)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.325f)
                .add(EntityAttributes.ATTACK_DAMAGE, 8)
                .add(EntityAttributes.FOLLOW_RANGE, 24)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.5f);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.BIGFOOT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.BIGFOOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BIGFOOT_DEATH;
    }

}
