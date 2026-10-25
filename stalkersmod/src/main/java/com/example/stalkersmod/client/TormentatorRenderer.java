package com.example.stalkersmod.client;

import com.example.stalkersmod.StalkersMod;
import com.example.stalkersmod.TormentatorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TormentatorRenderer extends GeoEntityRenderer<TormentatorEntity> {
    public TormentatorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new DefaultedEntityGeoModel<>(new ResourceLocation(StalkersMod.MODID, "tormentator")));
    }
}
