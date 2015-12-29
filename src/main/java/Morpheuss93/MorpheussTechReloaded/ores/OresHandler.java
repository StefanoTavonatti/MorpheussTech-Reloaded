package Morpheuss93.MorpheussTechReloaded.ores;

import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.ores.blocks.AluminiumOre;
import Morpheuss93.MorpheussTechReloaded.ores.blocks.CopperOre;
import Morpheuss93.MorpheussTechReloaded.ores.blocks.SilverOre;
import Morpheuss93.MorpheussTechReloaded.ores.blocks.TinOre;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.AluminiumIngot;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.CopperIngot;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.SilverIngot;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.TinIngot;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.alloy.BrassIngot;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.alloy.BronzeIngot;
import Morpheuss93.MorpheussTechReloaded.ores.ingots.alloy.SteelIngot;
import Morpheuss93.MorpheussTechReloaded.ores.worldgenerators.AluminiumWGenerator;
import Morpheuss93.MorpheussTechReloaded.ores.worldgenerators.CopperWGenerator;
import Morpheuss93.MorpheussTechReloaded.ores.worldgenerators.SilverWGenerator;
import Morpheuss93.MorpheussTechReloaded.ores.worldgenerators.TinWGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by stefano on 27/12/15.
 */
public class OresHandler {//TODO zinc

    public static TinOre tinOre=new TinOre();
    public static AluminiumOre aluminiumOre=new AluminiumOre();
    public static CopperOre copperOre=new CopperOre();
    public static SilverOre silverOre=new SilverOre();

    public static CopperIngot copperIngot;
    public static AluminiumIngot aluminiumIngot;
    public static SilverIngot silverIngot;
    public static TinIngot tinIngot;
    public static BronzeIngot bronzeIngot;
    public static BrassIngot brassIngot;
    public static SteelIngot steelIngot;

    public static void configureOre(){

        GameRegistry.registerBlock(tinOre, Reference.MODID+"-"+tinOre.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("oreTin", tinOre);

        GameRegistry.registerBlock(aluminiumOre, Reference.MODID+"-"+aluminiumOre.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("oreAluminium", aluminiumOre);

        GameRegistry.registerBlock(copperOre, Reference.MODID+"-"+copperOre.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("oreCopper", copperOre);

        GameRegistry.registerBlock(silverOre, Reference.MODID+"-"+silverOre.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("oreSilver", silverOre);
    }

    public static void configureWorldGenerator(){
        GameRegistry.registerWorldGenerator(new CopperWGenerator(),0);
        GameRegistry.registerWorldGenerator(new AluminiumWGenerator(), 0);
        GameRegistry.registerWorldGenerator(new SilverWGenerator(), 0);
        GameRegistry.registerWorldGenerator(new TinWGenerator(), 0);
    }

    public static void configureIngots(){
        copperIngot=new CopperIngot();
        GameRegistry.registerItem(copperIngot, Reference.MODID+"-"+copperIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotCopper", copperIngot);

        aluminiumIngot=new AluminiumIngot();
        GameRegistry.registerItem(aluminiumIngot, Reference.MODID+"-"+aluminiumIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotAluminium", aluminiumIngot);

        silverIngot=new SilverIngot();
        GameRegistry.registerItem(silverIngot, Reference.MODID+"-"+silverIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotSilver", silverIngot);

        tinIngot=new TinIngot();
        GameRegistry.registerItem(tinIngot, Reference.MODID+"-"+tinIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotTin", tinIngot);

        bronzeIngot=new BronzeIngot();
        GameRegistry.registerItem(bronzeIngot, Reference.MODID+"-"+bronzeIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotBronze", bronzeIngot);

        brassIngot=new BrassIngot();
        GameRegistry.registerItem(brassIngot, Reference.MODID+"-"+brassIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotBrass", brassIngot);

        steelIngot=new SteelIngot();
        GameRegistry.registerItem(steelIngot, Reference.MODID+"-"+steelIngot.getUnlocalizedName().substring(5));
        OreDictionary.registerOre("ingotSteel", steelIngot);
    }
}
