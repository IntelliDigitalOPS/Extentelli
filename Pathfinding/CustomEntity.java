package intelli.extentelli.pathfinder;
 
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
 
public class CustomEntity extends EntityCreature {
 
	public CustomEntity(Location loc, Player player, EntityTypes en) {
        super(en, ((CraftWorld) loc.getWorld()).getHandle());
 
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
 
        this.setHealth(10);
 
        this.setGoalTarget(((CraftPlayer)player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, true);
    }
 
    @Override
    protected void initPathfinder() { // very bad
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalEntity(this, 1.50, 15));
        this.goalSelector.a(2, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    }
}
