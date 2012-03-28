package net.jmt.minecraft.jradar.src;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.*;
import net.minecraft.src.*;

public class EntityTextures 
{
	private Minecraft mc;
	private List <EntityTexture> textures;
	private Gui g;
	public EntityTextures(Minecraft mc, Gui g)
	{
		this.mc = mc;
		this.g = g;
		textures = new ArrayList();
		initTextures();
	}
	
	private void initTextures()
	{
		textures.add(new EntityTexture(EntityZombie.class, 0, 0));
		textures.add(new EntityTexture(EntityWolf.class, 8, 0));
		textures.add(new EntityTexture(EntityGolem.class, 16, 0));
		textures.add(new EntityTexture(EntitySquid.class, 24, 0));
		textures.add(new EntityTexture(EntitySpider.class, 32, 0));
		textures.add(new EntityTexture(EntitySnowman.class, 40, 0));
		textures.add(new EntityTexture(EntitySlime.class, 48, 0));
		
		textures.add(new EntityTexture(EntitySkeleton.class, 0, 8));
		textures.add(new EntityTexture(EntitySilverfish.class, 8, 8));
		textures.add(new EntityTexture(EntitySheep.class, 16, 8));
		textures.add(new EntityTexture(EntityMooshroom.class, 24, 8));
		textures.add(new EntityTexture(EntityPigZombie.class, 32, 8));
		textures.add(new EntityTexture(EntityPig.class, 40, 8));
		textures.add(new EntityTexture(EntityOcelot.class, 48, 8));
		
		textures.add(new EntityTexture(EntityMagmaCube.class, 0, 16));
		textures.add(new EntityTexture(EntityGhast.class, 8, 16));
		textures.add(new EntityTexture(EntityBlaze.class, 16, 16));
		textures.add(new EntityTexture(EntityEnderman.class, 24, 16));
		textures.add(new EntityTexture(EntityCreeper.class, 32, 16));
		textures.add(new EntityTexture(EntityCow.class, 40, 16));
		textures.add(new EntityTexture(EntityChicken.class, 48, 16));
		
		textures.add(new EntityTexture(EntityPlayer.class, 0, 24));
		textures.add(new EntityTexture(EntityCaveSpider.class, 8, 24));
		//textures.add(new EntityTexture(EntityGreyCat.class, 16, 24));
		//textures.add(new EntityTexture(EntityOrangeCat.class, 24, 24));
		//textures.add(new EntityTexture(EntityBlackCat.class, 32, 24));
		textures.add(new EntityTexture(EntityVillager.class, 40, 24));
		textures.add(new EntityTexture(EntityDragon.class, 48, 24));
	}
	
	public void renderTexture(EntityLiving c, int x, int y)
	{
		String loc = "/allmobs.png";
		int tex = mc.renderEngine.getTexture(loc);
		mc.renderEngine.bindTexture(tex);
		for(int as = 0; as < textures.size(); as++)
		{
			EntityTexture et = textures.get(as);

			if(c.getClass() == et.cls)
			{
				try
				{
					g.drawTexturedModalRect(x, y, et.X, et.Y, 8, 8);
				} catch(Exception e)
				{
					//hmmmmm
				}
			}
		}
	}

	
	private class EntityTexture
	{
		public int X;
		public int Y;
		public Class cls;
		public EntityTexture(Class <? extends EntityLiving> e, int texX, int texY)
		{
			cls = e;
			X = texX;
			Y = texY;
		}
	}
}
