package Morpheuss93.MorpheussTechReloaded.ores.ingots;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 29/12/15.
 */
public class SilverIngot extends Item {
    public SilverIngot()
    {
        super();
        this.setUnlocalizedName("Silver Ingot");
        this.setTextureName(Reference.MODID+":"+"SilverIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }
}
