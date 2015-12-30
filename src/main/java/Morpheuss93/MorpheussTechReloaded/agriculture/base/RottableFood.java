package Morpheuss93.MorpheussTechReloaded.agriculture.base;

import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by stefano on 30/12/15.
 */
public class RottableFood extends ItemFood {

    public static long old_time[];//a posizione par1
    public static int old_stack_pos=-1;
    public static boolean canRot=true;

    public RottableFood(int par1,float par2, boolean par3) {
        super(par1,par2,par3);
        this.setMaxDamage(25);
        old_time=new long[64];
        for(int i=0;i<64;i++){
            old_time[i]=0;
        }
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par1, boolean par2) {//par1=slot_pos
        super.onUpdate(itemstack, world, entity, par1, par2);//fare un rottable item


        int dam=itemstack.getItemDamage();
        long time=System.currentTimeMillis()/1000;

        if(time%24==0 )
        {

            if(dam <25 &&time!=old_time[par1])
            {
                old_time[par1]=time;
                //old_stack_pos=par1;
                itemstack.setItemDamage(dam+1);
                //System.out.println("Time Millis: "+System.currentTimeMillis());
            }
        }
        else{
            old_stack_pos=-1;
        }

        if(dam==25){
            if(entity instanceof EntityPlayer)
            {
                ItemStack it=new ItemStack(AgricultureHandler.rottenStuff,itemstack.stackSize);
                EntityPlayer player=(EntityPlayer) entity;
                player.inventory.setInventorySlotContents(par1, it);;
            }

        }

    }


}
