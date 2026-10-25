package com.example.stalkersmod;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StalkerEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StalkerEntity(EntityType<? extends StalkerEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.FOLLOW_RANGE, 32);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        // Se defiende de quien le pegue (incluido el Tormentator)
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /** Prefijo de los nombres de animacion en el archivo .animation.json */
    protected String animPrefix() {
        return "animation.stalker.";
    }

    // ---- Animaciones ----
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "main", 5, this::predicate));
    }

    private PlayState predicate(AnimationState<StalkerEntity> state) {
        if (isDeadOrDying()) {
            return state.setAndContinue(RawAnimation.begin()
                    .then(animPrefix() + "death", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        if (swinging) {
            return state.setAndContinue(RawAnimation.begin()
                    .then(animPrefix() + "attack", Animation.LoopType.PLAY_ONCE));
        }
        if (state.isMoving()) {
            return state.setAndContinue(RawAnimation.begin().thenLoop(animPrefix() + "walk"));
        }
        return state.setAndContinue(RawAnimation.begin().thenLoop(animPrefix() + "idle"));
    }

    @Override
    protected void tickDeath() {
        // Deja ver la animacion de muerte ~2 segundos antes de desaparecer
        deathTime++;
        if (deathTime >= 40 && !level().isClientSide() && !isRemoved()) {
            level().broadcastEntityEvent(this, (byte) 60);
            remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
