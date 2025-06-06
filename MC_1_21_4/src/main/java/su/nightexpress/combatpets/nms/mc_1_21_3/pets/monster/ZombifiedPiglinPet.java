package su.nightexpress.combatpets.nms.mc_1_21_3.pets.monster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.combatpets.api.pet.PetEntity;
import su.nightexpress.combatpets.nms.mc_1_21_3.goals.combat.PetHurtByTargetGoal;
import su.nightexpress.combatpets.nms.mc_1_21_3.goals.combat.PetZombieAttackGoal;
import su.nightexpress.combatpets.nms.mc_1_21_3.goals.follow.PetFollowOwnerGoal;

public class ZombifiedPiglinPet extends ZombifiedPiglin implements PetEntity {

    public ZombifiedPiglinPet(@NotNull ServerLevel level) {
        super(EntityType.ZOMBIFIED_PIGLIN, level);
    }

    @Override
    public void setGoals() {
        this.targetSelector.addGoal(1, new PetHurtByTargetGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PetFollowOwnerGoal(this));
        this.goalSelector.addGoal(2, new PetZombieAttackGoal(this, 1D));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty, EntitySpawnReason reason, SpawnGroupData groupData) {
        return groupData;
    }

    @Override
    protected void customServerAiStep(ServerLevel worldserver) {

    }

    @Override
    public boolean isSunSensitive() {
        return false;
    }
}
