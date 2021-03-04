package intelli.template;//Might want to change the package declaration

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

public class ClassName extends JavaPlugin implements Listener, CommandExecutor, TabCompleter{
	
	ChatColor green = ChatColor.GREEN;
	ChatColor red = ChatColor.RED;
	ChatColor yellow = ChatColor.YELLOW;
	
	@Override
	public void onEnable() {
		System.out.println("Started");
		//Startup Logic
	}
	@Override
	public void onDisable() {
		System.out.println("Stopped");
		//Stop Logic
	}
	private static final String[] COMMANDS = {"start", "stop", "changethis"};//Change to match args
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
		List<String> completions = new ArrayList<>();
		StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        Collections.sort(completions);
        return completions;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("commandname"))return false;
		//Command Logic
		return true;
	}
	@EventHandler
	public void customName(CreatureSpawnEvent event) {
		//Event Logic (Change the event)
	}
}
