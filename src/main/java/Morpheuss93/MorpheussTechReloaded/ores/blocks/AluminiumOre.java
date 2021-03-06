package Morpheuss93.MorpheussTechReloaded.ores.blocks;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by stefano on 29/12/15.
 */
public class AluminiumOre extends Block {

    public AluminiumOre() {
        super(Material.rock);
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setBlockName("Aluminium Ore");
        this.setBlockTextureName(Reference.MODID+":"+"AluminiumOre");
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 1);
    }
}
