package Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.machines.MachinesHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by stefano on 29/12/15.
 */
public class AlloyFurnace extends BlockContainer {

    @SideOnly(Side.CLIENT)
    protected IIcon top;

    @SideOnly(Side.CLIENT)
    protected IIcon front;


    protected static boolean isBurning;
    protected final boolean isBurning2;
    private final Random random=new Random();

    public AlloyFurnace(boolean isActive) {
        super(Material.rock);

        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        isBurning2=isActive;

        if(isActive){
            this.setLightLevel(1.0F);
        }

        this.setHardness(1.0F);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister){
        this.blockIcon=iconregister.registerIcon(Reference.MODID+":AlloyFurnaceSide");
        this.front=iconregister.registerIcon(this.isBurning2 ? Reference.MODID+":AlloyFurnaceActive" : Reference.MODID+":AlloyFurnaceInactive");
        this.top=iconregister.registerIcon(Reference.MODID+":AlloyFurnaceTop");
    }

	/*public IIcon getIcon(int side,int meta){

		if(side==1){
			return top;
		}else if(side==3){
			return front;
		}else{
			return this.blockIcon;
		}
	}*/

    public IIcon getIcon(int side,int meta){

        return side == 1 ? this.top : ( side ==0? this.top : (side!=meta?this.blockIcon:this.front));
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        player.openGui(MorpheussTechReloaded.instance, 0, world, x, y, z);
        return true;
    }

    public Item getItemDropped(int par1, Random random, int par3){
        return Item.getItemFromBlock(MachinesHandler.alloyFurnace);
    }

    public Item getItem(World world,int par2,int par3,int par4){
        return Item.getItemFromBlock(MachinesHandler.alloyFurnace);
    }

    @SideOnly(Side.CLIENT)
    public void onBlockAdded(World world,int x,int y,int z ){
        super.onBlockAdded(world, x, y, z);
        //this.direction(world,x,y,z);
        this.dir2(world, x, y, z);
    }

    private void direction(World world, int x, int y, int z) {
        if(!world.isRemote)
        {
            Block direction=world.getBlock(x, y, z-1);
            Block direction1=world.getBlock(x, y, z+1);
            Block direction2=world.getBlock(x -1, y, z);
            Block direction3=world.getBlock(x +1, y, z);
            byte byte0=3;

            if(direction.func_149730_j() && !direction1.func_149730_j()){
                byte0=3;
            }

            if(direction1.func_149730_j() && !direction.func_149730_j()){
                byte0=2;
            }

            if(direction2.func_149730_j() && !direction3.func_149730_j()){
                byte0=5;
            }

            if(direction3.func_149730_j() && !direction2.func_149730_j()){
                byte0=4;
            }

            world.setBlockMetadataWithNotify(x, y, z, byte0, 2);
        }
    }


    private void dir2(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
    {
        if (!p_149930_1_.isRemote)
        {
            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
        }
    }

    @Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntityFurnace)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }

	/*public void onBlockPlaceBy(World world,int x,int y,int z,EntityLivingBase entity,ItemStack itemstack){
		int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (direction == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (direction == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (direction == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (direction == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (itemstack.hasDisplayName()) {
			((TileEntityAlloyFurnace) world.getTileEntity(x, y, z)).furnaceName(itemstack.getDisplayName());
		}


	}*/

    public static void updateBlockState(boolean burning,World world,int x,int y,int z){
        int direction =world.getBlockMetadata(x, y, z);
        TileEntity tileentity=world.getTileEntity(x, y, z);
        isBurning=true;

        if(burning){
            world.setBlock(x, y, z, MachinesHandler.alloyFurnaceActive);//TODO cambiare con blocco generico
        }else{
            world.setBlock(x, y, z, MachinesHandler.alloyFurnace);
        }

        isBurning=false;

        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
        if(tileentity !=null){
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }

    public void breakBlock(World world,int x,int y,int z,Block block,int meta){
        if(!isBurning){
            TileEntityAlloyFurnaceBasic tileEntityAlloyFurnace =(TileEntityAlloyFurnaceBasic)world.getTileEntity(x, y, z);
            if(tileEntityAlloyFurnace!=null){
                for(int i=0;i<tileEntityAlloyFurnace.getSizeInventory();++i)
                {
                    ItemStack itemstack =tileEntityAlloyFurnace.getStackInSlot(i);
                    if(itemstack!=null){
                        float f=this.random.nextFloat()*0.6F+0.1F;
                        float f1=this.random.nextFloat()*0.6F+0.1F;
                        float f2=this.random.nextFloat()*0.6F+0.1F;

                        while(itemstack.stackSize>0){
                            int j = this.random.nextInt(21)+10;

                            if(j>itemstack.stackSize){
                                j=itemstack.stackSize;
                            }

                            itemstack.stackSize-=j;
                            EntityItem entityitem=new EntityItem(world,(double)((float) x+f),(double) ((float) y +f1), (double)((float)z+2),new ItemStack(itemstack.getItem(),j,itemstack.getItemDamage()));

                            if(itemstack.hasTagCompound()){
                                entityitem.getEntityItem().setTagCompound(((NBTTagCompound) itemstack.getTagCompound().copy()));
                            }

                            float f3=0.025F;
                            entityitem.motionX=(double)((float)this.random.nextGaussian()*f3);
                            entityitem.motionY=(double)((float)this.random.nextGaussian()*f3+0.1F);
                            entityitem.motionZ=(double)((float)this.random.nextGaussian()*f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }
                world.func_147453_f(x, y, z, block);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

	/*@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world,int x,int y,int z,Random random){
		if(this.isBurning2){
			int direction=world.getBlockMetadata(x, y, z);

			float xx=(float) x+ 0.5F,yy=(float) y+random.nextFloat()*6.0F/16.0F,zz=(float) z+0.5F,xx2=random.nextFloat() * 0.3F - 0.2F, zz2 = 0.5F;

			if (direction == 4) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 5) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 3) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 2) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			}
		}
	}*/

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
        if (this.isBurning2)
        {
            int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
            float f = (float)p_149734_2_ + 0.5F;
            float f1 = (float)p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)p_149734_4_ + 0.5F;
            float f3 = 0.52F;
            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                p_149734_1_.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                p_149734_1_.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
        return new TileEntityAlloyFurnaceBasic();
    }
}
