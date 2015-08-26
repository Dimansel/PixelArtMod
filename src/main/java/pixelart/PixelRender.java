package pixelart;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PixelRender implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		 GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		 tessellator.startDrawingQuads();
		 tessellator.setNormal(0.0F, -1.0F, 0.0F);
		 renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
		 tessellator.draw();
		 tessellator.startDrawingQuads();
		 tessellator.setNormal(0.0F, 1.0F, 0.0F);
		 renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, metadata));
		 tessellator.draw();
		 tessellator.startDrawingQuads();
		 tessellator.setNormal(0.0F, 0.0F, -1.0F);
		 renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, metadata));
		 tessellator.draw();
		 tessellator.startDrawingQuads();
		 tessellator.setNormal(0.0F, 0.0F, 1.0F);
		 renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, metadata));
		 tessellator.draw();
		 tessellator.startDrawingQuads();
		 tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		 renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, metadata));
		 tessellator.draw();
		 tessellator.startDrawingQuads();
		 tessellator.setNormal(1.0F, 0.0F, 0.0F);
		 renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, metadata));
		 tessellator.draw();
		 GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public int getRenderId() {
		return PixelBlock.renderID;
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		PixelBlock pb = (PixelBlock)block;
		PixelTile pt = (PixelTile)world.getTileEntity(x, y, z);
		
		int l = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float f = (float)(l >> 16 & pt.r) / 255.0F;
        float f1 = (float)(l >> 8 & pt.g) / 255.0F;
        float f2 = (float)(l & pt.b) / 255.0F;
        
        renderer.renderStandardBlockWithAmbientOcclusion(block, x, y, z, f, f1, f2);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}
