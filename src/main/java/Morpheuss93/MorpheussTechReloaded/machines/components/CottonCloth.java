package Morpheuss93.MorpheussTechReloaded.machines.components;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 30/12/15.
 */
public class CottonCloth extends Item {

    public CottonCloth(){
        super();
        this.setUnlocalizedName("CottonCloth");
        this.setTextureName(Reference.MODID+":"+"CottonCloth");
        this.setMaxStackSize(64);
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);
    }
}
