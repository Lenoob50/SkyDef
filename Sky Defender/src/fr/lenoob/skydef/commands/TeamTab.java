package fr.lenoob.skydef.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TeamTab implements TabCompleter {
	
	public List<String> arguments = new ArrayList<String>();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(arguments.isEmpty()) {
			arguments.add("Défenseur");
			arguments.add("Attaquants");
		}
		
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for(String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		return null;
	}

}
