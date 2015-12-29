package Morpheuss93.MorpheussTechReloaded.fluids;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by stefano on 29/12/15.
 */
public class FluidsHandler {
    public static Fluid alcohol = new Fluid("Alcohol");
    public static AlcoholBlock alcoholBlock;

    public static Item alcoholBucket;//TODO move in right position

    public static void configureFluids(){
        FluidRegistry.registerFluid(alcohol);
        alcoholBlock = new AlcoholBlock(alcohol, Material.water);
        alcoholBlock.setBlockName("Alcohol");
        //alcohol.setIcons(alcoholBlock.getStillIcon(), alcoholBlock.getFlowIcon());
        GameRegistry.registerBlock(alcoholBlock, Reference.MODID + "_" + alcoholBlock.getUnlocalizedName().substring(5));
        alcohol.setUnlocalizedName(alcoholBlock.getUnlocalizedName());
    }

    public static void configureItems(){

        alcoholBucket = new ItemBucket(alcoholBlock).setUnlocalizedName("AlcoholBucket").setContainerItem(Items.bucket).setTextureName(Reference.MODID+":"+"AlcoholBucket");
        alcoholBucket.setCreativeTab(MorpheussTechReloaded.tabOre);//TODO right tab
        GameRegistry.registerItem(alcoholBucket, Reference.MODID+"_"+"AlcoholBucket");
        FluidContainerRegistry.registerFluidContainer(alcohol,new ItemStack(alcoholBucket),new ItemStack(Items.bucket));
        //gestisco l'evento del fluido che viene raccolto
        BucketHandler.INSTANCE.buckets.put(alcoholBlock, alcoholBucket);
        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

    }
}
