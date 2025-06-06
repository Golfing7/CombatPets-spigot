package su.nightexpress.combatpets.nms.mc_1_21_4.pets.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ServerLevelAccessor;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.combatpets.api.pet.PetEntity;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.combat.PetHurtByTargetGoal;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.combat.PetMeleeAttackGoal;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.follow.PetFollowOwnerGoal;

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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty, EntitySpawnReason reason, SpawnGroupData groupData) {
        return groupData;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entity) {
        // Re-add goal target, so our bee won't turn to passive after a single attack.
        boolean hurtTarget = super.doHurtTarget(level, entity);
        if (hurtTarget && entity instanceof LivingEntity && entity.isAlive()) {
            this.setTarget((LivingEntity) entity, TargetReason.CUSTOM);
        }
        return hurtTarget;
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return this.level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS_SERVER;
    }
}
