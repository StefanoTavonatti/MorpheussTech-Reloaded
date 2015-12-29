package Morpheuss93.MorpheussTechReloaded.ores.ingots.alloy;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 29/12/15.
 */
public class SteelIngot extends Item {

    public SteelIngot() {

        super();
        this.setUnlocalizedName("Steel Ingot");
        this.setTextureName(Reference.MODID+":"+"SteelIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }
}
