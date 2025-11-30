package jaiz.bigfoot.item.custom;

import jaiz.bigfoot.entity.bullet.BulletEntity;
import jaiz.bigfoot.sound.ModSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GunItem extends Item implements ProjectileItem {
    public GunItem(Item.Settings settings) {
        super(settings);
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

    public int shootBreak = 0;

    public int reload = 0;

    ClickingDetectorMixinAccessor clicker =
            (ClickingDetectorMixinAccessor) MinecraftClient.getInstance();
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {

        shootBreak--;
        if(reload > 20){
            user.stopUsingItem();
            this.finishUsing(stack, world, user);
            if(user instanceof PlayerEntity player){
                reload = 0;
                player.getItemCooldownManager().set(stack, 100);
            }
        }

        if(clicker.isLeftClicking() && shootBreak < 0 && user.isUsingItem()){
            if(user instanceof PlayerEntity player && !player.getItemCooldownManager().isCoolingDown(stack)){
            ItemStack itemStack = user.getMainHandStack();
            if (world instanceof ServerWorld serverWorld) {
                ProjectileEntity.spawnWithVelocity(
                        (world2, shooter, stack2) -> {
                            assert user instanceof PlayerEntity;
                            return new BulletEntity((PlayerEntity) user, world, user.getEntityPos().getX(), user.getEyePos().getY() -0.1f, user.getEntityPos().getZ());
                        },
                        serverWorld,
                        itemStack,
                        user,
                        0.0F,
                        5f,
                        0.0F
                );
            }
            user.playSound(ModSounds.GUN_SHOT);
            user.setPitch(user.getPitch() + user.getRandom().nextBetween(-1, 1));
            shootBreak = 2;
            reload++;
            }
        }

    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 1200;
    }

    @Override
    public void initializeProjectile(ProjectileEntity entity, double x, double y, double z, float power, float uncertainty) {
    }


    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return null;
    }

    @Override
    public ProjectileItem.Settings getProjectileSettings() {
        return ProjectileItem.Settings.builder()
                .positionFunction((pointer, facing) -> DispenserBlock.getOutputLocation(pointer, 1.0, Vec3d.ZERO))
                .uncertainty(6.6666665F)
                .power(1.0F)
                .overrideDispenseEvent(1051)
                .build();
    }
}
