package pixelart;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void initMod() {
		super.initMod();
		
		PixelBlock.renderID = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler pRender = new PixelRender();
		RenderingRegistry.registerBlockHandler(PixelBlock.renderID, pRender);
	}
}
