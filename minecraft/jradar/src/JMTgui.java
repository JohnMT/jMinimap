package net.jmt.minecraft.jradar.src;

import java.awt.Color;
import java.util.List;

import net.jmt.minecraft.jradar.src.EntityTextures;
import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Gui;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

public class JMTgui 
{
	private Gui g;
	public JMTgui(Gui gui)
	{
		g = gui;
	}
	
	public void drawFilledBorderRect(int x1, int y1, int x2, int y2, int c1, int c2)
	{
		/*SNIP*/
	}
	
	public void drawHollowBorderRect(int x1, int y1, int x2, int y2, int c2)
	{
		/*SNIP*/
	}
	
    public static void drawHollowCircle(float cx, float cy, float r, int num_segments, float lw, int c) 
    { 
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;
    	float theta = (float) (2 * 3.1415926 / (num_segments)); 
    	float p = (float) Math.cos(theta);//calculate the sine and cosine
    	float s = (float) Math.sin(theta);
    	float t;
        GL11.glColor4f(f1, f2, f3, f);
    	float x = r;
    	float y = 0;//start at angle = 0  
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lw);
    	GL11.glBegin(GL11.GL_LINE_LOOP); 
    	for(int ii = 0; ii < num_segments; ii++) 
    	{ 
    		GL11.glVertex2f(x + cx, y + cy);//final vertex vertex 
            
    		//rotate the stuff
    		t = x;
    		x = p * x - s * y;
    		y = s * t + p * y;
    	} 
    	GL11.glEnd(); 
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        
        /*
        float f = (float) (c >> 24 & 0xff) / 255F;
  		float f1 = (float) (c >> 16 & 0xff) / 255F;
  		float f2 = (float) (c >> 8 & 0xff) / 255F;
  		float f3 = (float) (c & 0xff) / 255F;
  		GL11.glEnable(GL11.GL_BLEND);
  		GL11.glDisable(GL11.GL_TEXTURE_2D);
  		GL11.glBlendFunc(770, 771);
  		GL11.glBegin(mode ? GL11.GL_POLYGON : GL11.GL_LINE_LOOP);// true = filled, false = hollow
  		GL11.glColor4f(f1, f2, f3, f);
  		for(double as = 0; as < 2*Math.PI; as+= 0.1)
  		{
      		GL11.glVertex2d(sx+Math.cos(as)*r, sy+Math.sin(as)*r);
  		}
  		GL11.glEnd();
  		GL11.glEnable(GL11.GL_TEXTURE_2D);
  		GL11.glDisable(GL11.GL_BLEND);
         */
    }
       
    public static void drawFullCircle(int cx, int cy, double r, int c) 
    {
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for(int i = 0; i <= 360; i++) 
        {
            double x = Math.sin((i * 3.141526D / 180)) * r;
            double y = Math.cos((i * 3.141526D / 180)) * r;
            GL11.glVertex2d(cx + x, cy + y);
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        
        /*
        float f = (float) (c >> 24 & 0xff) / 255F;
  		float f1 = (float) (c >> 16 & 0xff) / 255F;
  		float f2 = (float) (c >> 8 & 0xff) / 255F;
  		float f3 = (float) (c & 0xff) / 255F;
  		GL11.glEnable(GL11.GL_BLEND);
  		GL11.glDisable(GL11.GL_TEXTURE_2D);
  		GL11.glBlendFunc(770, 771);
  		GL11.glBegin(mode ? GL11.GL_POLYGON : GL11.GL_LINE_LOOP);// true = filled, false = hollow
  		GL11.glColor4f(f1, f2, f3, f);
  		for(double as = 0; as < 2*Math.PI; as+= 0.1)
  		{
      		GL11.glVertex2d(sx+Math.cos(as)*r, sy+Math.sin(as)*r);
  		}
  		GL11.glEnd();
  		GL11.glEnable(GL11.GL_TEXTURE_2D);
  		GL11.glDisable(GL11.GL_BLEND);
         */
    }
    
    public static void drawRoundRect(int sx, int sy, int width, int height, int r, int c) 
    {
    	/*SNIP*/
    }
    
    public static void drawGradientCircle(double cx, double cy, double r, int c1, int c2)
    {
    	/*SNIP*/
    }
    
    public static void drawRoundRectOutline(int sx, int sy, int width, int height, int r, float lw, int c) 
    {
    	/*SNIP*/
    }
            
    public static void drawOutlinedBoundingBox(AxisAlignedBB axisalignedbb)
    {
    	/*SNIP*/
    }
    
    public static void drawIsoscolesTriangleOutline(double cx, double cy, int sizefactor, float theta, int lw, int c)
    {
    	GL11.glTranslated(cx, cy, 0);
    	GL11.glRotatef(180 + theta, 0F,0F,1.0F);
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lw);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        
        GL11.glVertex2d(0,(1*sizefactor));
        GL11.glVertex2d((1*sizefactor),-(1*sizefactor));
        GL11.glVertex2d(-(1*sizefactor),-(1*sizefactor));
        
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glRotatef(-180 - theta, 0F,0F,1.0F);
    	GL11.glTranslated(-cx, -cy, 0);
    }
    
    public static void drawIsoscolesTriangle(double cx, double cy, int sizefactor, float theta, int c)
    {
    	GL11.glTranslated(cx, cy, 0);
    	GL11.glRotatef(180 + theta, 0F,0F,1.0F);
        float f = (float) (c >> 24 & 0xff) / 255F;
        float f1 = (float) (c >> 16 & 0xff) / 255F;
        float f2 = (float) (c >> 8 & 0xff) / 255F;
        float f3 = (float) (c & 0xff) / 255F;
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(GL11.GL_TRIANGLES);
        
        GL11.glVertex2d(0,(1*sizefactor));
        GL11.glVertex2d((1*sizefactor),-(1*sizefactor));
        GL11.glVertex2d(-(1*sizefactor),-(1*sizefactor));
        
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glRotatef(-180 - theta, 0F,0F,1.0F);
    	GL11.glTranslated(-cx, -cy, 0);
    }
    
    public static void drawFunkyTriangle(double cx, double cy, int sizefactor, float theta, int c)
    {
    	/*SNIP*/
    }
    
    public static void drawEquilateralTriangle(int cx, int cy, int r, int theta, int c)
    {
    	/*SNIP*/
    }
    
    public static void drawLine(int x1, int y1, int x2, int y2, float lw, int c)
    {
    	/*SNIP*/
    }
    
    /*SNIP*/
    
    public static void drawRect(int x, int y, int w, int h, int c)
    {
    	/*SNIP*/
    }
    
    public static void drawTexturedRect(double x, double y, int texture, int texcoordx, int texcoordy, int w, int h)
    {
    	/*SNIP*/
    }

}
