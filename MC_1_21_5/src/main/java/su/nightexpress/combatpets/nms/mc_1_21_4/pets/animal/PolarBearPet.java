package su.nightexpress.combatpets.nms.mc_1_21_4.pets.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.combatpets.api.pet.PetEntity;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.combat.PetHurtByTargetGoal;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.combat.PetMeleeAttackGoal;
import su.nightexpress.combatpets.nms.mc_1_21_4.goals.follow.PetFollowOwnerGoal;

public class PolarBearPet extends PolarBear implements PetEntity {

    public PolarBearPet(@NotNull ServerLevel level) {
        super(EntityType.POLAR_BEAR, level);
    }

    @Override
    public void setGoals() {
        this.targetSelector.addGoal(1, new PetHurtByTargetGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PetMeleeAttackGoal(this, 1.2));
        this.goalSelector.addGoal(2, new PetFollowOwnerGoal(this));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty, EntitySpawnReason reason, SpawnGroupData groupData) {
        return groupData;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return this.level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS_SERVER;
    }
}
