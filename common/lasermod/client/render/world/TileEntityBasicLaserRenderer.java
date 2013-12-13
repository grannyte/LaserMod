package lasermod.client.render.world;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import lasermod.core.helper.LogHelper;
import lasermod.tileentity.TileEntityBasicLaser;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author ProPercivalalb
 */
public class TileEntityBasicLaserRenderer extends TileEntitySpecialRenderer {

    public void renderBasicLaser(TileEntityBasicLaser basicLaser, double x, double y, double z, float tick) {
    	GL11.glPushMatrix();
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	GL11.glEnable(GL12.GL_RESCALE_NORMAL);
     	GL11.glDisable(GL11.GL_ALPHA_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING); //Make the line see thought blocks
        GL11.glDepthMask(false);
        //GL11.glDisable(GL11.GL_DEPTH_TEST); //Make the line see thought blocks
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator tessellator = Tessellator.instance;
        GL11.glColor4f(0F / 255F, 0F / 255F, 0F / 255F, 1.0F);
      // if(tick % 0.1 == 0.1)
        basicLaser.last = basicLaser.getLaserBox(x, y, z);
        
    	drawBoundingBox(basicLaser.last);
         
        //GL11.glEnable(GL11.GL_DEPTH_TEST); //Make the line see thought blocks
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glEnable(GL11.GL_LIGHTING); //Make the line see thought blocks
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

    }
    
    public static void drawBoundingBox(AxisAlignedBB boundingBox) {
	    Tessellator tessellator = Tessellator.instance;
	    tessellator.startDrawingQuads();
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	    tessellator.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
	    tessellator.draw();
	}

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        renderBasicLaser((TileEntityBasicLaser) tileEntity, x, y, z, tick);
    }
}
