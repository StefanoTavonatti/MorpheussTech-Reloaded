package Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace;

import Morpheuss93.MorpheussTechReloaded.blocks.BlockHandler;
import Morpheuss93.MorpheussTechReloaded.ores.OresHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * Created by stefano on 29/12/15.
 */
public class AlloyFurnaceRecipes {

    public static final AlloyFurnaceRecipes SMELTING_BASE = new AlloyFurnaceRecipes();

    private ArrayList smeltingRecipe;
    private ArrayList quantity;
    private ArrayList results;

    public AlloyFurnaceRecipes(){
        smeltingRecipe=new ArrayList();
        quantity=new ArrayList();
        results=new ArrayList();

        ArrayList temp=new ArrayList();

        //////////Bronze/////////////
        temp.add(new ItemStack(OresHandler.tinIngot,1));
        temp.add(new ItemStack(OresHandler.copperIngot,1));
        temp.add(new ItemStack(OresHandler.copperIngot,1));
        temp.add(new ItemStack(OresHandler.copperIngot,1));

        addRecipie(temp, OresHandler.bronzeIngot, 4);

        //////Brass///////

        temp=new ArrayList();//temporanea da cambiare
        temp.add(new ItemStack(OresHandler.copperIngot));
        temp.add(new ItemStack(OresHandler.copperIngot));
        temp.add(new ItemStack(OresHandler.aluminiumIngot));
        temp.add(new ItemStack(OresHandler.aluminiumIngot));

        addRecipie(temp, OresHandler.brassIngot, 4);

        //Steel//

        temp=new ArrayList();
        temp.add(new ItemStack(Items.coal));
        temp.add(new ItemStack(Items.iron_ingot));
        temp.add(new ItemStack(Items.coal));

        addRecipie(temp, OresHandler.steelIngot, 1);

        //Ender Alloy//

        temp=new ArrayList();
        temp.add(new ItemStack(OresHandler.silverIngot));
        temp.add(new ItemStack(Items.ender_pearl));
        temp.add(new ItemStack(Items.ender_pearl));

        addRecipie(temp,OresHandler.enderAlloyIngot, 1);


        temp=new ArrayList();
        temp.add(new ItemStack(Blocks.stone));
        temp.add(new ItemStack(Items.ender_pearl));
        temp.add(new ItemStack(Items.ender_pearl));

        addRecipie(temp,Item.getItemFromBlock(Blocks.end_stone),1);

        temp=new ArrayList();
        temp.add(new ItemStack(Blocks.glass));
        temp.add(new ItemStack(Items.ender_pearl));
        temp.add(new ItemStack(Items.ender_pearl));

        addRecipie(temp,Item.getItemFromBlock(BlockHandler.enderCoatedGlass),1);
    }

    public void addRecipie(ArrayList recipie, Item result, int num){

        smeltingRecipe.add(recipie);
        quantity.add(num);
        results.add(result);

    }

    public ItemStack getSmeltingResult(ArrayList recipie){

        ItemStack result=null;



        for(int i=0;i<smeltingRecipe.size();i++)
        {
            int t=((ArrayList)smeltingRecipe.get(i)).size();
            boolean uguali=false;
            if(recipie.size()==t){
                ArrayList temp=(ArrayList)smeltingRecipe.get(i);
                uguali=true;
                for(int j=0;j<t;j++)
                {
                    ItemStack tempI=(ItemStack)temp.get(j);
                    ItemStack tempI2=(ItemStack)recipie.get(j);

                    int[] id1= OreDictionary.getOreIDs(tempI);
                    int[] id2=OreDictionary.getOreIDs(tempI2);

                    if(id1.length ==0 || id2.length==0){
                        if(tempI.getItem()!=tempI2.getItem()){
                            uguali=false;
                        }
                    }else
                    {
                        String s1=OreDictionary.getOreName(id1[0]);
                        String s2=OreDictionary.getOreName(id2[0]);


                        if(!s1.equals(s2))
                        {
                            uguali=false;
                        }
                    }
                }

            }
            if(uguali){
                result=new ItemStack((Item)results.get(i),(Integer)quantity.get(i));
            }
        }
        return result;
    }
}
