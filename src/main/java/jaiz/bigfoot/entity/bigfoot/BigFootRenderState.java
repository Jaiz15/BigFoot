package jaiz.bigfoot.entity.bigfoot;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

@Environment(EnvType.CLIENT)
public class BigFootRenderState extends LivingEntityRenderState {
    public Object texture;
}
