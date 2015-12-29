package Morpheuss93.MorpheussTechReloaded;

import Morpheuss93.MorpheussTechReloaded.ores.OresHandler;
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


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration config=new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        OresHandler.configureOre();
        OresHandler.configureIngots();
        OresHandler.configureWorldGenerator();

        config.save();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        proxy.registerRenders();
        //NetworkRegistry.INSTANCE.registerGuiHandler(instance, new MGuiHandler());//TODO
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
