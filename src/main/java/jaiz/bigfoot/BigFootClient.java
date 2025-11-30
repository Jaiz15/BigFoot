package jaiz.bigfoot;

import jaiz.bigfoot.entity.ModEntities;
import jaiz.bigfoot.entity.ModModelLayers;
import jaiz.bigfoot.entity.bigfoot.BigFoot;
import jaiz.bigfoot.entity.bigfoot.BigFootRenderer;
import jaiz.bigfoot.entity.bullet.BulletEntityModel;
import jaiz.bigfoot.entity.bullet.BulletEntityRenderer;
import jaiz.bigfoot.item.ModItemGroups;
import jaiz.bigfoot.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.WindChargeEntityModel;
import net.minecraft.world.biome.FoliageColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigFootClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.register(ModEntities.BIGFOOT, BigFootRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BIGFOOT, BigFoot::getTexturedModelData);

		EntityRendererRegistry.register(ModEntities.BULLET, BulletEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BULLET, BulletEntityModel::getTexturedModelData);

	}
}