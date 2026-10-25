package com.example.stalkersmod;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StalkersMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void attributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.STALKER.get(), StalkerEntity.createAttributes().build());
        e.put(ModEntities.TORMENTATOR.get(), TormentatorEntity.createAttributes().build());
    }
}
