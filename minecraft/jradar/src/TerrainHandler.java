package net.jmt.minecraft.jradar.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.*;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.Gui;
import net.minecraft.src.MathHelper;

public class TerrainHandler 
{
	private int lowerBound;
	private int upperBound;
	private Minecraft mc;
	private Gui g;
	
	public TerrainHandler(Minecraft mc, Gui g, int lowerBound, int upperBound)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.mc = mc;
		this.g = g;
		
	}
	
	private void colorBasedOnMultiplier(int color)
	{
	        float f = (float) (color >> 24 & 0xff) / 255F;
	        float f1 = (float) (color >> 16 & 0xff) / 255F;
	        float f2 = (float) (color >> 8 & 0xff) / 255F;
	        float f3 = (float) (color & 0xff) / 255F;
	        GL11.glColor4f(f1, f2, f3, f);
	}
	
	public void drawTopLayer(int centerX, int centerY)
	{
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/terrain.png")); //binding terrain texture
		
		 for(int as = lowerBound; as<upperBound; as++)
	        {
	        	int pX = as;
	        	for(int aa = lowerBound; aa<upperBound; aa++)
	        	{
	        		int pZ = aa;
	        		
	        		int bX = (int) (mc.thePlayer.posX + pX); //block posX
	        		int bZ = (int) (mc.thePlayer.posZ + pZ); //block posZ
	        		int bY = mc.theWorld.getHeightValue(bX, bZ)-1; //formatted block posY (-1 for compensation)
	        		
	        		int bI = mc.theWorld.getBlockId(bX,bY,bZ);//block id
	        		
	    	        double d =  MathHelper.clamp_float(mc.theWorld.getWorldChunkManager().getBiomeGenAt(bX, bY).getFloatTemperature(), 0.0F, 1.0F); //biome info
	    	        double d1 =  MathHelper.clamp_float(mc.theWorld.getWorldChunkManager().getBiomeGenAt(bX, bY).getFloatRainfall(), 0.0F, 1.0F); //biome info
	    	        
	    	        double disx = mc.thePlayer.posX-bX;
	    	        double disz = mc.thePlayer.posZ-bZ;
	    	        
	    	        if((disx - 0.5)*(disx - 0.5) + (disz - 0.5)*(disz - 0.5) > (upperBound)*(upperBound))
	    	        {
	    	        	continue;
	    	        }
	        		int k8 = Block.blocksList[bI].blockIndexInTexture; //block position in texture
	        		
	        		if(bI == 2)
	        		{
	          			colorBasedOnMultiplier(ColorizerGrass.getGrassColor(d, d1));          			
	        		} else if(bI == 18)
	        		{
	          			colorBasedOnMultiplier(ColorizerFoliage.getFoliageColor(d, d1));
	        		} else if(bI == 8 || bI == 9)
	        		{	        			
	        			colorBasedOnMultiplier(0xff000fff);
	        		} else
	        		{
	        			GL11.glColor4f(255, 255, 255, 255);
	        		}
	        		g.drawTexturedModalRect(disx - 1, disz - 1, k8 % 16 << 4, (k8 >> 4) << 4, 1, 1);
	        	}
	        }
	}
}
