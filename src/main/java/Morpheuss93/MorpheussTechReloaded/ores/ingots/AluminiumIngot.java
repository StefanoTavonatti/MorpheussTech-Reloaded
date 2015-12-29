package Morpheuss93.MorpheussTechReloaded.ores.ingots;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 29/12/15.
 */
public class AluminiumIngot extends Item {
    public AluminiumIngot()
    {
        super();
        this.setUnlocalizedName("Aluminium Ingot");
        this.setTextureName(Reference.MODID+":"+"AluminiumIngot");
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setMaxStackSize(64);
    }
}
