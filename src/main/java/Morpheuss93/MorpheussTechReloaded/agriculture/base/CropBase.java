package Morpheuss93.MorpheussTechReloaded.agriculture.base;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by stefano on 30/12/15.
 */
public class CropBase extends BlockCrops implements IGrowable{
    @SideOnly(Side.CLIENT)
    protected IIcon[] iIcon;

    public Item theSeed;

    public CropBase() {
        setTickRandomly(true);
        float f=0.5F;
        setBlockBounds(0.5F-f, 0.0F, 0.0F-f, 0.5F+f, 0.25F, 0.5F+f);
        setCreativeTab((CreativeTabs)null);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        disableStats();
    }

    @Override
    protected boolean canPlaceBlockOn(Block parBlock) {

        return parBlock==Blocks.farmland;
    };

    public void incrementGrowStage(World parWorld, int parX, int parY, int parZ){
        int growStage=parWorld.getBlockMetadata(parX, parY, parZ) + MathHelper.getRandomIntegerInRange(parWorld.rand, 2,5);
        //int growStage=parWorld.getBlockMetadata(parX, parY, parZ) +1;
        if(growStage>7)
        {
            growStage=7;
        }

        parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
    }

    @Override
    public net.minecraft.item.Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this);
    };

    @Override
    public int getRenderType() {
        return 1;
    };

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int parSide, int parGrowthStage) {
        return iIcon[parGrowthStage];
    };


    @Override // checks if finished growing (a grow stage of 7 is final stage)
    public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {

        return parWorld.getBlockMetadata(parX, parY, parZ) != 7;

    }

    @Override
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_,
                                 int p_149852_3_, int p_149852_4_, int p_149852_5_) {

        return true;
    }

    @Override
    public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ)
    {

        incrementGrowStage(parWorld, parX, parY, parZ);
    }

}
