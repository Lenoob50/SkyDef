package fr.lenoob.skydef.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lenoob.skydef.main;

public class CommandTeam implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(msg.equalsIgnoreCase("team")) {
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args.length == 0) {
					p.sendMessage("La commande est /team <team> <player>");
				}
				if(args.length >=1) {
					if(args[0].equalsIgnoreCase("D�fenseur")) {
						main.getInstance().D�fenseur.addEntry(args[1].toString());
						Bukkit.broadcastMessage("�3"+args[1].toString()+" �2� rejoins l'�quipe des �3D�fenseur");
					}					
					if(args[0].equalsIgnoreCase("Attaquants")) {
						main.getInstance().Attaquants.addEntry(args[1].toString());
						Bukkit.broadcastMessage("�c"+args[1].toString()+" �2� rejoins l'�quipe des �cAttaquants");
					}
					
				}
			}
		}
		return false;
	}

}
