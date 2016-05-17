package Morpheuss93.MorpheussTechReloaded.blocks;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by stefano on 10/05/16.
 */
public class EnderCoatedGlass extends BaseGlassStyle {
    protected EnderCoatedGlass() {
        super(Material.glass);
        this.setCreativeTab(MorpheussTechReloaded.tabBlocks);
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
        return 1;
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

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {


        float f = (float)x + 0.5F;
        float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
        float f2 = (float)z + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;

       // world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
       // world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
        world.spawnParticle("portal", (double)(f + f4), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);


    }
}
