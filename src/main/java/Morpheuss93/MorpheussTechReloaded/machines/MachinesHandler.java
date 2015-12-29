package Morpheuss93.MorpheussTechReloaded.machines;

import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.AlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.TileEntityAlloyFurnaceBasic;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Created by stefano on 29/12/15.
 */
public class MachinesHandler {

    public static Block alloyFurnace;
    public static Block alloyFurnaceActive;

    public static void configureMachine(){

        alloyFurnace=new AlloyFurnace(false).setBlockName("AlloyFurnace");
        alloyFurnaceActive=new AlloyFurnace(true).setBlockName("AlloyFurnaceActive");
        GameRegistry.registerBlock(alloyFurnace, Reference.MODID+"-"+alloyFurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(alloyFurnaceActive, Reference.MODID+"-"+alloyFurnaceActive.getUnlocalizedName().substring(5));

    }

    public static void configureTileEntity(){
        GameRegistry.registerTileEntity(TileEntityAlloyFurnaceBasic.class, Reference.MODID+"TileEntityAlloyFurnaceBasic");
    }
}
