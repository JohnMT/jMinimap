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
	        		if(aa*aa + as*as > upperBound*upperBound)
	        		{
	        			continue; //confine radar to a circle using pythagorean theorem
	        		}
	        		int pZ = aa;
	        		
	        		int bX = (int) (mc.thePlayer.posX + pX); //block posX
	        		int bZ = (int) (mc.thePlayer.posZ + pZ); //block posZ
	        		int bY = mc.theWorld.getHeightValue(bX, bZ)-1; //formatted block posY (-1 for compensation)
	        		
	        		int bI = mc.theWorld.getBlockId(bX,bY,bZ);//block id
	        		
	    	        double d =  MathHelper.clamp_float(mc.theWorld.getWorldChunkManager().getBiomeGenAt(bX, bY).getFloatTemperature(), 0.0F, 1.0F); //biome info
	    	        double d1 =  MathHelper.clamp_float(mc.theWorld.getWorldChunkManager().getBiomeGenAt(bX, bY).getFloatRainfall(), 0.0F, 1.0F); //biome info
	    	        
	        		int k8 = Block.blocksList[bI].blockIndexInTexture; //block position in texture
	        		
	        		GL11.glTranslatef(0.5F, 0.5F, 0F); //compensating for integer restrictions of Minecraft methods
	        		if(bI == 2)
	        		{
	          			//g.drawRect(-pX - 1,-pZ - 1,-pX,-pZ, ColorizerGrass.getGrassColor(d, d1));
	          			
	          			colorBasedOnMultiplier(ColorizerGrass.getGrassColor(d, d1));          			
	          			g.drawTexturedModalRect(-pX - 1, -pZ - 1, k8 % 16 << 4, (k8 >> 4) << 4, 1, 1);
	        		} else if(bI == 18)
	        		{
	        			//g.drawRect(-pX - 1,-pZ - 1,-pX,-pZ, ColorizerFoliage.getFoliageColor(d, d1));
	        			
	          			colorBasedOnMultiplier(ColorizerFoliage.getFoliageColor(d, d1));
	          			g.drawTexturedModalRect(-pX - 1, -pZ - 1, k8 % 16 << 4, (k8 >> 4) << 4, 1, 1);
	        		} else if(bI == 8 || bI == 9)
	        		{
	        			//g.drawRect(-pX - 1,-pZ - 1,-pX,-pZ, mc.theWorld.getWorldChunkManager().getBiomeGenAt(bX, bZ).waterColorMultiplier * 0xffffffff);
	        			//g.drawRect(-pX - 1,-pZ - 1,-pX,-pZ, 0xff000fff);
	        			
	        			colorBasedOnMultiplier(0xff000fff);
	          			g.drawTexturedModalRect(-pX - 1, -pZ - 1, k8 % 16 << 4, (k8 >> 4) << 4, 1, 1);
	        		} else
	        		{
	        			GL11.glColor4f(255, 255, 255, 255);
	            		g.drawTexturedModalRect(-pX - 1, -pZ - 1, k8 % 16 << 4, (k8 >> 4) << 4, 1, 1);
	        		}
	        		GL11.glTranslatef(-0.5F, -0.5F, 0F);
	        	}
	        }
	}
}
