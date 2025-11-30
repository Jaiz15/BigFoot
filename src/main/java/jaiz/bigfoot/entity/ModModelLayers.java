package jaiz.bigfoot.entity;

import jaiz.bigfoot.BigFootMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer BIGFOOT =
            new EntityModelLayer(Identifier.of(BigFootMod.MOD_ID, "bigfoot"), "main");

    public static final EntityModelLayer BULLET =
            new EntityModelLayer(Identifier.of(BigFootMod.MOD_ID, "bullet"), "main");


}
