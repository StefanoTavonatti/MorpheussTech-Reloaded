package Morpheuss93.MorpheussTechReloaded.agriculture.foods;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.ItemFood;

/**
 * Created by stefano on 30/12/15.
 */
public class Popcorn extends ItemFood {

    public Popcorn() {
        super(2,1.0F,false);
        this.setUnlocalizedName("Popcorn");
        this.setTextureName(Reference.MODID+":Popcorn");
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
        this.setMaxStackSize(64);
    }

}
