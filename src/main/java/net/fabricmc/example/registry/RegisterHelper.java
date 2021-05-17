package net.fabricmc.example.registry;

import java.util.function.Function;

import net.fabricmc.example.CustomFabricItem;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class RegisterHelper {

    public static final Item ModIconItem = new Item(new FabricItemSettings());

    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.create(
		new Identifier(ExampleMod.MOD_ID_STRING, "general"))
		.icon(() -> new ItemStack(ModIconItem))
		.build();

    public static final RegistryKey<DimensionOptions> DIM_KEY = RegistryKey.of(
        Registry.DIMENSION_OPTIONS,
        new Identifier(ExampleMod.MOD_ID_STRING, "ether")
        );

    public static RegistryKey<World> WORLD_KEY = RegistryKey.of(
        Registry.DIMENSION,
        DIM_KEY.getValue()
    );

    // public static final RegistryKey<DimensionType> DIM_TYPE_KEY = RegistryKey.of(
    //     Registry.DIMENSION_TYPE_KEY,
    //     new Identifier(ExampleMod.MOD_ID_STRING, "ether_type")
    //     ); 

    public static void initRegistry(){
        System.out.println("Beginning registry.");
        Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID_STRING, "mod_group_icon"), ModIconItem);
        
        registerItems();
        registerBlocks();
        registerFluids();

        //Registry.register(Registry.CHUNK_GENERATOR, new Identifier(ExampleMod.MOD_ID_STRING, "ether"), EtherChunkGenerator.CODEC);

        WORLD_KEY = RegistryKey.of(Registry.DIMENSION, new Identifier(ExampleMod.MOD_ID_STRING, "ether"));

    }

    public static void registerItems(){
        System.out.println("Registering Items.");
        for(ItemRegistry item : ItemRegistry.values()){
            Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID_STRING, item.toString()), item.item);
        }
    }

    public static void registerBlocks(){
        System.out.println("Registering Blocks.");
        for(BlockRegistry block : BlockRegistry.values()){
            Registry.register(Registry.BLOCK, new Identifier(ExampleMod.MOD_ID_STRING, block.toString()), block.block);
            Registry.register(Registry.ITEM,  new Identifier(ExampleMod.MOD_ID_STRING, block.toString()), block.block_item);
        }
        
        for(DimensionBlockRegistry block : DimensionBlockRegistry.values()){
            Registry.register(Registry.BLOCK, new Identifier(ExampleMod.MOD_ID_STRING, block.toString()), block.block);
            Registry.register(Registry.ITEM,  new Identifier(ExampleMod.MOD_ID_STRING, block.toString()), block.block_item);
        }
    }

    public static void registerFluids(){
        System.out.println("Registering Fluids.");
        for(FluidBlockRegistry fluidBlock : FluidBlockRegistry.values()){
            Registry.register(Registry.BLOCK, new Identifier(ExampleMod.MOD_ID_STRING, fluidBlock.toString()), fluidBlock.fluid_block);
            Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID_STRING, fluidBlock.toString()), fluidBlock.block_item);
        }

        for(FluidRegistry fluid : FluidRegistry.values()){
            setupFluidRendering(fluid.still, fluid.flowing, new Identifier("minecraft", "water"), fluid.color);
            BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), fluid.still,  fluid.flowing);
        }
    }
    
	public static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
		final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
		final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");
 
		// If they're not already present, add the sprites to the block atlas
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(stillSpriteId);
			registry.register(flowingSpriteId);
		});
 
		final Identifier fluidId = Registry.FLUID.getId(still);
		final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");
 
		final Sprite[] fluidSprites = { null, null };
 
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return listenerId;
			}
 
			/**
			 * Get the sprites from the block atlas when resources are reloaded
			 */
			@Override
			public void apply(ResourceManager resourceManager) {
				final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
				fluidSprites[0] = atlas.apply(stillSpriteId);
				fluidSprites[1] = atlas.apply(flowingSpriteId);
			}
		});
 
		// The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
		final FluidRenderHandler renderHandler = new FluidRenderHandler()
		{
			@Override
			public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
				return fluidSprites;
			}
 
			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return color;
			}
		};
 
		FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);

    }
    
}
