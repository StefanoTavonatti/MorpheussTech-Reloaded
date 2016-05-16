package Morpheuss93.MorpheussTechReloaded.tools;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import com.mojang.realmsclient.dto.PlayerInfo;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by stefano on 11/05/16.
 */
public class MagicMirror extends Item {

    public MagicMirror(){
        super();
        this.setUnlocalizedName("MagicMirror");
        this.setTextureName(Reference.MODID+":"+"MagicMirror");
        this.setMaxStackSize(1);
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);//TODO right tab
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        //player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        if(!world.isRemote) {
            System.out.println("TEST CLICK " + player.getHealth());
            if(player.dimension==0) {
                tp_player(player);
            }else
            {
                player.travelToDimension(0);
                tp_player(player);
            }


        }

        return itemStack;
    }

    private void tp_player(EntityPlayer player) {
        ChunkCoordinates cc = player.getBedLocation(0);
        if (cc == null) {
            World world2 = DimensionManager.getWorld(0);
            cc = world2.getSpawnPoint();
        }
        double x = cc.posX;
        double z = cc.posZ;
        cc.posY = player.worldObj.getTopSolidOrLiquidBlock(cc.posX, cc.posZ);
        double y = cc.posY;

        System.out.println("" + x + " " + y + " " + z);


        player.setPositionAndUpdate(x, y + 0.5D, z);
    }

    private void tp(EntityPlayer player)
    {
        World world = player.worldObj;
        if (!world.provider.canRespawnHere())
            world = DimensionManager.getWorld(0);

        ChunkCoordinates spawn = player.getBedLocation(world.provider.dimensionId);
        if (spawn == null && world.provider.dimensionId != 0)
        {
            world = DimensionManager.getWorld(0);
            spawn = player.getBedLocation(world.provider.dimensionId);
        }
        if (spawn == null)
            //throw new TranslatedCommandException("No bed found.");
            return;

        spawn = EntityPlayer.verifyRespawnCoordinates(player.worldObj, spawn, true);
        if (spawn == null)
            //throw new TranslatedCommandException("Your bed has been obstructed.");
            return;

        System.out.println(""+spawn.posX+" "+spawn.posY+" "+spawn.posZ);
        player.setPositionAndUpdate(spawn.posX,spawn.posY,spawn.posZ);
        //player.moveEntity(spawn.posX,spawn.posY,spawn.posZ);
        /*PlayerInfo.get(player.getPersistentID()).setLastTeleportOrigin(new WarpPoint(player));
        WarpPoint spawnPoint = new WarpPoint(world.provider.dimensionId, spawn, player.rotationPitch, player.rotationYaw);
        TeleportHelper.teleport(player, spawnPoint);*/
    }

}
