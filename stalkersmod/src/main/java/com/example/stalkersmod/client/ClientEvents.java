package com.example.stalkersmod.client;

import com.example.stalkersmod.ModEntities;
import com.example.stalkersmod.StalkersMod;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StalkersMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(ModEntities.STALKER.get(), StalkerRenderer::new);
        e.registerEntityRenderer(ModEntities.TORMENTATOR.get(), TormentatorRenderer::new);
    }
}
