package rmit.connectN.protocol.messagebus.server.message;

import rmit.connectN.net.server.data.Game;
import rmit.connectN.protocol.util.messagebus.Message;

public class StartGame implements Message {
	public Game game;
}
