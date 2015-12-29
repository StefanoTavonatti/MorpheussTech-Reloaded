package Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace;

import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.AlloyFurnace;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
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
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;

/**
 * Created by stefano on 29/12/15.
 */
public class TileEntityAlloyFurnace extends TileEntity implements ISidedInventory {
    private static final int[]slotsTop=new int[]{0};
    private static final int[]slotsBottom=new int[]{2,1};
    private static final int[] slotsSides=new int[]{1};


    protected ItemStack[] furnaceItemStacks=new ItemStack[11];
    public int furnaceBurnTime;
    public int currentBurnTime;//per quanto brucia il carburante

    public int furnaceCookTime;

    private String furnaceName;

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
        return this.hasCustomInventoryName() ? this.furnaceName:"Alloy Furnace";
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;//da sistemare
    }

    public void readFromNBT(NBTTagCompound tagCompound){
        super.readFromNBT(tagCompound);
        NBTTagList tagList=tagCompound.getTagList("Items", 10);
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
                this.currentBurnTime=this.furnaceBurnTime=getItemBurnTime(this.furnaceItemStacks[9]);

                if(this.furnaceBurnTime>0){
                    flag1=true;
                    if(this.furnaceItemStacks[9]!=null){
                        --this.furnaceItemStacks[9].stackSize;
                    }

                    if(this.furnaceItemStacks[9].stackSize==0){
                        this.furnaceItemStacks[9]=furnaceItemStacks[9].getItem().getContainerItem(this.furnaceItemStacks[9]);
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
            AlloyFurnace.updateBlockState(this.furnaceBurnTime>0, this.worldObj, this.xCoord,this.yCoord , this.zCoord);
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
    protected boolean canSmelt(){

        ArrayList temp=new ArrayList();
        boolean vuoto=true;
        for(int i=0;i<9;i++){
            if(furnaceItemStacks[i]!=null)
            {
                vuoto=false;
                temp.add(furnaceItemStacks[i]);
            }
        }

        if(vuoto){
            return false;
        }else{
            ItemStack itemstack=AlloyFurnaceRecipes.SMELTING_BASE.getSmeltingResult(temp);
            if(itemstack==null) return false;
            if(this.furnaceItemStacks[10] == null) return true; //output slot
            if(!this.furnaceItemStacks[10].isItemEqual(itemstack)) return false;//10 2
            int result=this.furnaceItemStacks[10].stackSize+itemstack.stackSize;
            return result <= getInventoryStackLimit() && result<=this.furnaceItemStacks[10].getMaxStackSize();
        }
    }

    public void smeltItem(){

        ArrayList temp=new ArrayList();
        boolean vuoto=true;
        for(int i=0;i<9;i++){
            if(furnaceItemStacks[i]!=null)
            {
                vuoto=false;
                temp.add(furnaceItemStacks[i]);
            }
        }

        if(this.canSmelt()){
            ItemStack itemstack=AlloyFurnaceRecipes.SMELTING_BASE.getSmeltingResult(temp);

            if(this.furnaceItemStacks[10]==null){
                this.furnaceItemStacks[10]=itemstack.copy();
            }else if(this.furnaceItemStacks[10].getItem()==itemstack.getItem()){
                this.furnaceItemStacks[10].stackSize+=itemstack.stackSize;
            }

            for(int i=0;i<9;i++){
                if(this.furnaceItemStacks[i]!=null)
                {
                    --this.furnaceItemStacks[i].stackSize;

                    if(this.furnaceItemStacks[i].stackSize <= 0){
                        this.furnaceItemStacks[i] = null;
                    }
                }
            }

        }

		/*if(this.canSmelt()){
			ItemStack itemstack=FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

			if(this.furnaceItemStacks[10]==null){
				this.furnaceItemStacks[10]=itemstack.copy();
			}else if(this.furnaceItemStacks[10].getItem()==itemstack.getItem()){
				this.furnaceItemStacks[10].stackSize+=itemstack.stackSize;
			}

			--this.furnaceItemStacks[0].stackSize;

			if(this.furnaceItemStacks[0].stackSize <= 0){
				this.furnaceItemStacks[0] = null;
			}

		}*/
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
}
