package Morpheuss93.MorpheussTechReloaded.agriculture.foods;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.base.RottableFood;

/**
 * Created by stefano on 30/12/15.
 */
public class PineappleSlice extends RottableFood {

    public PineappleSlice() {
        super(6,0.6F, false);
        this.setUnlocalizedName("PineappleSlice");
        this.setTextureName(Reference.MODID+":PineappleSlice");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
        this.setMaxStackSize(16);

    }
}
