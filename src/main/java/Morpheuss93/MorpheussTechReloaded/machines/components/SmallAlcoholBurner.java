package Morpheuss93.MorpheussTechReloaded.machines.components;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;

/**
 * Created by stefano on 30/12/15.
 */
public class SmallAlcoholBurner extends Item {

    public SmallAlcoholBurner(){
        super();
        this.setUnlocalizedName("Small Alcohol Burner");
        this.setTextureName(Reference.MODID+":"+"SmallAlcoholBurner");
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);
        this.setMaxStackSize(64);
    }
}
