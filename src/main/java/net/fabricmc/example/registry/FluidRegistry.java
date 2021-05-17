package net.fabricmc.example.registry;

import net.fabricmc.example.EtherFluidA;
import net.minecraft.fluid.FlowableFluid;

public enum FluidRegistry {
    
    ether_a_fluid(0x3FC03);

    public final FlowableFluid flowing;
    public final FlowableFluid still;
    
    public final int color;

    FluidRegistry(int color){
        this.color = color;
        this.flowing = new EtherFluidA.Flowing();
        this.still = new EtherFluidA.Still();
        
    }


}
