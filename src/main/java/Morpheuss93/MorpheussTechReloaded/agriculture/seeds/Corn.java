package Morpheuss93.MorpheussTechReloaded.agriculture.seeds;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.BaseItemSeedFood;
import net.minecraft.init.Blocks;

/**
 * Created by stefano on 30/12/15.
 */
public class Corn extends BaseItemSeedFood {

    public Corn() {
        super(1, 1.0F, AgricultureHandler.cornCrop, Blocks.farmland);
        this.setUnlocalizedName("Corn");
        this.setTextureName(Reference.MODID+":Corn");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
    }

}
