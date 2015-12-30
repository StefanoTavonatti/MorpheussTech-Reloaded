package Morpheuss93.MorpheussTechReloaded.tools;

import Morpheuss93.MorpheussTechReloaded.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by stefano on 30/12/15.
 */
public class ToolsHandler {

    public static Machete macheteStone,macheteIron,macheteGold,macheteDiamond,macheteBronze,macheteBrass,macheteWood,macheteSteel;

    public static void configureTools(){
        macheteStone=new Machete(Item.ToolMaterial.STONE,"MacheteStone");
        GameRegistry.registerItem(macheteStone, Reference.MODID+"-"+macheteStone.getUnlocalizedName().substring(5));

        macheteIron=new Machete(Item.ToolMaterial.IRON,"MacheteIron");
        GameRegistry.registerItem(macheteIron, Reference.MODID+"-"+macheteIron.getUnlocalizedName().substring(5));

        macheteGold=new Machete(Item.ToolMaterial.GOLD, "MacheteGold");
        GameRegistry.registerItem(macheteGold,Reference.MODID+"-"+macheteGold.getUnlocalizedName().substring(5));

        macheteDiamond=new Machete(Item.ToolMaterial.EMERALD, "MacheteDiamond");
        GameRegistry.registerItem(macheteDiamond, Reference.MODID+"-"+macheteDiamond.getUnlocalizedName().substring(5));

        macheteBronze=new Machete(Materials.MaterialBronze,"MacheteBronze");
        GameRegistry.registerItem(macheteBronze,Reference.MODID+"-"+macheteBronze.getUnlocalizedName().substring(5));

        macheteBrass=new Machete(Materials.MaterialBrass,"MacheteBrass");
        GameRegistry.registerItem(macheteBrass, Reference.MODID+"-"+macheteBrass.getUnlocalizedName().substring(5));

        macheteWood=new Machete(Item.ToolMaterial.WOOD,"MacheteWood");
        GameRegistry.registerItem(macheteWood, Reference.MODID+"-"+macheteWood.getUnlocalizedName().substring(5));

        macheteSteel=new Machete(Materials.MaterialSteel, "MacheteSteel");
        GameRegistry.registerItem(macheteSteel, Reference.MODID+"-"+macheteSteel.getUnlocalizedName().substring(5));
    }
}
