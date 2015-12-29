package Morpheuss93.MorpheussTechReloaded;

import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.TileEntityAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered.GUIAlcoholAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered.TileEntityAlcoholAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.ContainerAlloyFurnace;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.GUIAlloyFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by stefano on 29/12/15.
 */
public class MGuiHandler implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        if(ID==0){
            TileEntityAlloyFurnace tileEntityFurnace =(TileEntityAlloyFurnace) world.getTileEntity(x, y, z);
            return new GUIAlloyFurnace(player.inventory,tileEntityFurnace);
        }

        if(ID==1){
            TileEntityAlcoholAlloyFurnace tileEntityAlcoholAlloyFurnace=(TileEntityAlcoholAlloyFurnace) world.getTileEntity(x, y, z);
            return new GUIAlcoholAlloyFurnace(player.inventory, tileEntityAlcoholAlloyFurnace);
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        if(ID==0){
            TileEntityAlloyFurnace tileEntityFurnace =(TileEntityAlloyFurnace) world.getTileEntity(x, y, z);
            return new ContainerAlloyFurnace(player.inventory,tileEntityFurnace);
        }

        if(ID==1){
            TileEntityAlcoholAlloyFurnace tileEntityAlcoholAlloyFurnace=(TileEntityAlcoholAlloyFurnace) world.getTileEntity(x,y, z);
            return new ContainerAlloyFurnace(player.inventory, tileEntityAlcoholAlloyFurnace);//TODO
        }
        return null;
    }

}
