package Morpheuss93.MorpheussTechReloaded.agriculture.seeds;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.SeedBase;

/**
 * Created by stefano on 30/12/15.
 */
public class AnanasSeed extends SeedBase {//seed find when a player broke the grass with a machete

    public AnanasSeed() {
        this.thePlant= AgricultureHandler.ananasPlant;
        this.setUnlocalizedName("AnanasSeed");
        this.setTextureName(Reference.MODID+":"+"AnanasSeed");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
    }

}
