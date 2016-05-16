package Morpheuss93.MorpheussTechReloaded.tools;

import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.blocks.BlockHandler;
import Morpheuss93.MorpheussTechReloaded.ores.OresHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by stefano on 30/12/15.
 */
public class ToolsHandler {

    public static Machete macheteStone,macheteIron,macheteGold,macheteDiamond,macheteBronze,macheteBrass,macheteWood,macheteSteel;
    public static Knife knife;
    public static MagicMirror magicMirror;

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

        knife=new Knife();
        GameRegistry.registerItem(knife, Reference.MODID+"-"+knife.getUnlocalizedName().substring(5));
        knife.setContainerItem(knife);

        magicMirror=new MagicMirror();
        GameRegistry.registerItem(magicMirror,Reference.MODID+"-"+magicMirror.getUnlocalizedName().substring(5));
    }

    public static void configureRecipes(){
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(magicMirror),new Object[]{" d ","ava"," s ",'d', Items.diamond,'a',"ingotEnderAlloy",'v', BlockHandler.enderCoatedGlass,'s',"ingotSilver"}));
    }
}
