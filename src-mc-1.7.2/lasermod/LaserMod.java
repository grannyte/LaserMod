package lasermod;

import lasermod.api.LaserRegistry;
import lasermod.laser.DefaultLaser;
import lasermod.lib.Reference;
import lasermod.network.NetworkManager;
import lasermod.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author ProPercivalalb
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
public class LaserMod {

	@Instance(value = Reference.MOD_ID)
	public static LaserMod instance;
	
	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
    public static CommonProxy proxy;
	
	public static NetworkManager NETWORK_MANAGER;
	
	/** Laser Mod Creative tab **/
	public static CreativeTabs tabLaser = new CreativeTabs("tabLaser") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ModItems.screwdriver;
		}
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ModBlocks.inti();
		ModItems.inti();
		ModEntities.inti();
		
		proxy.onPreLoad();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		NETWORK_MANAGER = new NetworkManager();
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
			
		proxy.registerHandlers();
	}
	
	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event) {
		LaserRegistry.registerLaser("default", new DefaultLaser());
	}
}
