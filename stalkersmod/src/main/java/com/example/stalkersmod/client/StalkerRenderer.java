package com.example.stalkersmod.client;

import com.example.stalkersmod.StalkerEntity;
import com.example.stalkersmod.StalkersMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StalkerRenderer extends GeoEntityRenderer<StalkerEntity> {
    public StalkerRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new DefaultedEntityGeoModel<>(new ResourceLocation(StalkersMod.MODID, "stalker")));
    }
}
