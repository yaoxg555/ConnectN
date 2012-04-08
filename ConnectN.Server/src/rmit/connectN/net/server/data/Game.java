package rmit.connectN.net.server.data;

import java.util.UUID;

import rmit.connectN.protocol.util.messagebus.EndPoint;
import rmit.connectN.protocol.util.messagebus.Message;
import rmit.connectN.protocol.util.messagebus.MessageBus;
import rmit.connectN.util.ioc.IoC;

public class Game {
	public enum Status {
		Waiting,
		Playing,
		Completed
	}	
	
	public UUID id;
	public String name;
	public GameData data;
	public Status status;
	
	IoC ioc;
	
	MessageBus queue;
	
	public Game(UUID id, String name, IoC ioc)
	{
		this.id = id;
		this.name = name;
		status = Status.Waiting;	
		data = new GameData();
		
		queue = new MessageBus(new rmit.connectN.util.messagebus.MessageBus(), ioc);
	}
	
	public Game unsubscribe(EndPoint ep, Message msg)
	{
		queue.unsubscribe(ep, msg);
		return this;
	}
	
	public Game subscribe(EndPoint ep, Message msg)
	{
		queue.subscribe(ep, msg);
		return this;
	}
	
	public Game dispatch(EndPoint ep, Message msg)
	{
		queue.dispatch(ep, msg);
		return this;
	}
		
	public Game start()
	{
		status = Status.Playing;
		return this;
	}
	
	public Game complete()
	{
		status = Status.Completed;
		return this;
	}
}
