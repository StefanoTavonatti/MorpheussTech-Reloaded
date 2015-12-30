package Morpheuss93.MorpheussTechReloaded.tools;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.agriculture.AgricultureHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by stefano on 30/12/15.
 */
public class Machete extends ItemSword{
    public Machete(ToolMaterial material,String name) {
        super(material);
        this.setCreativeTab(MorpheussTechReloaded.tabTools);
        this.configure(name);
    }

    public void configure(String name){
        this.setUnlocalizedName(name);
        this.setTextureName(Reference.MODID+":"+name);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int x, int y, int z, EntityLivingBase entity){

        if(block.getUnlocalizedName().equals("tile.tallgrass")&& !world.isRemote){


            int ran= MathHelper.getRandomIntegerInRange(new Random(), 0, 100);

            boolean drop=false;
            Item itemDrop=null;

            System.out.println("numero: "+ran);

            if(ran>=0 && ran <20)
            {
                itemDrop= AgricultureHandler.ananasSeed;
                drop=true;
            }else if(ran>=20 && ran <30){
                itemDrop=AgricultureHandler.cottonSeed;
                drop=true;
            }else if(ran>=30 && ran<40){
                itemDrop=AgricultureHandler.corn;
                drop=true;
            }

            if(drop)
            {

                EntityItem item=new EntityItem(world,(double)x,(double)y,(double)z,new ItemStack(itemDrop,1));
                world.spawnEntityInWorld(item);

                itemstack.damageItem(1, entity);

            }
        }

        return super.onBlockDestroyed(itemstack, world, block, x, y, z, entity);
    }
}
