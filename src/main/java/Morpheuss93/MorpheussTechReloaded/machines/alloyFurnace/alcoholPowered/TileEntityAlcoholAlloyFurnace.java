package Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.alcoholPowered;

import Morpheuss93.MorpheussTechReloaded.fluids.FluidsHandler;
import Morpheuss93.MorpheussTechReloaded.machines.alloyFurnace.TileEntityAlloyFurnace;
import cpw.mods.fml.common.asm.transformers.ItemStackTransformer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Created by stefano on 30/12/15.
 */
public class TileEntityAlcoholAlloyFurnace extends TileEntityAlloyFurnace implements IFluidHandler{
    protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*2);
    public int fluidAmount;

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if(resource.getFluid()== FluidsHandler.alcohol)
        {
            int ft=tank.fill(resource, doFill);;
            fluidAmount=tank.getFluidAmount();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            this.markDirty();
            return ft;
        }
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource,
                            boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
        {
            return null;
        }
        fluidAmount=tank.getFluidAmount();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.markDirty();
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        FluidStack res=tank.drain(maxDrain, doDrain);
        fluidAmount=tank.getFluidAmount();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.markDirty();
        return res;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid==FluidsHandler.alcohol;
        //return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.fluidAmount=tag.getInteger("fluidAmount");
        tank.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
        tag.setInteger("fluidAmount", this.fluidAmount);
    }

    public int getTankAmount()
    {

        return this.fluidAmount;
        //return tank.getFluidAmount();
    }

    public FluidTank getTank(){
        return tank;
    }

    public void setTankAmount(int value){
        System.out.println("SetTankAmount "+value);
        if(tank.getFluid()!=null)
        {
            //tank.getFluid().amount=value;

        }
        fluidAmount=value;

    }

    public void useFuel(int quantity){
        fluidAmount-=quantity;
        tank.drain(quantity, true);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.markDirty();
    }

    public void addFuel(int quantity){
        fluidAmount+=quantity;
        tank.fill(new FluidStack(FluidsHandler.alcohol, quantity), true);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.markDirty();
    }

    @SideOnly(Side.CLIENT)
    public int getTankScaled(int levelMax){
        return this.getTankAmount()*levelMax/tank.getCapacity();
    }

    /**
     * i cambiamenti nella gui vanno sincronizzati
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void updateEntity(){//TODO, riempire da sechhio slot 9
        boolean flag=this.furnaceBurnTime>0;
        boolean flag1=false;

        if(this.furnaceItemStacks[9]!=null){
            ItemStack itemStack=this.furnaceItemStacks[9];
            if(itemStack.getItem()==FluidsHandler.alcoholBucket){
                if(this.getTankAmount()<=tank.getCapacity()-1000){
                    this.addFuel(1000);
                    this.furnaceItemStacks[9]=new ItemStack(((ItemBucket)itemStack.getItem()).getContainerItem());
                }
            }
        }

        if(this.furnaceBurnTime>0){
            --this.furnaceBurnTime;
        }

        //if(!this.worldObj.isRemote){
        if(this.furnaceBurnTime==0 && this.canSmelt()){
            if(this.getTankAmount()>=200)//Quantit� di alcohol necessaria alla cottura
                this.currentBurnTime=this.furnaceBurnTime=200;
            else
                this.currentBurnTime=this.furnaceBurnTime=0;

            if(this.furnaceBurnTime>0){
                flag1=true;
                useFuel(200);
            }
        }

        if(this.isBurning() && this.canSmelt()){
            ++this.furnaceCookTime;
            if(this.furnaceCookTime==200){
                this.furnaceCookTime=0;
                this.smeltItem();
                flag1=true;
            }
        }else{
            this.furnaceCookTime=0;
        }
        //}

        if(flag !=this.furnaceBurnTime>0){
            flag1=true;
            AlcoholAlloyFurnace.updateBlockStateAlcoholAlloyFurnace(this.furnaceBurnTime>0, this.worldObj, this.xCoord,this.yCoord , this.zCoord);
        }

        if(flag1){
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            this.markDirty();
        }
    }
}
