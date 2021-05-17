package net.fabricmc.example.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;

import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.biome.source.BiomeSource;

public class EtherChunkGenerator extends ChunkGenerator {

    protected final boolean customBool;

    public static final Codec<EtherChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
    instance.group(
            BiomeSource.CODEC.fieldOf("biome_source")
                .forGetter((generator) -> generator.biomeSource),

            Codec.BOOL.fieldOf("custom_bool")
                .forGetter((generator) -> generator.customBool))
                
        .apply(instance, instance.stable(EtherChunkGenerator::new))
    );

    public EtherChunkGenerator(BiomeSource biomeSource, boolean customBool) {
		super(biomeSource, new StructuresConfig(false));
		this.customBool = customBool;
	}

    @Override
    public void buildSurface(ChunkRegion region, Chunk chunk) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        // TODO Auto-generated method stub
        return CODEC;
    }

    @Override
    public BlockView getColumnSample(int x, int z) {
        return new VerticalBlockSample(new BlockState[0]);
    }

    @Override
    public int getHeight(int x, int z, Type heightmapType) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ChunkGenerator withSeed(long arg0) {
        // TODO Auto-generated method stub
        return this;
    }



}
