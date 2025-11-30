package jaiz.bigfoot.entity.bullet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;

@Environment(EnvType.CLIENT)
public class BulletEntityModel extends EntityModel<EntityRenderState> {

    private final ModelPart bullet;

    public BulletEntityModel(ModelPart root) {
        super(root);
        this. bullet = root.getChild("bullet");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bullet = modelPartData.addChild("bullet", ModelPartBuilder.create().uv(0,
                0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F,
                new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    public void setAngles(EntityRenderState entityRenderState) {
        super.setAngles(entityRenderState);

    }
}
