package jaiz.bigfoot.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(jaiz.bigfoot.BigFootMod.MOD_ID, "bigfoot"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bigfoot"))
                    .icon(() -> new ItemStack(Items.CROSSBOW.asItem())).entries((displayContext, entries) -> {
                        // things go here
                        entries.add(ModItems.GUN);
                        entries.add(ModItems.MACHETE);
                        entries.add(ModItems.THE_FOOT);
                        entries.add(ModItems.BIGFOOT_SPAWN_EGG);

                    }).build());

    public static void registerItemGroups() {
    }
}
