package net.jmt.minecraft.jradar.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Gui;

public class EntityHandler 
{
	private int radius;
	private Minecraft mc;
	private Gui g;
	
	public EntityHandler(Minecraft mc, Gui g, int radius)
	{
		this.radius = radius;
		this.mc = mc;
		this.g = g;
	}
	
	public void renderSurroundingEntities(int centerX, int centerY)
	{
        EntityTextures ets = new EntityTextures(mc, g);
        
        for(int as = 0; as < mc.theWorld.loadedEntityList.size(); as++)
        {
        	if(!(mc.theWorld.loadedEntityList.get(as) instanceof EntityLiving))
        	{
        		continue;
        	}
        	EntityLiving ent = (EntityLiving) mc.theWorld.loadedEntityList.get(as);

        	int xx = (int) (mc.thePlayer.posX - ent.posX);
        	int zz = (int) (mc.thePlayer.posZ - ent.posZ);
        	
        	if(xx*xx + zz*zz > radius*radius)
        	{
        		continue;
        	}
        	
        	GL11.glColor4f(255, 255, 255, 255);
            GL11.glPushMatrix();
            GL11.glTranslatef(centerX + xx, centerY + zz, 0);
            GL11.glRotatef(mc.thePlayer.rotationYaw, 0,0,1);
            GL11.glScalef(0.5F, 0.5F, 0);
            
            ets.renderTexture(ent, -4, -4);
        	//g.drawTexturedModalRect(-4, -4, 0, 0, 8, 8);
            
        	GL11.glScalef(2, 2, 0);
        	GL11.glTranslatef(-xx, -zz, 0);
        	GL11.glPopMatrix();
        	
        }
	}
}
