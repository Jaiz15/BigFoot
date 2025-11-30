package jaiz.bigfoot.entity.bullet;

import jaiz.bigfoot.BigFootMod;
import jaiz.bigfoot.entity.ModModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BulletEntityRenderer extends EntityRenderer<BulletEntity, EntityRenderState> {
    private static final Identifier TEXTURE = Identifier.of(BigFootMod.MOD_ID, "textures/entity/bullet.png");
    private final BulletEntityModel model;

    public BulletEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new BulletEntityModel(context.getPart(ModModelLayers.BULLET));
    }

    @Override
    public void render(EntityRenderState renderState, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        queue.submitModel(
                this.model,
                renderState,
                matrices,
                RenderLayer.getBreezeWind(TEXTURE, this.getXOffset(renderState.age) % 1.0F, 0.0F),
                renderState.light,
                OverlayTexture.DEFAULT_UV,
                renderState.outlineColor,
                null
        );
        super.render(renderState, matrices, queue, cameraState);
    }

    protected float getXOffset(float tickProgress) {
        return tickProgress * 0.03F;
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
