package Morpheuss93.MorpheussTechReloaded.ores.ingots.alloy;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 09/05/16.
 */
public class EnderAlloyIngot extends Item {
    public EnderAlloyIngot(){
        this.setUnlocalizedName("Ender Alloy Ingot");
        this.setTextureName(Reference.MODID+":"+"EnderAlloyIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }
}
