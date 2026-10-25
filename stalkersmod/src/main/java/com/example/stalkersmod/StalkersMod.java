package com.example.stalkersmod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(StalkersMod.MODID)
public class StalkersMod {
    public static final String MODID = "stalkersmod";

    public StalkersMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(bus);
    }
}
