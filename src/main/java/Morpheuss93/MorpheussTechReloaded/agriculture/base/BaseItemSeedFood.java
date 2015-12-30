package Morpheuss93.MorpheussTechReloaded.agriculture.base;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by stefano on 30/12/15.
 */
public class BaseItemSeedFood extends ItemFood implements IPlantable {

    private final Block theBlockPlant;

    private final Block soilId;//suolo su cui verr� piantato



    public BaseItemSeedFood(int parHealAmount, float parSaturationModifier, Block parBlockPlant, Block parSoilBlock) {
        super(parHealAmount, parSaturationModifier, false);
        theBlockPlant = parBlockPlant;
        soilId = parSoilBlock;


    }

    @Override
    public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer,
                             World parWorld, int parX, int parY, int parZ, int par7, float par8,
                             float par9, float par10){
        if(par7!=1){
            return false;
        }
        else if(parPlayer.canPlayerEdit(parX, parY+1, parZ, par7, parItemStack)){

            //controllo se il suolo va bene e se c'� aria sopra
            if (parWorld.getBlock(parX, parY, parZ).canSustainPlant(parWorld,
                    parX, parY, parZ, ForgeDirection.UP, (IPlantable) this) && parWorld
                    .isAirBlock(parX, parY+1, parZ))
            {// place the plant block
                parWorld.setBlock(parX, parY+1, parZ, theBlockPlant);
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

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {

        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return theBlockPlant;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {

        return 0;
    }

    public Block getSoilId(){
        return soilId;
    }



}