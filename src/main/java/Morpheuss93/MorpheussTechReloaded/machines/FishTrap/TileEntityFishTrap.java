package Morpheuss93.MorpheussTechReloaded.machines.FishTrap;

import java.util.ArrayList;
import java.util.Random;

import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.machines.MachinesHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by stefano on 30/12/15.
 */
public class TileEntityFishTrap extends TileEntity implements ISidedInventory{

    private static final int[]slotsTop=new int[]{0};
    private static final int[]slotsBottom=new int[]{2,1};
    private static final int[] slotsSides=new int[]{1};


    private ItemStack[] furnaceItemStacks=new ItemStack[3];//TODO capire perch�
    public int furnaceBurnTime;
    public int currentBurnTime;//per quanto brucia il carburante

    public int furnaceCookTime;

    private String furnaceName;

    private boolean validPosition=false;

    public void furnaceName(String string){
        this.furnaceName=string;
    }

	/*public int getSizeInventory(){
		return this.furnaceItemStacks.length;
	}*/

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.furnaceItemStacks[par1] != null) {
            ItemStack itemstack;
            if (this.furnaceItemStacks[par1].stackSize <= par2) {
                itemstack = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                return itemstack;
            } else {
                itemstack = this.furnaceItemStacks[par1].splitStack(par2);

                if (this.furnaceItemStacks[par1].stackSize == 0) {
                    this.furnaceItemStacks[par1] = null;
                }
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.furnaceName:"Fish Trap";
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;//da sistemare
    }

    public void readFromNBT(NBTTagCompound tagCompound){
        super.readFromNBT(tagCompound);
        NBTTagList tagList=tagCompound.getTagList("Items", 10);//TODO qui
        this.furnaceItemStacks=new ItemStack[this.getSizeInventory()];

        for(int i=0;i<tagList.tagCount();++i){
            NBTTagCompound tagCompound1=tagList.getCompoundTagAt(i);
            byte byte0=tagCompound1.getByte("Slot");

            if(byte0 >= 0 && byte0 <this.furnaceItemStacks.length ){
                this.furnaceItemStacks[byte0]=ItemStack.loadItemStackFromNBT(tagCompound1);
            }
        }

        this.furnaceBurnTime=tagCompound.getShort("BurnTime");
        this.furnaceCookTime=tagCompound.getShort("CookTime");
        //this.currentBurnTime=getItemBurnTime(this.furnaceItemStacks[1]);
        this.currentBurnTime=tagCompound.getShort("currentBurnTime");//camb
        if(tagCompound.hasKey("CustomName",8)){
            this.furnaceName=tagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound tagCompound){
        super.writeToNBT(tagCompound);
        tagCompound.setShort("BurnTime",(short)this.furnaceBurnTime);
        tagCompound.setShort("CookTime",(short)this.furnaceCookTime);
        tagCompound.setShort("currentBurnTime",(short)this.currentBurnTime);//camb

        NBTTagList tagList=new NBTTagList();

        for(int i=0;i<this.furnaceItemStacks.length;++i){
            if(this.furnaceItemStacks[i]!=null){
                NBTTagCompound tagCompound1=new NBTTagCompound();
                tagCompound1.setByte("Slot",(byte) i);
                this.furnaceItemStacks[i].writeToNBT(tagCompound1);
                tagList.appendTag(tagCompound1);
            }
        }

        tagCompound.setTag("Items", tagList);

        if(this.hasCustomInventoryName()){
            tagCompound.setString("CustomName",this.furnaceName);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1){
        return this.furnaceCookTime*par1/200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemaningScaled(int par1){
        if(this.currentBurnTime == 0){
            this.currentBurnTime=200;
        }

        return this.furnaceBurnTime*par1 /this.currentBurnTime;
        //return this.furnaceBurnTime*par1/this.furnaceBurnTime;
    }

    public boolean isBurning(){
        return this.furnaceBurnTime>0;
    }

    public void updateEntity(){
        boolean flag=this.furnaceBurnTime>0;
        boolean flag1=false;

        if(this.furnaceBurnTime>0){
            --this.furnaceBurnTime;
        }

        if(!this.worldObj.isRemote){
            if(this.furnaceBurnTime==0 && this.canSmelt()){
                ;
                if(this.furnaceItemStacks[0]!=null){
                    if(this.furnaceItemStacks[0].getItem()== AgricultureHandler.corn){
                        this.currentBurnTime=200;
                        this.furnaceBurnTime=200;
                    }
                }

                if(this.furnaceBurnTime>0){
                    flag1=true;
                    if(this.furnaceItemStacks[0]!=null){//carburante
                        --this.furnaceItemStacks[0].stackSize;
                    }

                    if(this.furnaceItemStacks[0]!=null)
                        if(this.furnaceItemStacks[0].stackSize==0){
                            this.furnaceItemStacks[0]=furnaceItemStacks[0].getItem().getContainerItem(this.furnaceItemStacks[0]);
                        }
                }
            }

            if(this.isBurning() && this.canSmelt()){
                ++this.furnaceCookTime;
                if(this.furnaceCookTime==200){
                    this.furnaceCookTime=0;
                    this.smeltItem();
                    flag1=true;
                }
            }else{
                this.furnaceCookTime=0;
            }
        }

        if(flag !=this.furnaceBurnTime>0){
            flag1=true;
            FishTrap.updateBlockState(this.furnaceBurnTime>0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            //AlloyFurnace.updateBlockState(this.furnaceBurnTime>0, this.worldObj, this.xCoord,this.yCoord , this.zCoord);
        }

        if(flag1){
            this.markDirty();
        }
    }


    /*private boolean canSmelt(){
        if(this.furnaceItemStacks[0]==null){
            return false;
        }else{
            ItemStack itemstack=FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
            if(itemstack==null) return false;
            if(this.furnaceItemStacks[10] == null) return true; //output slot
            if(!this.furnaceItemStacks[10].isItemEqual(itemstack)) return false;//10 2
            int result=this.furnaceItemStacks[10].stackSize+itemstack.stackSize;
            return result <= getInventoryStackLimit() && result<=this.furnaceItemStacks[10].getMaxStackSize();
        }
    }*/
    private boolean canSmelt(){
        validPosition=isInWater(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        if(!validPosition)
            return false;
        //TODO Controllare rete, slot per tipi diversi di pesce controllare slot liberi per il can smelt
        if(this.furnaceItemStacks[1]!=null)
        {
            if(this.furnaceItemStacks[1].getItem()== MachinesHandler.fishingNet){//TODO aggiungere rete//NON NULL
                if(this.furnaceItemStacks[2]!=null){
                    ItemStack itemStack=this.furnaceItemStacks[2];
                    //System.out.println(this.furnaceItemStacks[1].getItemDamage());
                    if(itemStack.stackSize<= itemStack.getMaxStackSize())
                        return true;
                }
                else
                    return true;
            }
        }
        return false;
    }

    public void smeltItem(){

        Random random=new Random();
        ArrayList temp=new ArrayList();

        if(this.canSmelt()){
            int r=random.nextInt(100);

            if(r<50)
            {
                ItemStack itemstack=new ItemStack(Items.fish,1,1);

                this.furnaceItemStacks[1].setItemDamage(this.furnaceItemStacks[1].getItemDamage()+1);
                if(this.furnaceItemStacks[1].getItemDamage()==this.furnaceItemStacks[1].getItem().getMaxDamage())
                    this.furnaceItemStacks[1]=null;

                if(this.furnaceItemStacks[2]==null){
                    this.furnaceItemStacks[2]=itemstack.copy();
                }else if(this.furnaceItemStacks[2].getItem()==itemstack.getItem()){
                    this.furnaceItemStacks[2].stackSize+=itemstack.stackSize;
                }
            }
        }

    }



    public static int getItemBurnTime(ItemStack itemstack){
        if(itemstack==null){
            return 0;
        }else{
            Item item=itemstack.getItem();//se non va l'altro import

            if(item instanceof ItemBlock && Block.getBlockFromItem(item) !=Blocks.air){
                Block block=Block.getBlockFromItem(item);
				/*
				if(block== BlockHandler.copperOre){ //da cambiare, solo per test
					return 200;
				}*/

                if(block.getMaterial() == Material.wood){
                    return 300;
                }
            }

			/*if(item == ItemsHandler.copperIngot) return 1600;
			if(item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().endsWith("EMERALD")) return 300;*/
            if(item==Items.stick) return 100;
            if(item==Items.coal) return 1600;
            return GameRegistry.getFuelValue(itemstack);

        }
    }

    public static boolean isItemFuel(ItemStack itemstack){
        return getItemBurnTime(itemstack) > 0;
    }

    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.furnaceItemStacks[slot];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(this.furnaceItemStacks[slot]!=null){
            ItemStack itemstack=this.furnaceItemStacks[slot];
            this.furnaceItemStacks[slot]=null;
            return itemstack;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.furnaceName!=null && this.furnaceName.length()>0;
    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack itemstack) {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(itemstack) :true);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ?false :player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord +0.5D, (double) this.zCoord+0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        this.furnaceItemStacks[slot]=itemstack;

        if(itemstack!=null && itemstack.stackSize > this.getInventoryStackLimit()){
            itemstack.stackSize=this.getInventoryStackLimit();
        }

    }

    @Override
    public boolean canExtractItem(int par1, ItemStack itemstack, int par3) {
        return par1 !=0 || par1 !=1 || itemstack.getItem()==Items.bucket;
    }

    @Override
    public boolean canInsertItem(int par1, ItemStack itemstack, int par3) {
        return this.isItemValidForSlot(par1, itemstack);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slotsBottom : (par1==1 ?slotsTop:slotsSides);
    }

    public void setValidPosition(boolean valid){
        validPosition=valid;
    }

    public boolean isInWater(World world,int x,int y,int z){
        boolean valid=true;

        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(!(i==0 && j==0)){
                    if(world.getBlock(x+i, y, z+j)!=Blocks.water){
                        valid=false;
                        break;
                    }
                }
            }

            if(!valid)
                return valid;
        }


        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(world.getBlock(x+i, y+1, z+j)!=Blocks.water){
                    valid=false;
                    break;
                }
            }

            if(!valid)
                return valid;
        }

        return valid;

    }
}
