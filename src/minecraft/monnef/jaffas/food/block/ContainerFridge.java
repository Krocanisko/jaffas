package monnef.jaffas.food.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

public class ContainerFridge extends Container {

    public static final int inventorySize = 20;
    protected TileEntityFridge tileEntity;
    protected float lastTemperature;
    protected int lastBurnTime;
    protected int lastItemBurnTime;

    public ContainerFridge(InventoryPlayer inventoryPlayer, TileEntityFridge te) {
        tileEntity = te;

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen

        int row, col;
        int colsPerRow = 4;
        for (int i = 0; i < inventorySize; i++) {
            col = i % colsPerRow;
            row = i / colsPerRow;
            addSlotToContainer(new Slot(tileEntity, i, 8 + col * 18, 13 + row * 18));
        }

        addSlotToContainer(new Slot(tileEntity, te.fuelSlot, 102, 69));

        //addSlotToContainer(new Slot(tileEntity, 0, 76, 37));

        //commonly used vanilla code that adds the player's inventory
        bindPlayerInventory(inventoryPlayer);
    }

    public void updateCraftingResults() {
        super.updateCraftingResults();
        Iterator var1 = this.crafters.iterator();

        while (var1.hasNext()) {
            ICrafting var2 = (ICrafting) var1.next();

            if (this.lastTemperature != this.tileEntity.temperature) {
                var2.sendProgressBarUpdate(this, 0, Math.round(this.tileEntity.temperature * 10));
            }

            if (this.lastBurnTime != this.tileEntity.burnTime) {
                var2.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
            }

            if (this.lastItemBurnTime != this.tileEntity.burnItemTime) {
                var2.sendProgressBarUpdate(this, 2, this.tileEntity.burnItemTime);
            }
        }

        this.lastTemperature = this.tileEntity.temperature;
        this.lastBurnTime = this.tileEntity.burnTime;
        this.lastItemBurnTime = this.tileEntity.burnItemTime;
    }

    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            tileEntity.temperature = par2 / 10F;
        }

        if (par1 == 1) {
            tileEntity.burnTime = par2;
        }

        if (par1 == 2) {
            tileEntity.burnItemTime = par2;
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }


    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int yshift = 29;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18 + yshift));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142 + yshift));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot)
    {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot >= 0 && slot <= inventorySize + 1) {
                if (!mergeItemStack(stackInSlot, inventorySize + 1, inventorySlots.size(), true)) {
                    return null;
                }
                //places it into the tileEntity is possible since its in the player inventory
            } else if (!mergeItemStack(stackInSlot, 0, inventorySize + 1, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }
        }

        return stack;
    }
}