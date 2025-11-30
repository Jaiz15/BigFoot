// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package jaiz.bigfoot.entity.bigfoot;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.FrogAnimations;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;


public class BigFoot extends EntityModel<BigFootRenderState> {
	private final ModelPart bigfoot;
	private final Animation walkingAnimation;

	public BigFoot(ModelPart root) {
        super(root);
		this.bigfoot = root.getChild("bigfoot");
		this.walkingAnimation = BigFootAnimation.WALK.createAnimation(bigfoot);

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bigfoot = modelPartData.addChild("bigfoot", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData body = bigfoot.addChild("body", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -19.5833F, 0.0833F));

		ModelPartData body_top = body.addChild("body_top", ModelPartBuilder.create().uv(0, 19).cuboid(-7.0F, -9.8333F, -1.1667F, 14.0F, 10.0F, 6.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-7.0F, -20.8333F, -3.1667F, 14.0F, 11.0F, 8.0F, new Dilation(0.0F))
				.uv(70, 0).cuboid(-7.0F, -9.8333F, -3.1667F, 14.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 2.4167F, 0.0833F));

		ModelPartData head = body_top.addChild("head", ModelPartBuilder.create().uv(44, 0).cuboid(-3.0F, -9.0F, -3.5F, 6.0F, 11.0F, 7.0F, new Dilation(0.0F))
				.uv(60, 34).cuboid(-3.0F, -9.0F, -3.5F, 6.0F, 11.0F, 7.0F, new Dilation(0.25F))
				.uv(40, 64).cuboid(3.0F, -6.0F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(46, 64).cuboid(-5.0F, -6.0F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -19.8333F, -1.6667F));

		ModelPartData arm_l = body_top.addChild("arm_l", ModelPartBuilder.create().uv(40, 34).cuboid(0.0F, -3.0F, -3.0F, 4.0F, 24.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(7.0F, -16.8333F, 0.8333F));

		ModelPartData arm_r = body_top.addChild("arm_r", ModelPartBuilder.create().uv(0, 50).cuboid(-4.0F, -3.0F, -3.0F, 4.0F, 24.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(-7.0F, -16.8333F, 0.8333F));

		ModelPartData legs = body.addChild("legs", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -2.4167F, -0.0833F));

		ModelPartData legs_l = legs.addChild("legs_l", ModelPartBuilder.create().uv(0, 35).cuboid(-3.75F, 18.25F, -9.5F, 8.0F, 3.0F, 12.0F, new Dilation(0.0F))
				.uv(20, 50).cuboid(-2.75F, 1.25F, -2.5F, 5.0F, 18.0F, 5.0F, new Dilation(-0.1F)), ModelTransform.origin(3.75F, 0.75F, 2.5F));

		ModelPartData legs_r = legs.addChild("legs_r", ModelPartBuilder.create().uv(40, 19).cuboid(-4.25F, 18.25F, -9.5F, 8.0F, 3.0F, 12.0F, new Dilation(0.0F))
				.uv(60, 52).cuboid(-2.25F, 1.25F, -2.5F, 5.0F, 18.0F, 5.0F, new Dilation(-0.1F)), ModelTransform.origin(-3.75F, 0.75F, 2.5F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	public ModelPart getPart() {
		return bigfoot;
	}

	public void setAngles(BigFootRenderState state) {
		super.setAngles(state);
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.walkingAnimation.applyWalking(state.limbSwingAnimationProgress, state.limbSwingAmplitude, 1.5F, 2.5F);
	}
}