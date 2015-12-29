package Morpheuss93.MorpheussTechReloaded.machines.components;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 30/12/15.
 */
public class SmallAlcoholBurnerSupport extends Item {

    public SmallAlcoholBurnerSupport(){
        super();
        this.setUnlocalizedName("Small Alcohol Burner Support");
        this.setTextureName(Reference.MODID+":"+"SmallAlcoholBurnerSupport");
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);
        this.setMaxStackSize(64);
    }

}
