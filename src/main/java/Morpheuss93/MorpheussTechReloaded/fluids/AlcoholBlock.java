package Morpheuss93.MorpheussTechReloaded.fluids;

import Morpheuss93.MorpheussTechReloaded.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by stefano on 29/12/15.
 */
public class AlcoholBlock extends BlockFluidClassic{
    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

    protected Fluid alcohol;

    public AlcoholBlock(Fluid fluid, Material material) {
        super(fluid, material);
        alcohol=fluid;
    }


    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = register.registerIcon(Reference.MODID+":"+"AlcoholStill");
        flowingIcon = register.registerIcon(Reference.MODID+":"+"AlcoholFlowing");
        alcohol.setIcons(stillIcon);

    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
        return super.displaceIfPossible(world, x, y, z);
    }

    public IIcon getStillIcon(){
        return stillIcon;
    }

    public IIcon getFlowIcon(){
        return flowingIcon;
    }
}
