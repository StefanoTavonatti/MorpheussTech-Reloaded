package Morpheuss93.MorpheussTechReloaded.machines.components.consumables;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 30/12/15.
 */
public class FishingNet extends Item {

    public FishingNet(){
        super();
        this.setUnlocalizedName("FishingNet");
        this.setTextureName(Reference.MODID+":"+"FishingNet");
        this.setMaxDamage(64);
        this.setMaxStackSize(1);
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);
    }

}
