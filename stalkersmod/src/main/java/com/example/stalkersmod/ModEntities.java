package com.example.stalkersmod;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StalkersMod.MODID);

    public static final RegistryObject<EntityType<StalkerEntity>> STALKER =
            ENTITIES.register("stalker", () -> EntityType.Builder
                    .of(StalkerEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 2.4f)
                    .build("stalker"));

    public static final RegistryObject<EntityType<TormentatorEntity>> TORMENTATOR =
            ENTITIES.register("tormentator", () -> EntityType.Builder
                    .of(TormentatorEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 2.8f)
                    .build("tormentator"));
}
