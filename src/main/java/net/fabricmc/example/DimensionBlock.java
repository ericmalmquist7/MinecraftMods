package net.fabricmc.example;

import net.fabricmc.example.registry.BlockRegistry;
import net.fabricmc.example.registry.RegisterHelper;
import net.fabricmc.example.registry.DimensionBlockRegistry;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class DimensionBlock extends Block{

    public DimensionBlock(Settings settings) {
        super(settings);
        //TODO Auto-generated constructor stub
    }

    private BlockPos[] multiblock_locations = new BlockPos[]{
        new BlockPos(0,1,0),
        new BlockPos(1,1,0),
        new BlockPos(0,1,1),
        new BlockPos(-1,1,0),
        new BlockPos(0,1,-1),

        new BlockPos(0,-1,0),
        new BlockPos(1,-1,0),
        new BlockPos(0,-1,1),
        new BlockPos(-1,-1,0),
        new BlockPos(0,-1,-1),
    };

    public boolean multiblock_built = false;
    
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (!world.isClient) {
            this.multiblock_built = checkMultiblock(state, world, pos, player);

            if (this.multiblock_built)
            {
                player.sendMessage(new LiteralText("Ether floods your mind, enveloping you.."), false);

                TeleportTarget target = new TeleportTarget(Vec3d.ZERO, new Vec3d(1, 1, 1), 45f, 60f);
                ServerWorld server_world = player.getServer().getWorld(RegisterHelper.WORLD_KEY);
                
                Entity teleported = FabricDimensions.teleport(player, server_world, target);
                if (teleported == null) throw new AssertionError("Entity didn't teleport");
            }
        }

    return ActionResult.SUCCESS;
    }

    private boolean checkMultiblock(BlockState state, World world, BlockPos pos, PlayerEntity player){
        
        BlockState expectedBlock = null;
        if(state == DimensionBlockRegistry.dimension_a.block.getDefaultState()){
            expectedBlock = BlockRegistry.base_a.block.getDefaultState();
        }
        else if(state == DimensionBlockRegistry.dimension_b.block.getDefaultState()){
            expectedBlock = BlockRegistry.base_b.block.getDefaultState();
        }
        else if(state == DimensionBlockRegistry.dimension_c.block.getDefaultState()){
            expectedBlock = BlockRegistry.base_c.block.getDefaultState();
        }
        else if(state == DimensionBlockRegistry.dimension_d.block.getDefaultState()){
            expectedBlock = BlockRegistry.base_d.block.getDefaultState();
        }
        else if(state == DimensionBlockRegistry.dimension_e.block.getDefaultState()){
            expectedBlock = BlockRegistry.base_e.block.getDefaultState();
        }
        System.out.println("Expecting block " + expectedBlock.toString());

        for (BlockPos loc : multiblock_locations){
            BlockState multiblock = world.getBlockState(pos.add(loc));
            System.out.println("Found multiblock of state " + multiblock);
            if ( multiblock != expectedBlock ){
                player.sendMessage(new LiteralText("Multiblock incomplete. Missing " + expectedBlock + " at location " + loc.toString() + " from keystone."), false);
                return false;
            }
        }
        return true;
    }
}
