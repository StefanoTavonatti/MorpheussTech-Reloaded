package Morpheuss93.MorpheussTechReloaded;

import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.blocks.BlockHandler;
import Morpheuss93.MorpheussTechReloaded.fluids.FluidsHandler;
import Morpheuss93.MorpheussTechReloaded.machines.MachinesHandler;
import Morpheuss93.MorpheussTechReloaded.ores.OresHandler;
import Morpheuss93.MorpheussTechReloaded.tools.ToolsHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by stefano on 27/12/15.
 */

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class MorpheussTechReloaded {

    @Mod.Instance(Reference.MODID)
    public static MorpheussTechReloaded instance;

    @SidedProxy(clientSide = "Morpheuss93.MorpheussTechReloaded.client.ClientProxy",serverSide="Morpheuss93.MorpheussTechReloaded.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabOre= new CreativeTabs("MorpheussTech-Reloaded Ores") {
        @Override
        public Item getTabIconItem() {
            return OresHandler.steelIngot;
        }

    };

    public static CreativeTabs tabMachine=new CreativeTabs("MorpheussTech-Reloaded Machines") {//TODO right position for elements and change icon
        @Override
        public Item getTabIconItem() {
            return FluidsHandler.alcoholBucket;
        }
    };

    public static CreativeTabs tabComponent=new CreativeTabs("MorpheussTech-Reloaded Machines Components") {
        @Override
        public Item getTabIconItem() {
            return MachinesHandler.smallAlcoholBurner;
        }
    };

    public static CreativeTabs tabBlocks=new CreativeTabs("MorpheussTech-Reloaded Blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockHandler.enderCoatedGlass);
        }
    };

    public static CreativeTabs tabTools=new CreativeTabs("MorpheussTech-Reloaded Tools") {
        @Override
        public Item getTabIconItem() {
            return ToolsHandler.macheteBronze;
        }
    };

    public static CreativeTabs tabCrops=new CreativeTabs("MorpheussTech-Reloaded Crops") {
        @Override
        public Item getTabIconItem() {
            return AgricultureHandler.ananas;
        }
    };


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration config=new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        OresHandler.configureOre();
        OresHandler.configureIngots();
        OresHandler.configureWorldGenerator();
        OresHandler.configureRecipes();

        BlockHandler.configureBlocks();

        FluidsHandler.configureFluids();
        FluidsHandler.configureItems();

        MachinesHandler.configureMachine();
        MachinesHandler.configureTileEntity();
        MachinesHandler.configureComponents();
        MachinesHandler.configureCrafting();

        ToolsHandler.configureTools();
        ToolsHandler.configureRecipes();

        AgricultureHandler.configureFood();
        AgricultureHandler.configureCrops();
        AgricultureHandler.configureSeed();
        AgricultureHandler.configureRecipes();

        config.save();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        proxy.registerRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new MGuiHandler());//TODO
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
