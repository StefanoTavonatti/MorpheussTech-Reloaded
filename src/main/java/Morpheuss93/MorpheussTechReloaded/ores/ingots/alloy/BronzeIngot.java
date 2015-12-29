package Morpheuss93.MorpheussTechReloaded.ores.ingots.alloy;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 29/12/15.
 */
public class BronzeIngot extends Item {

    public BronzeIngot(){
        super();
        this.setUnlocalizedName("Bronze Ingot");
        this.setTextureName(Reference.MODID+":"+"BronzeIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }
}
