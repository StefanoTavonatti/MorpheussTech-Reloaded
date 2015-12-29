package Morpheuss93.MorpheussTechReloaded.ores.blocks;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by stefano on 27/12/15.
 */
public class TinOre extends Block {

    public TinOre() {
        super(Material.rock);
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setBlockName("Tin Ore");
        this.setBlockTextureName(Reference.MODID+":"+"TinOre");
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 1);
    }
}
