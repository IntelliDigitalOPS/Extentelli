package intelli.extentelli;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import com.google.common.base.Preconditions;

public class Cuboid {
	
	//By @Intelli 
	
	private Location cornerA;
	private Location cornerB;
	private Random rand = new Random();
	
	public Cuboid(Location corner1, Location corner2) {
		this.cornerA = Preconditions.checkNotNull(corner1, "Illegal argument, corner cannot be null");
		this.cornerB = Preconditions.checkNotNull(corner2, "Illegal argument, corner cannot be null");
		Preconditions.checkArgument(corner1.getWorld()==corner2.getWorld(),
				"Illegal Argument, corner locations do not exist in the same world");
	}
	public Location[] getCorners() {
		return new Location[] {cornerA, cornerB};
	}
	public ArrayList<Location> getLocArray(){
		ArrayList<Location> locs = new ArrayList<Location>();
		int xMax = Integer.max(cornerA.getBlockX(), cornerB.getBlockX());
	    int xMin = Integer.min(cornerA.getBlockX(), cornerB.getBlockX());
	    int yMax = Integer.max(cornerA.getBlockY(), cornerB.getBlockY());
	    int yMin = Integer.min(cornerA.getBlockY(), cornerB.getBlockY());
	    int zMax = Integer.max(cornerA.getBlockZ(), cornerB.getBlockZ());
	    int zMin = Integer.min(cornerA.getBlockZ(), cornerB.getBlockZ());
	    for (int x = xMin; x <= xMax; x++)
	        for (int y = yMin; y <= yMax; y++)
	            for (int z = zMin; z <= zMax; z++)
	            	locs.add(new Location(cornerA.getWorld(), x, y, z));
	    return locs;     	
	}
	public ArrayList<Block> getBlockArray(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		int xMax = Integer.max(cornerA.getBlockX(), cornerB.getBlockX());
	    int xMin = Integer.min(cornerA.getBlockX(), cornerB.getBlockX());
	    int yMax = Integer.max(cornerA.getBlockY(), cornerB.getBlockY());
	    int yMin = Integer.min(cornerA.getBlockY(), cornerB.getBlockY());
	    int zMax = Integer.max(cornerA.getBlockZ(), cornerB.getBlockZ());
	    int zMin = Integer.min(cornerA.getBlockZ(), cornerB.getBlockZ());
	    for (int x = xMin; x <= xMax; x++)
	        for (int y = yMin; y <= yMax; y++)
	            for (int z = zMin; z <= zMax; z++)
	            	blocks.add(cornerA.getWorld().getBlockAt(x, y, z));
	    return blocks;
	}
	public void setType(Material type) {
		for(Block b:getBlockArray())
			b.setType(type);
	}
	public void setType(Material type, float percent) {
		for(Block b:getBlockArray())
			b.setType((rand.nextInt(Math.round(percent/100F))==0)?type:b.getType());
	}
	public boolean contains(Location loc) {
		return getLocArray().contains(loc);
	}
	public boolean contains(Block block) {
		return getBlockArray().contains(block);
	}
	public boolean contains(Entity e) {
		return getLocArray().contains(e.getLocation());
	}
}
