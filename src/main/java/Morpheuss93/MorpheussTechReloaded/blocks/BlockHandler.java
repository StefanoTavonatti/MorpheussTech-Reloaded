package Morpheuss93.MorpheussTechReloaded.blocks;

import Morpheuss93.MorpheussTechReloaded.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by stefano on 10/05/16.
 */
public class BlockHandler {

    public static EnderCoatedGlass enderCoatedGlass;

    public static void configureBlocks(){

        enderCoatedGlass=new EnderCoatedGlass();
        GameRegistry.registerBlock(enderCoatedGlass, Reference.MODID+"-"+enderCoatedGlass.getUnlocalizedName().substring(5));


    }
}
