package Morpheuss93.MorpheussTechReloaded.agriculture.seeds;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.SeedBase;

/**
 * Created by stefano on 30/12/15.
 */
public class CottonSeed extends SeedBase {

    public CottonSeed() {
        super();
        thePlant= AgricultureHandler.cottonCrop;
        this.setUnlocalizedName("CottonSeed");
        this.setTextureName(Reference.MODID+":CottonSeed");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
    }

}
