package Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic;

import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.TileEntityAlloyFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Created by stefano on 29/12/15.
 */
public class ContainerAlloyFurnace extends Container { //TODO fare lo stesso della tile entity
    protected TileEntityAlloyFurnace tileFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    protected int xFuel=148;
    protected int yFuel=58;

    public ContainerAlloyFurnace(InventoryPlayer player, TileEntityAlloyFurnace tileEntityFurnace) {
        this.tileFurnace=tileEntityFurnace;

        //this.addSlotToContainer(new Slot(tileEntityFurnace,0,56,17));//position of slot
		/*if(!(tileEntityFurnace instanceof TileEntityAlcoholAlloyFurnace))
		{*/
        if(/*!(tileEntityFurnace instanceof TileEntityAlcoholAlloyFurnace)*/true)//TODO
            this.addSlotToContainer(new Slot(tileEntityFurnace,9,xFuel,yFuel));//combustibile prima 1
        else
            this.addSlotToContainer(new Slot(tileEntityFurnace,9,147,63));
        this.addSlotToContainer(new SlotFurnace(player.player,tileEntityFurnace,10,116,35));//risultato
        int i;

        for(int n=0;n<3;n++)
        {
            for(int m=0;m<3;m++)
            {
                this.addSlotToContainer(new Slot(tileEntityFurnace,m+n*3,22+18*(m),18+17*n));
            }
        }

        for(i=0;i<3;++i){
            for(int j=0;j<9;++j){
                this.addSlotToContainer(new Slot(player,j+i*9+9,8+j*18,84+i*18));
            }
        }

        for(i=0;i<9;++i){
            this.addSlotToContainer(new Slot(player,i, 8+i*18,142));
        }
        //}
    }

    /*public ContainerAlloyFurnace(TileEntityAlcoholAlloyFurnace tileEntityFurnace){//TODO
        this.tileFurnace=tileEntityFurnace;
    }*/

    public void addCraftingToCrafters(ICrafting craft){
        super.addCraftingToCrafters(craft);
        craft.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
        craft.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
        craft.sendProgressBarUpdate(this, 2, this.tileFurnace.currentBurnTime);
    }

    public void detectAndSendChanges(){
        super.detectAndSendChanges();
        for(int i=0;i<this.crafters.size();++i){
            ICrafting craft= (ICrafting)this.crafters.get(i);

            if(this.lastCookTime!=this.tileFurnace.furnaceCookTime){
                craft.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
            }

            if(this.lastBurnTime!=this.tileFurnace.furnaceBurnTime){
                craft.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
            }

            if(this.lastItemBurnTime!=this.tileFurnace.currentBurnTime){
                craft.sendProgressBarUpdate(this, 2, this.tileFurnace.currentBurnTime);
            }
        }

        this.lastBurnTime=this.tileFurnace.furnaceBurnTime;
        this.lastCookTime=this.tileFurnace.furnaceCookTime;
        this.lastItemBurnTime=this.tileFurnace.currentBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1,int par2){
        //System.out.println("par3 "+par1);
        if(par1==0){
            this.tileFurnace.furnaceCookTime=par2;
        }

        if(par1==1){
            this.tileFurnace.furnaceBurnTime=par2;
        }

        if(par1==2){
            this.tileFurnace.currentBurnTime=par2;
        }

		/*if(this.tileFurnace instanceof TileEntityAlcoholAlloyFurnace){
			TileEntityAlcoholAlloyFurnace tileEntityAlcoholAlloyFurnace=(TileEntityAlcoholAlloyFurnace) this.tileFurnace;
			if(par1==3)
				this.tileFurnace.currentBurnTime=par2;
		}*/
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileFurnace.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int par2){
        ItemStack itemstack=null;
        Slot slot=(Slot) this.inventorySlots.get(par2);

        if(slot !=null &&slot.getHasStack()){
            ItemStack itemstack1 =slot.getStack();
            itemstack=itemstack1.copy();

            if(par2==2){
                if(!this.mergeItemStack(itemstack1, 3, 39, true)){
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }else if(par2 !=1 && par2!=0){
                if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1)!=null){
                    if(!this.mergeItemStack(itemstack1, 0, 1, false)){
                        return null;
                    }
                }else if(tileFurnace.isItemFuel(itemstack1)){
                    if(!this.mergeItemStack(itemstack1, 1, 2, false)){
                        return null;
                    }
                }else if(par2 >3 && par2<30){
                    if(!this.mergeItemStack(itemstack1, 30, 39, false)){
                        return null;
                    }
                }else if(par2 >= 30 && par2<39 && !this.mergeItemStack(itemstack1, 3, 39, false)){
                    return null;
                }
            }else if(!this.mergeItemStack(itemstack1, 3, 39, false)){
                return null;
            }

            if(itemstack1.stackSize==0){
                slot.putStack((ItemStack)null);
            }else{
                slot.onSlotChanged();
            }

            if(itemstack1.stackSize == itemstack.stackSize){
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }

}
