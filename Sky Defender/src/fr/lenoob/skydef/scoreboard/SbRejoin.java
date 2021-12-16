package fr.lenoob.skydef.scoreboard;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.BLUE;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.utils.SDState;


public class SbRejoin {

    private ScoreboardManager scoreboardManager;
    public SbRejoin(ScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    
	}	

	public static SbRejoin instance;
	public static SbRejoin getInstance(){
	        return instance;
	}
    public int timer = 0;
    public int Episode = 1;
    public World w = Bukkit.getWorld("world");
    public WorldBorder wb = w.getWorldBorder();
    public void game(Player player) throws IOException {
        scoreboardManager.createBoard(player, fastBoard -> {
            fastBoard.updateTitle(AQUA +"Sky Defender I");

            Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
                @Override
                public void run() {
                	String ligne;
                	FileReader fr;
                	BufferedReader lecteurAvecBuffer = null;
                	try {
						fr= new FileReader("plugins/SkyDefender/time.txt");
						lecteurAvecBuffer = new BufferedReader(fr);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
                	try {
						while((ligne = lecteurAvecBuffer.readLine()) != null) {
							fastBoard.updateLine(3,ligne);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                	String lineep ;
                	FileReader frep ;
                	BufferedReader lecteurAvecBufferEp = null;
                	try {
						frep = new FileReader("plugins/SkyDefender/ep.txt");
						lecteurAvecBufferEp = new BufferedReader(frep);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
                	try {
						while((lineep = lecteurAvecBufferEp.readLine()) != null) {
							fastBoard.updateLine(2, "Jour : §3"+lineep);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                	if(main.getInstance().isState(SDState.GAME)) {
                		fastBoard.updateLine(11,  GOLD+"PVP : "+RED+"✗");
                		fastBoard.updateLine(13, GOLD+"Nether : "+RED+"✗");
                	}
                	if(main.getInstance().isState(SDState.NETHER)) {
                		fastBoard.updateLine(11,  GOLD+"PVP : "+RED+"✗");
                		fastBoard.updateLine(13, GOLD+"Nether : "+GREEN+"✗");
                	}
                	if(main.getInstance().isState(SDState.PVP)) {
                		fastBoard.updateLine(11,  GOLD+"PVP : "+GREEN+"✔");
                		fastBoard.updateLine(13, GOLD+"Nether : "+RED+"✗");
                	}
                	
                    if(timer == 1170) {
                    	player.sendMessage("§3[§6Fallen Kingdomm's§3]§6 Début du jour : &3" + Episode+1 + BLUE+" &6dans 30 secondes");
                    }
                    if(timer == 1190) {
                    	player.sendMessage("§3[§6Fallen Kingdomm's§3]§6 Début du jour : &3" + Episode+1 + BLUE+" &6dans 10 secondes");
                    }
                    fastBoard.updateLine(0, AQUA + "Joueurs en game :"+main.getInstance().playersInGame.size());
                    fastBoard.updateLine(4,  GOLD + "Bordure : §3" + wb.getSize() + " X "+ wb.getSize());
                    fastBoard.updateLine(6, GOLD+ "Attaquants : §c"+main.getInstance().Attaquants.getEntries().size());
					fastBoard.updateLine(8, GOLD+"Défenseur : §3"+main.getInstance().Défenseur.getEntries().size());
					fastBoard.updateLine(2, GOLD+"Jour : §3"+Episode);
                }
            }, 20, 20);
            	

            fastBoard.updateLines(
                    AQUA + "Joueurs en game :"+main.getInstance().playersInGame.size() ,
                    WHITE + "§l>>-------------<<",
                    GOLD + "Jour : §3" ,
                    GOLD + "Heure : " ,
                    GOLD + "Bordure : " + wb.getSize() + " X "+ wb.getSize(),
                    WHITE + "§l>>-------------<<",
                    GOLD+ "Attaquants : §c"+main.getInstance().Attaquants.getEntries().size(),
                    WHITE +" ",
                    GOLD+"Défenseurss : §3"+main.getInstance().Défenseur.getEntries().size(),
                    WHITE +" ",
                    WHITE + "§l>>-------------<<",
                    GOLD+"PVP : "+RED+"✗",
                    GOLD+"",
                    GOLD+"Nether : "+RED+"✗"


            );
        });
    }

}



