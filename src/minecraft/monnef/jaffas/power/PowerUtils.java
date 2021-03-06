package monnef.jaffas.power;

import monnef.jaffas.power.api.IPowerConsumer;
import monnef.jaffas.power.api.IPowerProvider;
import net.minecraftforge.common.ForgeDirection;

public class PowerUtils {
    public static final int LOSE_COEFFICIENT_MULTIPLIER = 100;

    public static boolean Connect(IPowerProvider provider, IPowerConsumer consumer, ForgeDirection side) {
        boolean success = true;
        if (side == ForgeDirection.UNKNOWN) {
            success &= provider.getPowerManager().connect(consumer);
            consumer.getPowerManager().connect(provider);
        } else {
            success &= provider.getPowerManager().connectDirect(consumer, side);
            consumer.getPowerManager().connectDirect(provider, side);
        }

        return success;
    }

    public static void Disconnect(IPowerProvider provider, IPowerConsumer consumer) {
        provider.getPowerManager().disconnect(consumer);
        consumer.getPowerManager().disconnect();
    }

    public static int loseEnergy(int energy, int distance) {
        return (energy * getLoseCoefficient(distance)) / LOSE_COEFFICIENT_MULTIPLIER;
    }

    public static int getLoseCoefficient(int distance) {
        if (distance <= 10) return 100;
        if (distance <= 20) return 80;
        if (distance <= 30) return 40;
        return 0;
    }
}
