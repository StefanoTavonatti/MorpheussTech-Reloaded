package Morpheuss93.MorpheussTechReloaded.ores.ingots;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 29/12/15.
 */
public class TinIngot extends Item {

    public TinIngot()
    {
        super();
        this.setUnlocalizedName("Tin Ingot");
        this.setTextureName(Reference.MODID+":"+"TinIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }
}
