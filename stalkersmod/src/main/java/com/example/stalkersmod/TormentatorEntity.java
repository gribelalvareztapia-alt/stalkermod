package com.example.stalkersmod;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class TormentatorEntity extends StalkerEntity {

    public TormentatorEntity(EntityType<? extends TormentatorEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.FOLLOW_RANGE, 40);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // Prioridad 0: ataca a cualquier Stalker (de su especie o no) antes que a los jugadores
        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(
                this, StalkerEntity.class, 0, true, false, e -> e != this));
    }

    @Override
    protected String animPrefix() {
        return "animation.tormentator.";
    }
}
