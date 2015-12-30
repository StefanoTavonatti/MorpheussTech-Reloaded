package Morpheuss93.MorpheussTechReloaded.agriculture.crops.cotton;

import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.CropBase;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by stefano on 30/12/15.
 */
public class CottonCrop extends CropBase{

    public CottonCrop() {
        super();
        this.setBlockName("CottonCrop");
        theSeed= AgricultureHandler.cottonSeed;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        //System.out.println("meta= "+meta);
        return meta>=7 ? MathHelper.getRandomIntegerInRange(new Random(), 1, 4) : 0;
    }

    @Override
    public Item getItemDropped(int parMetadata, Random parRand, int parFortune)  {
        //System.out.println("Ananas Dropped ");
        return Items.string;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister parIIconRegister) {

        iIcon=new IIcon[8];


        iIcon[0]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_0");
        iIcon[1]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_1");
        iIcon[2]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_2");
        iIcon[3]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_3");
        iIcon[4]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_4");
        iIcon[5]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_5");
        iIcon[6]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_6");
        iIcon[7]=parIIconRegister.registerIcon(Reference.MODID+":cotton_stage_7");
    }

    @Override
    protected Item func_149866_i() {
        //return super.func_149866_i();
        return AgricultureHandler.cottonSeed;
    }

    public void double_c(World world,int x,int y,int z){
        int meta=world.getBlockMetadata(x, y, z);
        if(meta>=7){
            if(world.getBlock(x, y+1, z)==Blocks.air){
                //world.setBlock(x, y+1, z, Blocks.diamond_block);
                world.setBlock(x, y+1, z, AgricultureHandler.cottonUp);
            }
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z,random);

        double_c(world,x,y,z);
    }

    @Override
    public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ){
        super.func_149853_b(parWorld, parRand, parX, parY, parZ);

        double_c(parWorld,parX,parY,parZ);
    }

    @Override
    public int getRenderType() {
        return 1;
    };


}
