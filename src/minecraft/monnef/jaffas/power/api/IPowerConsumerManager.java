package monnef.jaffas.power.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public interface IPowerConsumerManager {
    void initialize(int maximalPacketSize, int bufferSize, TileEntity tile);

    int getMaximalPacketSize();

    int getBufferSize();

    int getCurrentMaximalPacketSize();

    boolean hasBuffered(int energy);

    /**
     * Consumes energy from a buffer.
     *
     * @param energy Energy amount.
     * @return Energy successfully consumed.
     */
    int consume(int energy);

    /**
     * Stores energy in a buffer (called by antennas).
     *
     * @param energy Energy amount.
     * @return Energy successfully stored.
     */
    int store(int energy);

    void connect(IPowerProvider provider);

    void connectDirect(IPowerProvider provider, ForgeDirection side);

    void disconnect();

    boolean isConnected();

    /**
     * Is buffer not full?
     *
     * @return Power consumer wants juice.
     */
    boolean energyNeeded();

    /**
     * Only in this method will manager request power (maximal one packet).
     * Do NOT call more often than once per tick.
     */
    void tick();

    int getFreeSpaceInBuffer();

    TileEntity getTile();

    int getCurrentBufferedEnergy();

    /**
     * Saves inner energy state.
     *
     * @param tagCompound
     */
    void writeToNBT(NBTTagCompound tagCompound);

    void readFromNBT(NBTTagCompound tagCompound);

    IPowerProvider getProvider();
}
