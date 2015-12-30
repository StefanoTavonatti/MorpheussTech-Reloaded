package Morpheuss93.MorpheussTechReloaded.tools;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 * Created by stefano on 30/12/15.
 */
public class Knife extends ItemSword {
    public Knife() {
        super(ToolMaterial.STONE);
        this.setUnlocalizedName("Knife");
        this.setTextureName(Reference.MODID+":Knife");
        this.setCreativeTab(MorpheussTechReloaded.tabTools);
        this.setMaxStackSize(1);
        this.setMaxDamage(192);

    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_)
    {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        // TODO Auto-generated method stub
        //return super.getContainerItem(itemStack);
        ItemStack it=new ItemStack(itemStack.getItem());
        it.setItemDamage(itemStack.getItemDamage()+1);
        return it;
    }
}
