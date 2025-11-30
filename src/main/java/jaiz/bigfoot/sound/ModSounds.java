package jaiz.bigfoot.sound;

import jaiz.bigfoot.BigFootMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent GUN_SHOT = registerSoundEvent("gun_shot");

    public static final SoundEvent BIGFOOT_FOREST_AMBIENT = registerSoundEvent("bigfoot_forest_ambient");

    public static final SoundEvent BIGFOOT_AMBIENT = registerSoundEvent("bigfoot_ambient");
    public static final SoundEvent BIGFOOT_HURT = registerSoundEvent("bigfoot_hurt");
    public static final SoundEvent BIGFOOT_DEATH = registerSoundEvent("bigfoot_death");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(BigFootMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));

    }

    public static void registerSounds() {
    }
}
