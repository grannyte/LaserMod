package lasermod.block.laser;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import lasermod.api.ILaser;
import lasermod.core.proxy.ClientProxy;

/**
 * @author ProPercivalalb
 */
public class IceLaser implements ILaser {

	@Override
	public void performActionOnEntitiesServer(List<Entity> entities, int direction) {
		for(Entity entity : entities) {
			
		}
	}
	
	@Override
	public void performActionOnEntitiesClient(List<Entity> entities, int direction) {
		if(entities.contains(ClientProxy.mc.thePlayer))
			if(ClientProxy.slowedByIceLaser < 100)
				ClientProxy.slowedByIceLaser += 6;
	}
	
	@Override
	public void performActionOnEntitiesBoth(List<Entity> entities, int direction) {
		for(Entity entity : entities) {
			double verticalSpeed = 0.120000000000000003D;
            double maxSpeed = 0.19999999999999999D;
            
            
            
		}
	}
	
	@Override
	public boolean shouldRenderLaser(EntityPlayer player, int direction) {
		return true;
	}
}
