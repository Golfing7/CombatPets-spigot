package su.nightexpress.combatpets.nms.mc_1_21.pets.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.combatpets.api.pet.PetEntity;
import su.nightexpress.combatpets.nms.mc_1_21.MC_1_21;
import su.nightexpress.combatpets.nms.mc_1_21.goals.combat.PetHurtByTargetGoal;
import su.nightexpress.combatpets.nms.mc_1_21.goals.combat.PetMeleeAttackGoal;
import su.nightexpress.combatpets.nms.mc_1_21.goals.follow.PetFollowOwnerGoal;

import javax.annotation.Nullable;

public class BeePet extends Bee implements PetEntity {

    public BeePet(@NotNull ServerLevel world) {
        super(EntityType.BEE, world);
    }

    @Override
    public void setGoals() {
        this.targetSelector.addGoal(1, new PetHurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new PetFollowOwnerGoal(this));
        this.goalSelector.addGoal(0, new PetMeleeAttackGoal(this, 2.4));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor,
                                        DifficultyInstance difficultyInstance,
                                        MobSpawnType spawnType,
                                        @org.jetbrains.annotations.Nullable SpawnGroupData groupData) {
        return groupData;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        // Re-add goal target, so our bee won't turn to passive after a single attack.
        boolean hurtTarget = super.doHurtTarget(entity);
        if (hurtTarget && entity instanceof LivingEntity && entity.isAlive()) {
            this.setTarget((LivingEntity) entity, TargetReason.CUSTOM, true);
        }
        return hurtTarget;
    }

    @Override
    protected void hurtArmor(DamageSource source, float amount) {
        MC_1_21.hurtArmor(this, source, amount);
    }

    @Override
    public void setHasStung(boolean flag) {
        // We don't want our bee to have stung and die
    }

    @Override
    public boolean hasStung() {
        // We don't want our bee to have stung and die
        return false;
    }

    @Override
    public void setSavedFlowerPos(BlockPos blockposition) {

    }

    @Override
    @VisibleForDebug
    public boolean hasHive() {
        return false;
    }

    @Override
    @Nullable
    @VisibleForDebug
    public BlockPos getHivePos() {
        return null;
    }

    @Override
    public boolean isFood(ItemStack itemstack) {
        return false;
    }

    @Override
    public InteractionResult mobInteract(Player entityhuman, InteractionHand enumhand) {
        return InteractionResult.sidedSuccess(this.level().isClientSide());
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public boolean canFallInLove() {
        return false;
    }
}
