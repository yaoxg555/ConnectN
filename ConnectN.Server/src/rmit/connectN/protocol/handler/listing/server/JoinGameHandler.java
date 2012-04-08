package rmit.connectN.protocol.handler.listing.server;

import rmit.connectN.net.common.MessageChannelWritable;
import rmit.connectN.net.server.data.*;
import rmit.connectN.protocol.common.Message;
import rmit.connectN.protocol.common.Protocol;
import rmit.connectN.protocol.messagebus.server.common.EndPoint;
import rmit.connectN.protocol.messagebus.server.message.Chat;
import rmit.connectN.protocol.messagebus.server.message.StartGame;

public class JoinGameHandler implements Protocol {
	GameManager gm;
	@Override
	public void process(Message _msg, MessageChannelWritable mc) {
		rmit.connectN.protocol.message.listing.JoinGame msg = (rmit.connectN.protocol.message.listing.JoinGame)_msg;
		EndPoint ep = new EndPoint(mc);
		StartGame startGameMsg = new StartGame();
		
		startGameMsg.game = gm.get(msg.id).start()
			.subscribe(ep, new Chat())
			.subscribe(ep, startGameMsg);
		
		startGameMsg.game.dispatch(ep, startGameMsg);
	}
	
	public JoinGameHandler(GameManager gm)
	{
		this.gm = gm;
	}
}
