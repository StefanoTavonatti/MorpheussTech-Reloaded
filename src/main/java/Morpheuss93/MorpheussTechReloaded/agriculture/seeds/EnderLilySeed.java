package Morpheuss93.MorpheussTechReloaded.agriculture.seeds;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.SeedBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by stefano on 17/05/16.
 */
public class EnderLilySeed extends SeedBase {

    public EnderLilySeed(){
        super();
        thePlant= AgricultureHandler.enderLily;
        this.setUnlocalizedName("EnderLilySeed");
        this.setTextureName(Reference.MODID+":EnderLilySeed");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
    }

    //TODO edit onItemUse water
    @Override
    public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer,
                             World parWorld, int parX, int parY, int parZ, int par7, float par8,
                             float par9, float par10){
        if(par7!=1){
            return false;
        }
        else if(parPlayer.canPlayerEdit(parX, parY+1, parZ, par7, parItemStack)){

            //check soil
            if (parWorld.getBlock(parX, parY, parZ)== Blocks.water && parWorld
                    .isAirBlock(parX, parY+1, parZ))
            {// place the plant block
                parWorld.setBlock(parX, parY+1, parZ, thePlant);
                // decrement the stack of seed items
                --parItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }else{
            return false;
        }
    }
}
