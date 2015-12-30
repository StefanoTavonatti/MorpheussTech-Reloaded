package Morpheuss93.MorpheussTechReloaded.agriculture;

import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.crops.*;
import Morpheuss93.MorpheussTechReloaded.agriculture.crops.corn.CornCrop;
import Morpheuss93.MorpheussTechReloaded.agriculture.crops.corn.CornUp;
import Morpheuss93.MorpheussTechReloaded.agriculture.crops.cotton.CottonCrop;
import Morpheuss93.MorpheussTechReloaded.agriculture.crops.cotton.CottonUp;
import Morpheuss93.MorpheussTechReloaded.agriculture.foods.*;
import Morpheuss93.MorpheussTechReloaded.agriculture.seeds.AnanasSeed;
import Morpheuss93.MorpheussTechReloaded.agriculture.seeds.Corn;
import Morpheuss93.MorpheussTechReloaded.agriculture.seeds.CottonSeed;
import Morpheuss93.MorpheussTechReloaded.craftingManagers.ShaplessRecipeDamages;
import Morpheuss93.MorpheussTechReloaded.tools.ToolsHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 30/12/15.
 */
public class AgricultureHandler {

    public final static Item ananas=new Ananas();
    public final static Item rottenStuff=new RottenStuff();
    public final static Item pineappleSlice=new PineappleSlice();
    public final static Item corncob=new Corncob();
    public final static Item popcorn=new Popcorn();

    public static AnanasSeed ananasSeed;
    public static CottonSeed cottonSeed;
    public static Corn corn;

    public final static Block ananasPlant=new AnanasPlant();
    public final static Block cottonCrop=new CottonCrop();
    public final static Block cottonUp=new CottonUp();
    public final static Block cornCrop=new CornCrop();
    public final static Block cornUp=new CornUp();


    public static void configureCrops(){

        GameRegistry.registerBlock(ananasPlant, Reference.MODID+"-"+"AnanasPlant");
        GameRegistry.registerBlock(cottonCrop, Reference.MODID+"-"+"CottonCrop");
        GameRegistry.registerBlock(cottonUp, Reference.MODID+"-"+"CottonUp");
        GameRegistry.registerBlock(cornCrop, Reference.MODID+"-"+"CornCrop");
        GameRegistry.registerBlock(cornUp, Reference.MODID+"-"+"CornUp");

    }

    public static void configureFood(){
        GameRegistry.registerItem(ananas, Reference.MODID+"-"+ananas.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(rottenStuff, Reference.MODID+"-"+rottenStuff.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(pineappleSlice, Reference.MODID+"-"+pineappleSlice.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(corncob, Reference.MODID+"-"+corncob.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(popcorn, Reference.MODID+"-"+popcorn.getUnlocalizedName().substring(5));
    }

    public static void configureSeed(){

        ananasSeed=new AnanasSeed();
        GameRegistry.registerItem(ananasSeed, Reference.MODID+"-"+ananasSeed.getUnlocalizedName().substring(5));

        cottonSeed=new CottonSeed();
        GameRegistry.registerItem(cottonSeed, Reference.MODID+"-"+cottonSeed.getUnlocalizedName().substring(5));

        corn=new Corn();
        GameRegistry.registerItem(corn, Reference.MODID+"-"+corn.getUnlocalizedName().substring(5));


        //configureRecipe();//TODO recipes
    }

    public static void configureRecipes(){
        List l=new ArrayList();
        l.add(new ItemStack(corncob));
        GameRegistry.addRecipe(new ShaplessRecipeDamages(new ItemStack(corn,1),l));


        GameRegistry.addSmelting(corn, new ItemStack(popcorn), 0.8F);

        List l2=new ArrayList();
        l2.add(new ItemStack(ananas));
        l2.add(new ItemStack(ToolsHandler.knife));
        GameRegistry.addRecipe(new ShaplessRecipeDamages(new ItemStack(pineappleSlice,6),l2));
    }
}
