package jaiz.bigfoot;

import jaiz.bigfoot.entity.ModEntities;
import jaiz.bigfoot.entity.bigfoot.BigFootEntity;
import jaiz.bigfoot.entity.bullet.BulletEntity;
import jaiz.bigfoot.item.ModItemGroups;
import jaiz.bigfoot.item.ModItems;
import jaiz.bigfoot.sound.ModSounds;
import jaiz.bigfoot.util.SpawnThings;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigFootMod implements ModInitializer {
	public static final String MOD_ID = "bigfoot";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
		SpawnThings.addPlacedSpawn();

		FabricDefaultAttributeRegistry.register(ModEntities.BIGFOOT, BigFootEntity.createAttributes());

		LOGGER.info("wsg gang");
	}
}