package lasermod.core.proxy;

import lasermod.ModBlocks;
import lasermod.client.render.block.TileEntityAdvancedLaserRenderer;
import lasermod.client.render.block.TileEntityBasicLaserRenderer;
import lasermod.client.render.block.TileEntityColourConverterRenderer;
import lasermod.client.render.block.TileEntityReflectorRenderer;
import lasermod.client.render.item.ItemReflectorRenderer;
import lasermod.packet.PacketAdvancedLaserUpdate;
import lasermod.packet.PacketColourConverterUpdate;
import lasermod.packet.PacketReflectorUpdate;
import lasermod.tileentity.TileEntityAdvancedLaser;
import lasermod.tileentity.TileEntityBasicLaser;
import lasermod.tileentity.TileEntityColourConverter;
import lasermod.tileentity.TileEntityReflector;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author ProPercivalalb
 */
public class ClientProxy extends CommonProxy {

	public static int slowedByIceLaser = 40;
	public static Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onPreLoad() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBasicLaser.class, new TileEntityBasicLaserRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedLaser.class, new TileEntityAdvancedLaserRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReflector.class, new TileEntityReflectorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityColourConverter.class, new TileEntityColourConverterRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.reflector.blockID, new ItemReflectorRenderer());
	}
	
	@Override
	public void handleReflectorPacket(PacketReflectorUpdate packet) {
		World world = this.mc.theWorld;
		TileEntity tileEntity = world.getBlockTileEntity(packet.x, packet.y, packet.z);
		
		if(!(tileEntity instanceof TileEntityReflector)) 
			return;
		TileEntityReflector reflector = (TileEntityReflector)tileEntity;
		reflector.openSides = packet.openSides;
		reflector.lasers = packet.lasers;
	}
	
	@Override
	public void handleAdvancedLaserPacket(PacketAdvancedLaserUpdate packet) {
		World world = this.mc.theWorld;
		TileEntity tileEntity = world.getBlockTileEntity(packet.x, packet.y, packet.z);
		
		if(!(tileEntity instanceof TileEntityAdvancedLaser)) 
			return;
		TileEntityAdvancedLaser advancedLaser = (TileEntityAdvancedLaser)tileEntity;
		advancedLaser.upgrades = packet.upgrades;
	}
	
	@Override
	public void handleColourConverterPacket(PacketColourConverterUpdate packet) {
		World world = this.mc.theWorld;
		TileEntity tileEntity = world.getBlockTileEntity(packet.x, packet.y, packet.z);
		
		if(!(tileEntity instanceof TileEntityColourConverter)) 
			return;
		TileEntityColourConverter colourConverter = (TileEntityColourConverter)tileEntity;
		colourConverter.laser = packet.laser;
		colourConverter.colour = packet.colour;
		world.markBlockForRenderUpdate(packet.x, packet.y, packet.z);
	}
	
	@Override
	public void registerHandlers() {
		
	}
	
	@Override
	public int armorRender(String str) {
		return RenderingRegistry.addNewArmourRendererPrefix(str);
	}
}
