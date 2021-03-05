package intelli.extentelli.pathfinder;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.base.Preconditions;

import net.minecraft.server.v1_16_R3.EntityTypes;

public class Pathfinder {
	
	//By @Intelli -> https://github.com/IntelliDigitalOPS/
	
	private Entity entity;
	private Plugin plugin;
	private boolean attacking = false;
	private boolean controlled = false;
	private double attackDistance = 5;
	private double attackDamage = 1.5D;
	
	public Pathfinder(Entity entity, Plugin plugin) {
		Preconditions.checkArgument(bukkitToNms(entity.getType())!=null,
				"That entity is currently not supported");
		this.entity = entity;
		attackInsentient();
	}
	
	@SuppressWarnings("rawtypes")
	private EntityTypes bukkitToNms(EntityType bukkitType) {
		switch(bukkitType) {
		case CHICKEN:
			return EntityTypes.CHICKEN;
		case COW:
			return EntityTypes.COW;
		case PIG:
			return EntityTypes.PIG;
		case SALMON:
			return EntityTypes.SALMON;
		case COD:
			return EntityTypes.COD;
		case SHEEP:
			return EntityTypes.SHEEP;
		case SQUID:
			return EntityTypes.SQUID;
		case PANDA:
			return EntityTypes.PANDA;
		case BAT:
			return EntityTypes.BAT;
		case HORSE:
			return EntityTypes.HORSE;
		case MULE:
			return EntityTypes.MULE;
		case DONKEY:
			return EntityTypes.DONKEY;
		case TROPICAL_FISH:
			return EntityTypes.TROPICAL_FISH;
		case TURTLE:
			return EntityTypes.TURTLE;
		default:
			return null;
		}
	}
	
	private void attackInsentient() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!attacking)return;
				for(Player p:Bukkit.getOnlinePlayers())
					if(p.getLocation().distance(entity.getLocation())<=attackDistance)
						p.damage(attackDamage, entity);
			}
		}.runTaskTimer(plugin, 0L, 19L);
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public boolean isPathfinding() {
		return this.controlled;
	}
	
	public boolean isAttacking() {
		return this.attacking;
	}
	
	public void setAttacking(boolean status) {
		this.attacking=status;
	}
	
	public void setAttackDistance(double distance) {
		this.attackDistance=distance;
	}
	
	public void setAttackDamage(double damage) {
		this.attackDamage=damage;
	}
	
	public void pathfindPlayer(Player player) {
		CustomEntity cMob = new CustomEntity(entity.getLocation(), player, bukkitToNms(entity.getType()));
		((CraftWorld) player.getWorld()).getHandle().addEntity(cMob);
		entity.remove();
		entity = Bukkit.getEntity(cMob.getUniqueID());
		controlled=true;
	}
	
	public void stopPathfinding() {
		Preconditions.checkArgument(controlled, "Cannot stop when not already pathfinding");
		Entity newen = entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
		entity.remove();
		entity = newen;
		controlled=false;
	}
}
