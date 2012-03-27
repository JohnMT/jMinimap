package net.jmt.minecraft.jradar.src;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.JMTgui;

public class JMTradar 
{
	private int posX;
	private int posY;
	private Minecraft mc;
	private Gui g;
	private EntityHandler eh;
	private TerrainHandler th;
	private JMTgui jg;
	private int sf;
	
	public JMTradar(int posX, int posY, int scaleFactor, Minecraft mc, Gui g)
	{
		this.mc = mc;
		this.g = g;
		this.posX = posX;
		this.posY = posY;
		this.sf = scaleFactor;
		eh = new EntityHandler(mc, g, 49);
		th = new TerrainHandler(mc, g, -50, 50);
		jg = new JMTgui(g);
	}
	
	public void drawRadar()
	{
		GL11.glPushMatrix();
    	GL11.glTranslatef(posX, posY, 0);
    	GL11.glRotatef(-mc.thePlayer.rotationYaw, 0, 0, 1);
		
		th.drawTopLayer(0, 0);
		eh.renderSurroundingEntities(0, 0);
		
		GL11.glRotatef(mc.thePlayer.rotationYaw, 0, 0, 1);
		
        jg.drawHollowCircle(0, 0, (float)50, 360, (float)3*sf, 0xff00f000);
        jg.drawIsoscolesTriangle(0, 0, 2, 0, 0xaaffff00);
        jg.drawIsoscolesTriangleOutline(0, 0, 2, 0, 2, 0xffffff00);
        
        GL11.glPopMatrix();
	}

}
