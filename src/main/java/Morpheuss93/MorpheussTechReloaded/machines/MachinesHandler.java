package Morpheuss93.MorpheussTechReloaded.machines;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered.AlcoholAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered.TileEntityAlcoholAlloyFurnace;
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
    public static AlcoholAlloyFurnace alcoholAlloyFurnace,alcoholAlloyFurnaceActive;

    public static void configureMachine(){

        alloyFurnace=new AlloyFurnace(false).setBlockName("AlloyFurnace");
        alloyFurnaceActive=new AlloyFurnace(true).setBlockName("AlloyFurnaceActive");
        GameRegistry.registerBlock(alloyFurnace, Reference.MODID+"-"+alloyFurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(alloyFurnaceActive, Reference.MODID+"-"+alloyFurnaceActive.getUnlocalizedName().substring(5));
        alloyFurnace.setCreativeTab(MorpheussTechReloaded.tabMachine);

        alcoholAlloyFurnace=(AlcoholAlloyFurnace) new AlcoholAlloyFurnace(false).setBlockName("AlcoholAlloyFurnace");
        alcoholAlloyFurnaceActive=(AlcoholAlloyFurnace) new AlcoholAlloyFurnace(true).setBlockName("AlcoholAlloyFurnaceActive");
        GameRegistry.registerBlock(alcoholAlloyFurnace, Reference.MODID+"_"+alcoholAlloyFurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(alcoholAlloyFurnaceActive, Reference.MODID+"_"+alcoholAlloyFurnaceActive.getUnlocalizedName().substring(5));
        alcoholAlloyFurnace.setCreativeTab(MorpheussTechReloaded.tabMachine);


    }

    public static void configureTileEntity(){
        GameRegistry.registerTileEntity(TileEntityAlloyFurnaceBasic.class, Reference.MODID+"TileEntityAlloyFurnaceBasic");
        GameRegistry.registerTileEntity(TileEntityAlcoholAlloyFurnace.class, Reference.MODID+"TileEntityAlcoholAlloyFurnace");
    }
}
