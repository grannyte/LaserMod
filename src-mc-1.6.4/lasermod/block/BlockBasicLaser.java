package lasermod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lasermod.api.ILaserReciver;
import lasermod.core.helper.LogHelper;
import lasermod.tileentity.TileEntityBasicLaser;
import lasermod.tileentity.TileEntityReflector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author ProPercivalalb
 */
public class BlockBasicLaser extends BlockContainer {

	public Icon frontIcon;
	public Icon backIcon;
	public Icon sideIcon;
	
	public BlockBasicLaser(int id) {
		super(id, Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBasicLaser();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
	    this.frontIcon = iconRegister.registerIcon("lasermod:basicLaserFront");
	    this.backIcon = iconRegister.registerIcon("lasermod:basicLaserBack");
	    this.sideIcon = iconRegister.registerIcon("lasermod:basicLaserSide");
	}


	public static int getOrientation(int par1) {
		return par1 & 7;
	}
	    
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		int meta = getOrientation(world.getBlockMetadata(x, y, z));

		if (meta > 5)
	        return this.frontIcon;
	    if (side == meta) {
	        return this.frontIcon; 
	    }
	    else {
	    	return side == Facing.oppositeSide[meta] ? backIcon : sideIcon;
	    }
    }
	    
	@Override
	public Icon getIcon(int side, int par2) {
	    int meta = 3;

	    if (meta > 5)
	        return this.frontIcon;
	    if (side == meta) {
	        return this.frontIcon;
	    }
	    else {
	    	return side == Facing.oppositeSide[meta] ? backIcon : sideIcon;
	    }
	}

	@Override
	public boolean isOpaqueCube() {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }
	
	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		 int rotation = determineOrientation(par1World, x, y, z, par5EntityLiving);
		 par1World.setBlockMetadataWithNotify(x, y, z, rotation, 2);
	}
	
	public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLivingBase par4EntityLivingBase) {
        if (MathHelper.abs((float)par4EntityLivingBase.posX - (float)par1) < 2.0F && MathHelper.abs((float)par4EntityLivingBase.posZ - (float)par3) < 2.0F) {
            double d0 = par4EntityLivingBase.posY + 1.82D - (double)par4EntityLivingBase.yOffset;

            if (d0 - (double)par2 > 2.0D) {
                return 1;
            }

            if ((double)par2 - d0 > 0.0D)
            {
                return 0;
            }
        }

        int l = MathHelper.floor_double((double)(par4EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }
}
