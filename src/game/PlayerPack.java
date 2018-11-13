package game;

import java.util.*;

public class PlayerPack {

	public List<Player> players = new ArrayList<Player>();
	public Pack pack = new Pack(new ArrayList<Card>());
	
	
	public PlayerPack(List<Player> players,Pack pack) {
		this.players = players;
		this.pack = pack;
	}
	
}
