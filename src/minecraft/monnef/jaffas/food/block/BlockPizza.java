package monnef.jaffas.food.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import monnef.core.BitHelper;
import monnef.jaffas.food.item.JaffaItem;
import monnef.jaffas.food.mod_jaffas;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

import static monnef.jaffas.food.mod_jaffas.blockPizza;
import static monnef.jaffas.food.mod_jaffas.getItem;

public class BlockPizza extends Block {
    public static final int BIT_ROTATION = 3;
    public static final float f2 = 2F / 16F;

    public BlockPizza(int par1, int par2, Material par3Material) {
        super(par1, par2, par3Material);
        setCreativeTab(null);
        setHardness(0.5f);
        setBlockName("blockPizza");
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f2, 1.0F);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double) ((float) par2), (double) par3, (double) ((float) par4), (double) ((float) par2), (double) ((float) par3 + f2), (double) ((float) par4));
    }

    public int setRotation(int meta, boolean direction) {
        return BitHelper.setBitToValue(meta, BIT_ROTATION, direction);
    }

    public boolean getRotation(int meta) {
        return BitHelper.isBitSet(meta, BIT_ROTATION);
    }

    public int setPieces(int meta, int count) {
        boolean rotation = getRotation(meta);
        meta = count;
        return setRotation(meta, rotation);
    }

    public int getPieces(int meta) {
        return meta & 7;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new TileEntityPizza();
    }

    @Override
    public int getRenderType() {
        return mod_jaffas.renderID;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
        par1World.setBlockTileEntity(par2, par3, par4, this.createTileEntity(par1World, par1World.getBlockMetadata(par2, par3, par4)));
    }

    @Override
    public String getTextureFile() {
        return "/jaffas_01.png";
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && super.canPlaceBlockAt(par1World, par2, par3 + 1, par4);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        this.eatPizzaSlice(par1World, par2, par3, par4, par5EntityPlayer);
        return true;
    }

    private void eatPizzaSlice(World world, int x, int y, int z, EntityPlayer player) {
        if (player.canEat(false)) {
            player.getFoodStats().addStats(2, 0.1F);

            int meta = world.getBlockMetadata(x, y, z);

            int slices = blockPizza.getPieces(meta) - 1;

            if (slices > 0) {
                world.setBlockMetadataWithNotify(x, y, z, blockPizza.setPieces(meta, slices));
                world.markBlockForRenderUpdate2(x, y, z);
            } else {
                world.setBlockWithNotify(x, y, z, 0);
            }

            /*
            int var6 = world.getBlockMetadata(x, y, z) + 1;

            if (var6 >= 6)
            {
                world.setBlockWithNotify(x, y, z, 0);
            }
            else
            {
                world.setBlockMetadataWithNotify(x, y, z, var6);
                world.markBlockForRenderUpdate2(x, y, z);
            } */
        }
    }

    //from cake block
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        if (!this.canBlockStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        return par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid();
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return getItem(JaffaItem.pizza).shiftedIndex;
    }
}
