package jaiz.bigfoot.item;

import jaiz.bigfoot.BigFootMod;
import jaiz.bigfoot.entity.ModEntities;
import jaiz.bigfoot.item.custom.GunItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item BIGFOOT_SPAWN_EGG = registerItem("bigfoot_spawn_egg",
            SpawnEggItem::new, new Item.Settings().spawnEgg(ModEntities.BIGFOOT));

    public static final Item GUN = registerItem("gun", GunItem::new, new Item.Settings().maxCount(1));

    public static final Item THE_FOOT = registerItem("the_foot", Item::new, new Item.Settings().maxCount(1));

    public static final Item MACHETE = registerEquipment("machete", new Item.Settings().sword(ToolMaterial.COPPER, 5.0F, -3.5F));

    public static Item registerEquipment(String id, Item.Settings settings) {
        return registerItem(id, Item::new, settings);
    }

    public static <I extends Item> I registerItem(String name, Function<Item.Settings, I> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BigFootMod.MOD_ID, name));
        I item = factory.apply(settings.registryKey(key));

        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        BigFootMod.LOGGER.info("registering Mod Items for " +  BigFootMod.MOD_ID);
    }
}

