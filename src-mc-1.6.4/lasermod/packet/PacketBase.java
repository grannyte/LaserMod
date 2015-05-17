package lasermod.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.Player;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * @author ProPercivalalb
 **/
public abstract class PacketBase {
	
	public abstract void readPacketData(DataInputStream data, Player p) throws IOException;

	public abstract void writePacketData(DataOutputStream data)  throws IOException;

	public abstract void processPacket();
	
	public abstract String getChannel();
	
	public final Packet250CustomPayload buildPacket() {
		 ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	     DataOutputStream data = new DataOutputStream(bytes);
	     try {
			this.writePacketData(data);
		 } 
	     catch (Exception e) {
			e.printStackTrace();
		 }
	     Packet250CustomPayload pack = new Packet250CustomPayload();
	     pack.channel = this.getChannel();
	     pack.data = bytes.toByteArray();
	     pack.length = bytes.size();
	     return pack;
	}
	
	public final void readPacket(Packet250CustomPayload packet, Player p) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
        DataInputStream dis = new DataInputStream(bis);
        this.readPacketData(dis, p);
        this.processPacket();
	}
}
