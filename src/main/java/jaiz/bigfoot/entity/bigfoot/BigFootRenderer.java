package jaiz.bigfoot.entity.bigfoot;

import com.google.common.collect.Maps;
import jaiz.bigfoot.BigFootMod;
import jaiz.bigfoot.entity.ModModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class BigFootRenderer extends MobEntityRenderer<BigFootEntity, BigFootRenderState, BigFoot> {

    private static final Identifier TEXTURE = Identifier.of(BigFootMod.MOD_ID, "textures/entity/bigfoot/big_foot_01.png");

    public static final Map<BigFootVariant, Identifier> TEXTURE_VARIANT =
            Util.make(Maps.newEnumMap(BigFootVariant.class), (map) -> {
                map.put(BigFootVariant.BIGGY, Identifier.of(BigFootMod.MOD_ID, "textures/entity/bigfoot/big_foot_01.png"));
                map.put(BigFootVariant.CHUNKY, Identifier.of(BigFootMod.MOD_ID, "textures/entity/bigfoot/big_foot_02.png"));
                map.put(BigFootVariant.FOOTY, Identifier.of(BigFootMod.MOD_ID, "textures/entity/bigfoot/big_foot_03.png"));
                map.put(BigFootVariant.BUZZLIGHTYEAR, Identifier.of(BigFootMod.MOD_ID, "textures/entity/bigfoot/big_foot_04.png"));
                map.put(BigFootVariant.MONK, Identifier.of(BigFootMod.MOD_ID, "textures/entity/bigfoot/big_foot_05.png"));
            });

    public BigFootRenderer(EntityRendererFactory.Context context) {
        super(context, new BigFoot(context.getPart(ModModelLayers.BIGFOOT)), 0.5f);
    }

    public BigFootRenderState createRenderState() {
        return new BigFootRenderState();
    }


    @Override
    public Identifier getTexture(BigFootRenderState state) {
        return TEXTURE_VARIANT.get(state.texture);
    }

    public void updateRenderState(BigFootEntity entity, BigFootRenderState state, float f) {
        super.updateRenderState(entity, state, f);
        state.texture = entity.getVariant();
    }
}
