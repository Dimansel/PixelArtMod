package pixelart;


import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelart.builder.BlockBuilder;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="pixel_art", name="Pixel Art", version="1.0")

public class ModFile {
	@SidedProxy(clientSide = "pixelart.ClientProxy", serverSide = "pixelart.CommonProxy")
	public static CommonProxy proxy;
	public static SimpleNetworkWrapper network;
	
	public static Block pBlock = new PixelBlock().setBlockName("pBlock");
	public static Block bBlock = new BlockBuilder().setBlockName("bBlock");
	public static Item rgb = new RGBmc().setUnlocalizedName("rgb_mc");
	
	@Mod.Instance("pixel_art")
	public static ModFile instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("pixel_channel");
	    network.registerMessage(ColorMessage.Handler.class, ColorMessage.class, 0, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.initMod();
		
		GameRegistry.registerTileEntity(PixelTile.class, "TileEntityPixel");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerBlock(pBlock, ItemBlockPixel.class, "pBlock");
		GameRegistry.registerBlock(bBlock, "bBlock");
		GameRegistry.registerItem(rgb, "rgb");
		GameRegistry.addShapedRecipe(new ItemStack(rgb), new Object[] {
			"SIS",
			"RGB",
			"SIS",
			'S', Items.redstone,
			'I', Items.iron_ingot,
			'R', new ItemStack(Items.dye, 1, 1),
			'G', new ItemStack(Items.dye, 1, 2),
			'B', new ItemStack(Items.dye, 1, 4)
		});
		GameRegistry.addShapedRecipe(new ItemStack(pBlock, 8), new Object[] {
			"III",
			"IMI",
			"III",
			'M', rgb,
			'I', Blocks.wool
		});
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		
	}
}