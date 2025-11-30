package jaiz.bigfoot.mixin;

import jaiz.bigfoot.item.custom.ClickingDetectorMixinAccessor;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class ClickDetectorMixin implements ClickingDetectorMixinAccessor {

    @Unique
    public boolean leftClicking = false;

    @Unique
    public boolean isLeftClicking() {
        return leftClicking;
    }


    @Inject(at = @At("HEAD"), method = "handleInputEvents")
    private void senseClicking(CallbackInfo callbackInfo) {
        MinecraftClient client = MinecraftClient.getInstance();
        leftClicking = client.options.attackKey.isPressed();
    }


}



