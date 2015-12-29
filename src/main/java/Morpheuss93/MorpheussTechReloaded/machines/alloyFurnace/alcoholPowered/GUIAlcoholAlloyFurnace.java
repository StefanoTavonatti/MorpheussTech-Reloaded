package Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered;

import Morpheuss93.MorpheussTechReloaded.Reference;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.basic.GUIAlloyFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by stefano on 30/12/15.
 */
public class GUIAlcoholAlloyFurnace extends GUIAlloyFurnace{

    private TileEntityAlcoholAlloyFurnace tileEntityAlcoholAlloyFurnace;

    private static final ResourceLocation furnaceGuiTexture =new ResourceLocation(Reference.MODID+":"+"textures/gui/container/AlcoholAlloyfurnace.png");

    public GUIAlcoholAlloyFurnace(InventoryPlayer invPlayer,
                                  TileEntityAlcoholAlloyFurnace tile) {
        super(invPlayer, tile);
        tileEntityAlcoholAlloyFurnace=tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1,int par2){
        super.drawGuiContainerForegroundLayer(par1, par2);
        if(this.tileEntityAlcoholAlloyFurnace instanceof TileEntityAlcoholAlloyFurnace){
			/*String string="Alcohol "+((TileEntityAlcoholAlloyFurnace)tileEntityAlcoholAlloyFurnace).getTankAmount();
			this.fontRendererObj.drawString(string, this.xSize/2 -this.fontRendererObj.getStringWidth(string), 60, 4210752);*/
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTexture);
        int k= (this.width - this.xSize)/2;
        int l= (this.height - this.ySize)/2;

        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if(tileEntityAlcoholAlloyFurnace.isBurning()){
            i1=tileEntityAlcoholAlloyFurnace.getBurnTimeRemaningScaled(12);

            this.drawTexturedModalRect(k + 85, l + 56 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1=tileEntityAlcoholAlloyFurnace.getCookProgressScaled(24);//freccia
        this.drawTexturedModalRect(k+79, l+34, 176, 14, i1+1,16);

        i1=tileEntityAlcoholAlloyFurnace.getTankScaled(55);
        this.drawTexturedModalRect(k+143, l+5+(55-i1), 176, 33, 23,i1);

        this.drawTexturedModalRect(k+144,l+8, 176, 88, 21, 50);
    }

}
