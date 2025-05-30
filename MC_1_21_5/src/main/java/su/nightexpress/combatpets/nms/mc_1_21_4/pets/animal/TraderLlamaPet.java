package su.nightexpress.combatpets.nms.mc_1_21_4.pets.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.combatpets.api.pet.PetEntity;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.combat.PetHurtByTargetGoal;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.follow.PetFollowOwnerGoal;

import java.util.UUID;

public class TraderLlamaPet extends TraderLlama implements PetEntity {

    public TraderLlamaPet(@NotNull ServerLevel world) {
        super(EntityType.TRADER_LLAMA, world);
    }

    @Override
    public void setGoals() {
        this.targetSelector.addGoal(1, new PetHurtByTargetGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PetFollowOwnerGoal(this));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.25, 40, 20.0f));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty, EntitySpawnReason reason, SpawnGroupData groupData) {
        return groupData;
    }

    @Override
    public void aiStep() {
        this.setDespawnDelay(Integer.MAX_VALUE);
        super.aiStep();
    }

    @Override
    public void joinCaravan(Llama entityllama) {

    }

    @Override
    public boolean isSaddled() {
        return true;
    }

    @Override
    public boolean isTamed() {
        return true;
    }

    @Override
    public @Nullable EntityReference<LivingEntity> getOwnerReference() {
        return new EntityReference<>(this.getOwnerId());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return this.level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected boolean handleEating(Player entityhuman, ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean canEatGrass() {
        return false;
    }
}
