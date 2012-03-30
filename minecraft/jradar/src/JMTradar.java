package net.jmt.minecraft.jradar.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.ScaledResolution;

public class JMTradar 
{
	private int posX;
	private int posY;
	private Minecraft mc;
	private Gui g;
	private EntityHandler eh;
	private TerrainHandler th;
	private JMTgui jg;
	private ScaledResolution sr;
	
	public static boolean enabled = true;
	public static boolean entities = true;
	public static boolean terrain = true;
	
	public JMTradar(int posX, int posY, Minecraft mc, Gui g)
	{
		this.mc = mc;
		this.g = g;
		this.posX = posX;
		this.posY = posY;
		eh = new EntityHandler(mc, g, 48);
		th = new TerrainHandler(mc, g, -50, 50);
		jg = new JMTgui(g);
		
		sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
	}
	
	public void setLocation(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	public void drawRadar()
	{
		GL11.glPushMatrix();
    	GL11.glTranslatef(posX, posY, 0);
    	GL11.glRotatef(-mc.thePlayer.rotationYaw, 0, 0, 1);
    	
        //jg.drawHollowCircle(0, 0, (float)63, 360, (float)3*sr.scaleFactor, 0xff00f000);
        renderDirections();
        try
        {
        	if(terrain)
        	{
        		th.drawTopLayer(0, 0);
        	}
			if(entities)
			{
				eh.renderSurroundingEntities(0, 0);
			}
		}
		catch(Exception e)
		{
		//probably the nether...
		}		
		
		GL11.glRotatef(mc.thePlayer.rotationYaw, 0, 0, 1);
	;
        jg.drawHollowCircle(0, 0, (float)50, 360, (float)4*sr.scaleFactor, 0xff00f000);
        jg.drawIsoscolesTriangle(0, 0, 2, 0, 0xaaffff00);
        jg.drawIsoscolesTriangleOutline(0, 0, 2, 0, 2, 0xffffff00);
        
    	GL11.glTranslatef(-posX, -posY, 0);
        GL11.glPopMatrix();
	}

	private void renderDirections()
	{
		//needs work...lazy method
		GL11.glTranslatef(0, 56, 0);
		GL11.glRotatef(mc.thePlayer.rotationYaw, 0, 0, 1);
		g.drawCenteredString(mc.fontRenderer, "N", 0, -4, 0xffffff);
		GL11.glRotatef(-mc.thePlayer.rotationYaw, 0, 0, 1);
		GL11.glTranslatef(0, -56, 0);
		
		GL11.glTranslatef(56, 0, 0);
		GL11.glRotatef(mc.thePlayer.rotationYaw, 0, 0, 1);
		g.drawCenteredString(mc.fontRenderer, "E", 0, -4, 0xffffff);
		GL11.glRotatef(-mc.thePlayer.rotationYaw, 0, 0, 1);
		GL11.glTranslatef(-56, 0, 0);
		
		GL11.glTranslatef(0, -56, 0);
		GL11.glRotatef(mc.thePlayer.rotationYaw, 0, 0, 1);
		g.drawCenteredString(mc.fontRenderer, "S", 0, -4, 0xffffff);
		GL11.glRotatef(-mc.thePlayer.rotationYaw, 0, 0, 1);
		GL11.glTranslatef(0, 56, 0);
		
		GL11.glTranslatef(-56, 0, 0);
		GL11.glRotatef(mc.thePlayer.rotationYaw, 0, 0, 1);
		g.drawCenteredString(mc.fontRenderer, "W", 0, -4, 0xffffff);
		GL11.glRotatef(-mc.thePlayer.rotationYaw, 0, 0, 1);
		GL11.glTranslatef(56, 0, 0);
	}
}
