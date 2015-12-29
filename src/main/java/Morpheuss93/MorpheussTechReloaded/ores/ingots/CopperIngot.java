package Morpheuss93.MorpheussTechReloaded.ores.ingots;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 29/12/15.
 */
public class CopperIngot extends Item {

    public CopperIngot()
    {
        super();
        this.setUnlocalizedName("Copper Ingot");
        this.setTextureName(Reference.MODID+":"+"CopperIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }

}
