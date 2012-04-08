package rmit.connectN.net.server.data;

import java.util.*;

import rmit.connectN.net.server.data.Game.Status;
import rmit.connectN.util.ioc.IoC;

public class GameManager {
	//initiation not thread safe
	private static GameManager _gm;
	
	Map<UUID, Game> map = Collections.synchronizedMap(new HashMap<UUID, Game>());
	IoC ioc;
	
	GameManager() {
		
		
	}
	
	public static GameManager instance()
	{
		if(_gm == null)
			_gm = new GameManager();
		return _gm;
	}
	
	public Game create(String name)
	{
		Game g = new Game(UUID.randomUUID(), name, ioc);
		map.put(g.id, g);
		return g;
	}
	
	public Iterable<Game> toIterable()
	{
		return map.values();
	}
	
	public Game get(UUID id)
	{
		return map.get(id);
	}
}
