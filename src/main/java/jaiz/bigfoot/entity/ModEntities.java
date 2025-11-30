package jaiz.bigfoot.entity;

import jaiz.bigfoot.BigFootMod;
import jaiz.bigfoot.entity.bigfoot.BigFootEntity;
import jaiz.bigfoot.entity.bullet.BulletEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<BigFootEntity> BIGFOOT = registerEntityType("bigfoot",
            EntityType.Builder.create(BigFootEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.25f, 2.95f));

    public static final EntityType<BulletEntity> BULLET = registerEntityType(
            "bullet",
            EntityType.Builder.<BulletEntity>create(BulletEntity::new, SpawnGroup.MISC).dropsNothing()
                    .dimensions(0.35f, 0.35f).maxTrackingRange(8)
    );


    public static <T extends Entity> EntityType<T> registerEntityType(String path, EntityType.Builder<T> entityTypeBuilder) {
        Identifier id = Identifier.of(BigFootMod.MOD_ID, path);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
        return Registry.register(Registries.ENTITY_TYPE, key, entityTypeBuilder.build(key));
    }
}

