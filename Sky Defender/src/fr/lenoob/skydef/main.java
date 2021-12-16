package fr.lenoob.skydef;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import fr.lenoob.skydef.commands.CommandSDStop;
import fr.lenoob.skydef.commands.CommandStart;
import fr.lenoob.skydef.commands.CommandTeam;
import fr.lenoob.skydef.commands.CommandeRevive;
import fr.lenoob.skydef.commands.TeamTab;
import fr.lenoob.skydef.listeners.CutClean;
import fr.lenoob.skydef.listeners.InventoryInterract;
import fr.lenoob.skydef.listeners.OnBreak;
import fr.lenoob.skydef.listeners.OnDamageTaken;
import fr.lenoob.skydef.listeners.OnInterract;
import fr.lenoob.skydef.listeners.OnJoin;
import fr.lenoob.skydef.listeners.OnPlace;
import fr.lenoob.skydef.listeners.OnPlayerDeath;
import fr.lenoob.skydef.listeners.OnQuitt;
import fr.lenoob.skydef.listeners.OnTalk;
import fr.lenoob.skydef.listeners.OnWalk;
import fr.lenoob.skydef.scoreboard.ScoreboardManager;
import fr.lenoob.skydef.utils.SDState;
import fr.lenoob.skydef.utils.Title;



public class main extends JavaPlugin {
	PluginManager pm = Bukkit.getPluginManager();
	public Scoreboard sb;
	public SDState current;
	public Team Défenseur;
	public Team Attaquants;
	public Team Mort;
	public ArrayList<UUID> playersInGame = new ArrayList<>();

	public Title title = new Title();
	
	@Override
	public void onEnable() {
		World world = Bukkit.getWorld("world");
		WorldBorder wb = world.getWorldBorder();
		current = SDState.WAIT;
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Attaquants = sb.registerNewTeam("Attaquants");
		Attaquants.setAllowFriendlyFire(true);
		Attaquants.setDisplayName("§c[Attaquants]");
		Attaquants.setPrefix("§c[Attaquants]");
		Défenseur = sb.registerNewTeam("Défenseur");
		Défenseur.setAllowFriendlyFire(false);
		Défenseur.setDisplayName("§3[Défenseur]");
		Défenseur.setPrefix("§3[Défenseur]");
		Mort = sb.registerNewTeam("Mort");
		Mort.setAllowFriendlyFire(false);
		Mort.setDisplayName("§7[Mort]");
		Mort.setPrefix("§7[Mort]");
		instance = this;
		Plugin name = pm.getPlugin(this.getName());
		getLogger().info("["+name+"]:"+" Plugin Chargé");
		pm.registerEvents(new OnJoin(), this);
		pm.registerEvents(new OnInterract(), this);
		pm.registerEvents(new InventoryInterract(), this);
		pm.registerEvents(new OnWalk(), this);
		pm.registerEvents(new OnDamageTaken(), this);
		pm.registerEvents(new OnTalk(), this);
		pm.registerEvents(new CutClean(), this);
		pm.registerEvents(new OnPlayerDeath(), this);
		pm.registerEvents(new OnQuitt(), this);
		pm.registerEvents(new OnPlace(), this);
		pm.registerEvents(new OnBreak(), this);
		getCommand("sdstart").setExecutor(new CommandStart());
		getCommand("revive").setExecutor(new CommandeRevive());
		getCommand("sdstop").setExecutor(new CommandSDStop());
		getCommand("team").setExecutor(new CommandTeam());
		getCommand("team").setTabCompleter(new TeamTab());
		wb.setCenter(new Location(Bukkit.getWorld("world"),0,50,0));
		wb.setDamageAmount(1.0);
		wb.setSize(2000);
		world.setDifficulty(Difficulty.PEACEFUL);
		world.setPVP(false);
		registerHealth();
	}
	
	@Override
	public void onLoad() {
		Plugin name = pm.getPlugin(this.getName());
		getLogger().info("["+name+"]:"+" Plugin en chargement");
	}
	
	@Override
	public void onDisable() {
		Plugin name = pm.getPlugin(this.getName());
		getLogger().info("["+name+"]:"+" Plugin déchargé");
	}
	
	public static main instance;
	public static main getInstance() {
		return instance;
	}
	
	public SDState getState() {
		return current;
	}
	
	public void setState(SDState state) {
		current = state;
	}
	
	public boolean isState(SDState state) {
		return current == state;
	}
	
	public void registerHealth() {
		if(sb.getObjective("health")!= null) {
			sb.getObjective("helath").unregister();
		}
		Objective h = sb.registerNewObjective("health", "health");
		h.setDisplayName(ChatColor.RED+"❤");
		h.setDisplaySlot(DisplaySlot.BELOW_NAME);
	}
	public void addInGame(Player player) {
		UUID uuid = player.getUniqueId();
		playersInGame.add(uuid);
	}

	public void death(Player player) {
		UUID uuid = player.getUniqueId();
		playersInGame.remove(uuid);
		player.setGameMode(GameMode.SPECTATOR);
		this.Mort.addEntry(player.getDisplayName());
	}
	
	
	public ScoreboardManager sm = new ScoreboardManager();

}
