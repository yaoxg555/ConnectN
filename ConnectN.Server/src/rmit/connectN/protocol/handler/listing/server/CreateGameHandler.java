package rmit.connectN.protocol.handler.listing.server;

import rmit.connectN.net.common.MessageChannelWritable;
import rmit.connectN.net.server.data.*;
import rmit.connectN.protocol.common.Message;
import rmit.connectN.protocol.common.Protocol;
import rmit.connectN.protocol.messagebus.server.common.EndPoint;
import rmit.connectN.protocol.messagebus.server.message.Chat;
import rmit.connectN.protocol.messagebus.server.message.StartGame;

public class CreateGameHandler implements Protocol {
	GameManager gm;
	@Override
	public void process(Message msg, MessageChannelWritable mc) {
		rmit.connectN.protocol.message.listing.CreateGame cg = (rmit.connectN.protocol.message.listing.CreateGame)msg;
		EndPoint ep = new EndPoint(mc);
		gm.create(cg.name)
			.subscribe(ep, new Chat())
			.subscribe(ep, new StartGame());
	}
	
	public CreateGameHandler(GameManager gm)
	{
		this.gm = gm;
	}
}
