package fr.lenoob.skydef.scoreboard;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.entity.Player;

public class ScoreboardManager {

    public Map<UUID, FastBoard> map;
    private UUID uuid;

    public ScoreboardManager() { this.map = new HashMap<>();}

    public void createBoard(Player player, Consumer<FastBoard> fastBoard){
        FastBoard board = new FastBoard(player);
        fastBoard.accept(board);
        this.map.put(player.getUniqueId(), board);
    }

    public void removeBoard(Player player){
        FastBoard fastBoard = this.map.remove(player.getUniqueId());
        fastBoard.delete();
    }

    public void updateBoard(Player player, Consumer<FastBoard> fastBoard){
        FastBoard board = this.map.get(player.getUniqueId());
        fastBoard.accept(board);
    }
    public void updateBoards(Consumer<FastBoard> fastBoard){
        FastBoard board = this.map.get(uuid);
        fastBoard.accept(board);
    }

    public FastBoard getFastBoard(Player player){
        return this.map.get(player.getUniqueId());
    }

}
