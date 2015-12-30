package Morpheuss93.MorpheussTechReloaded.agriculture.foods;


import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.BaseRottableItemSeedFood;
import net.minecraft.init.Blocks;

/**
 * Created by stefano on 30/12/15.
 */
public class Ananas extends BaseRottableItemSeedFood {

    public Ananas(){
        super(6,0.6F, AgricultureHandler.ananasPlant, Blocks.farmland);
        setUnlocalizedName("Ananas");
        setTextureName(Reference.MODID+":Ananas");
        setCreativeTab(MorpheussTechReloaded.tabCrops);

        this.setMaxStackSize(1);

    }

}
