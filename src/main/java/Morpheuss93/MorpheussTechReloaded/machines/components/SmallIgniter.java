package Morpheuss93.MorpheussTechReloaded.machines.components;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 30/12/15.
 */
public class SmallIgniter extends Item {

    public SmallIgniter(){
        super();
        this.setUnlocalizedName("Small Igniter");
        this.setTextureName(Reference.MODID+":"+"SmallIgniter");
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);
        this.setMaxStackSize(64);
    }
}
