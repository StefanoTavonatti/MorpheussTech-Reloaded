package Morpheuss93.MorpheussTechReloaded.machines;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.machines.FishTrap.FishTrap;
import Morpheuss93.MorpheussTechReloaded.machines.FishTrap.TileEntityFishTrap;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered.AlcoholAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered.TileEntityAlcoholAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.AlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.TileEntityAlloyFurnaceBasic;
import Morpheuss93.MorpheussTechReloaded.machines.components.*;
import Morpheuss93.MorpheussTechReloaded.machines.components.consumables.FishingNet;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by stefano on 29/12/15.
 */
public class MachinesHandler {

    public static Block alloyFurnace;
    public static Block alloyFurnaceActive;
    public static AlcoholAlloyFurnace alcoholAlloyFurnace,alcoholAlloyFurnaceActive;
    public static SmallAlcoholBurnerComponent smallAlcoholBurnerComponent;
    public static SmallAlcoholBurnerSupport smallAlcoholBurnerSupport;
    public static SmallIgniter smallIgniter;
    public static SmallAlcoholBurner smallAlcoholBurner;
    public static FishTrap fishTrap;
    public static CottonCloth cottonCloth;
    public static FishingNet fishingNet;

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

        fishTrap=(FishTrap) new FishTrap(false).setBlockName("Fish Trap");
        GameRegistry.registerBlock(fishTrap, fishTrap.getUnlocalizedName().substring(5));

    }

    public static void configureComponents(){
        smallAlcoholBurnerComponent=new SmallAlcoholBurnerComponent();
        GameRegistry.registerItem(smallAlcoholBurnerComponent, smallAlcoholBurnerComponent.getUnlocalizedName().substring(5));

        smallAlcoholBurnerSupport=new SmallAlcoholBurnerSupport();
        GameRegistry.registerItem(smallAlcoholBurnerSupport, smallAlcoholBurnerSupport.getUnlocalizedName().substring(5));

        smallIgniter=new SmallIgniter();
        GameRegistry.registerItem(smallIgniter, smallIgniter.getUnlocalizedName().substring(5));

        smallAlcoholBurner=new SmallAlcoholBurner();
        GameRegistry.registerItem(smallAlcoholBurner, smallAlcoholBurner.getUnlocalizedName().substring(5));

        cottonCloth=new CottonCloth();
        GameRegistry.registerItem(cottonCloth, Reference.MODID+"-"+cottonCloth.getUnlocalizedName().substring(5));

        fishingNet=new FishingNet();
        GameRegistry.registerItem(fishingNet, Reference.MODID+"-"+fishingNet.getUnlocalizedName().substring(5));

    }

    public static void configureTileEntity(){
        GameRegistry.registerTileEntity(TileEntityAlloyFurnaceBasic.class, Reference.MODID+"TileEntityAlloyFurnaceBasic");
        GameRegistry.registerTileEntity(TileEntityAlcoholAlloyFurnace.class, Reference.MODID+"TileEntityAlcoholAlloyFurnace");
        GameRegistry.registerTileEntity(TileEntityFishTrap.class, Reference.MODID+"TileEntityFishTrap");
    }

    public static void configureCrafting(){

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smallAlcoholBurnerComponent), true, new Object[]{"b b","b b","b b",'b',"ingotBronze"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smallAlcoholBurnerSupport), true, new Object[]{"sss","s s","sss",'s',"ingotSteel"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smallIgniter), true, new Object[]{" fi","  i","  i",'i', Items.iron_ingot,'f',Items.flint_and_steel}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(smallAlcoholBurner), true, new Object[]{" i "," c "," s ",'i',smallIgniter,'c',smallAlcoholBurnerComponent,'s',smallAlcoholBurnerSupport}));


        GameRegistry.addShapedRecipe(new ItemStack(alloyFurnace,1), new Object[]{"BFB","B B","BFB",'B', Blocks.brick_block,'F',Blocks.furnace});
        GameRegistry.addShapedRecipe(new ItemStack(alcoholAlloyFurnace,1), new Object[]{" a ","csc","ccc",'a',alloyFurnace,'s',smallAlcoholBurner,'c',Blocks.cobblestone});

        GameRegistry.addShapedRecipe(new ItemStack(cottonCloth,1), new Object[]{"sss","sss","sss",'s',Items.string});
        GameRegistry.addShapedRecipe(new ItemStack(fishingNet), new Object[]{"scs","scs","scs",'s',Items.stick,'c',cottonCloth});

        GameRegistry.addShapedRecipe(new ItemStack(fishTrap), new Object[]{"www","wfw","www",'w',Blocks.planks,'f',fishingNet});
    }
}
