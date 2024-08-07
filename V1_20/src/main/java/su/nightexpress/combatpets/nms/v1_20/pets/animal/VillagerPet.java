package su.nightexpress.combatpets.nms.v1_20.pets.animal;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.combatpets.api.pet.PetEntity;
import su.nightexpress.combatpets.nms.v1_20.V1_20;
import su.nightexpress.combatpets.nms.v1_20.goals.combat.PetMeleeAttackGoal;
import su.nightexpress.combatpets.nms.v1_20.goals.combat.PetHurtByTargetGoal;
import su.nightexpress.combatpets.nms.v1_20.goals.follow.PetFollowOwnerGoal;

public class VillagerPet extends Villager implements PetEntity {

    public VillagerPet(@NotNull ServerLevel level) {
        super(EntityType.VILLAGER, level);
    }

    @Override
    public void setGoals() {
        this.targetSelector.addGoal(1, new PetHurtByTargetGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PetFollowOwnerGoal(this));
        this.goalSelector.addGoal(4, new PetMeleeAttackGoal(this));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor,
                                        DifficultyInstance difficultyInstance,
                                        MobSpawnType spawnType,
                                        @Nullable SpawnGroupData groupData,
                                        @Nullable CompoundTag tag) {
        return groupData;
    }

    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return this.brainProvider().makeBrain(dynamic);
    }

    public void refreshBrain(ServerLevel worldserver) {
        Brain<Villager> behaviorcontroller = this.getBrain();
        behaviorcontroller.stopAll(worldserver, this);
        this.brain = behaviorcontroller.copyWithoutBehaviors();
    }

    @Override
    protected void hurtArmor(DamageSource source, float amount) {
        V1_20.hurtArmor(this, source, amount);
    }

    @Override
    public boolean canRestock() {
        return false;
    }

    @Override
    public void restock() {

    }

    @Override
    public boolean shouldRestock() {
        return false;
    }

    @Override
    public void setChasing(boolean flag) {
        super.setChasing(false);
    }

    @Override
    public boolean isChasing() {
        return false;
    }

    @Override
    public void releaseAllPois() {

    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public void setOffers(MerchantOffers merchantrecipelist) {
        this.offers = null;
    }

    @Override
    public void increaseMerchantCareer() {

    }

    @Override
    public void thunderHit(ServerLevel worldserver, LightningBolt entitylightning) {

    }

    @Override
    public boolean wantsToPickUp(ItemStack itemstack) {
        return false;
    }

    @Override
    protected void updateTrades() {

    }

    @Override
    public void gossip(ServerLevel worldserver, Villager entityvillager, long i) {

    }

    @Override
    public boolean wantsToSpawnGolem(long i) {
        return false;
    }

    @Override
    public void startSleeping(BlockPos blockposition) {

    }

    @Override
    public void stopSleeping() {

    }
}
