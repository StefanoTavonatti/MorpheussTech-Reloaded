package Morpheuss93.MorpheussTechReloaded.agriculture.crops;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.IPlantable;

/**
 * Created by stefano on 17/05/16.
 */
public class EnderLily extends BlockLilyPad implements IPlantable {

    public EnderLily(){
        float f = 0.5F;
        float f1 = 0.015625F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        this.setCreativeTab(MorpheussTechReloaded.tabCrops);
        this.setBlockTextureName(Reference.MODID+":"+"EnderLily");
    }

    @Override
    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.water;
    }
}
