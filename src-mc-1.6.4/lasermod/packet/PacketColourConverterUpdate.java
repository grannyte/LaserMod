package lasermod.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import lasermod.LaserMod;
import lasermod.api.ILaser;
import lasermod.api.LaserInGame;
import lasermod.api.LaserRegistry;
import lasermod.tileentity.TileEntityColourConverter;
import lasermod.tileentity.TileEntityReflector;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.common.network.Player;

/**
 * @author ProPercivalalb
 **/
public class PacketColourConverterUpdate extends PacketBase {

    public int x, y, z;
    public LaserInGame laser;
    public int colour;

    public PacketColourConverterUpdate() {}

    public PacketColourConverterUpdate(int x, int y, int z, TileEntityColourConverter colourConvertor) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.laser = colourConvertor.laser;
        this.colour = colourConvertor.colour;
    }

	@Override
	public void readPacketData(DataInputStream data, Player p) throws IOException {
	    x = data.readInt();
	    y = data.readInt();
	    z = data.readInt();
	    colour = data.readInt();
	    if(data.readBoolean()) {
		    double strength = data.readDouble();
		    int laserCount = data.readInt();
		    ArrayList<ILaser> laserList = new ArrayList<ILaser>();
		    for(int j = 0; j < laserCount; ++j)
		    	laserList.add(LaserRegistry.getLaserFromId(data.readUTF()));
		    int side = data.readInt();
		    LaserInGame laserInGame = new LaserInGame(laserList).setStrength(strength).setSide(side);
		    laserInGame.red = data.readInt();
		    laserInGame.green = data.readInt();
		    laserInGame.blue = data.readInt();
		    laser = laserInGame;
	    }
	   
	}

	@Override
	public void writePacketData(DataOutputStream data)  throws IOException {
		data.writeInt(x);
	    data.writeInt(y);
	    data.writeInt(z);
	    data.writeInt(colour);
	    data.writeBoolean(this.laser != null);
	    if(this.laser != null) {
		    data.writeDouble(laser.getStrength());
		    data.writeInt(laser.laserCount());
		    for(ILaser laser2 : laser.getLaserType()) {
			    data.writeUTF(LaserRegistry.getIdFromLaser(laser2));	
		    }
		    data.writeInt(laser.getSide());
		    data.writeInt(laser.red);
		    data.writeInt(laser.green);
		    data.writeInt(laser.blue);
	    }
	}

	@Override
	public void processPacket() {
		LaserMod.proxy.handleColourConverterPacket(this);
	}

	@Override
	public String getChannel() {
		return "laser:colour";
	}
}
