package Morpheuss93.MorpheussTechReloaded.blocks;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;

import java.util.Random;

/**
 * Created by stefano on 10/05/16.
 */
public class EnderCoatedGlass extends BaseGlassStyle {
    protected EnderCoatedGlass() {
        super(Material.glass);
        this.setCreativeTab(MorpheussTechReloaded.tabOre);
        this.setBlockName("Ender Coated Glass");
        this.setBlockTextureName(Reference.MODID+":"+"EnderCoatedGlass");
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }
}
