package su.nightexpress.combatpets.wardrobe.handler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.combatpets.wardrobe.util.VariantHandler;
import su.nightexpress.nightcore.util.NumberUtil;

import java.util.List;
import java.util.stream.IntStream;

public class SizeVariantHandler extends VariantHandler<Integer> {

    @Override
    @NotNull
    public List<Integer> values() {
        return IntStream.range(1, 17).boxed().toList();
    }

    @Override
    @Nullable
    public Integer parse(@NotNull String raw) {
        return NumberUtil.getInteger(raw, 1);
    }

    @Override
    @Nullable
    public Integer read(@NotNull LivingEntity entity) {
        if (entity instanceof Slime slime) {
            return slime.getSize();
        }
        if (entity instanceof Phantom phantom) {
            return phantom.getSize();
        }

        return null;
    }

    @Override
    @NotNull
    public String getLocalized(@NotNull Integer value) {
        return String.valueOf(value);
    }

    @Override
    @NotNull
    public String getRaw(@NotNull Integer value) {
        return value.toString();
    }

    @Override
    public boolean apply(@NotNull LivingEntity entity, @Nullable Integer value) {
        if (value == null) return false;

        if (entity instanceof Slime slime) {
            slime.setSize(value);
        }
        if (entity instanceof Phantom phantom) {
            phantom.setSize(value);
        }

        return false;
    }
}
