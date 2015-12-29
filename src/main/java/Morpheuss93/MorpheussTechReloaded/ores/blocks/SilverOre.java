package Morpheuss93.MorpheussTechReloaded.ores.blocks;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by stefano on 29/12/15.
 */
public class SilverOre extends Block {
    public SilverOre() {
        super(Material.rock);
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setBlockName("Silver Ore");
        this.setBlockTextureName(Reference.MODID+":"+"SilverOre");
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe", 2);
    }
}
