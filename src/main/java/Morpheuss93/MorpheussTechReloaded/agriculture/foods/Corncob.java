package Morpheuss93.MorpheussTechReloaded.agriculture.foods;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.RottableFood;

/**
 * Created by stefano on 30/12/15.
 */
public class Corncob extends RottableFood {

    public Corncob() {
        super(1, 1.0F, false);
        this.setUnlocalizedName("Corncob");
        this.setTextureName(Reference.MODID+":Corncob");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
        this.setMaxStackSize(64);
    }

}
