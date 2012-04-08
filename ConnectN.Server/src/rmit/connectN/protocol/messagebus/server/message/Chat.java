package rmit.connectN.protocol.messagebus.server.message;

import java.util.Date;

import rmit.connectN.protocol.util.messagebus.Message;

public class Chat implements Message {
	public String text;
	public Date time;
}
