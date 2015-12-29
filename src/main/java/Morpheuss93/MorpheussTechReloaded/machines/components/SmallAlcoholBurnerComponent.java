package Morpheuss93.MorpheussTechReloaded.machines.components;

import Morpheuss93.MorpheussTechReloaded.MorpheussTechReloaded;
import Morpheuss93.MorpheussTechReloaded.Reference;
import net.minecraft.item.Item;
import tv.twitch.Core;

/**
 * Created by stefano on 30/12/15.
 */
public class SmallAlcoholBurnerComponent extends Item {

    public SmallAlcoholBurnerComponent(){
        super();
        this.setUnlocalizedName("Small Alcohol Burner Component");
        this.setTextureName(Reference.MODID+":"+"SmallAlcoholBurnerComponent");
        this.setCreativeTab(MorpheussTechReloaded.tabComponent);
        this.setMaxStackSize(64);
    }
}

