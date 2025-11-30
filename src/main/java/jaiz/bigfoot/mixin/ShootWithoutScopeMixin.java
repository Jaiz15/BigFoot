package jaiz.bigfoot.mixin;

import jaiz.bigfoot.entity.bullet.BulletEntity;
import jaiz.bigfoot.item.ModItems;
import jaiz.bigfoot.item.custom.ClickingDetectorMixinAccessor;
import jaiz.bigfoot.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ShootWithoutScopeMixin extends PlayerLikeEntity {

    @Shadow @Final private ItemCooldownManager itemCooldownManager;

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    protected ShootWithoutScopeMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    ClickingDetectorMixinAccessor clicker =
            (ClickingDetectorMixinAccessor) MinecraftClient.getInstance();


    @Unique
    public int shootBreak = 0;

    @Unique
    public int reload = 0;

    @Inject(at = @At("HEAD"), method = "tick")
    private void shootGun(CallbackInfo callbackInfo) {
        shootBreak--;
        if(this.getMainHandStack().isOf(ModItems.GUN) && clicker.isLeftClicking() && !this.isUsingItem() && shootBreak < 0 && !this.getItemCooldownManager().isCoolingDown(this.getMainHandStack())){
            if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
                ProjectileEntity.spawnWithVelocity(
                        (world2, shooter, stack2) -> new BulletEntity(this.getAttackingPlayer(), this.getEntityWorld(), this.getEntityPos().getX(), this.getEyePos().getY() -0.1f, this.getEntityPos().getZ()),
                        serverWorld,
                        this.getMainHandStack(),
                        this,
                        0.0F,
                        5f,
                        5.0F
                );

            }
            this.playSound(ModSounds.GUN_SHOT);
            this.setYaw(this.getYaw() + this.getRandom().nextBetween(-1, 1));
            this.setPitch(this.getPitch() + this.getRandom().nextBetween(-2, 1));
            shootBreak = 1;
            reload++;
        }
        if(reload > 10){
            reload = 0;
            this.itemCooldownManager.set(this.getMainHandStack(), 100);
        }
    }


}



