package rmit.connectN.protocol.messagebus.server.handler;

import rmit.connectN.protocol.message.chating.ChatMessage;
import rmit.connectN.protocol.messagebus.server.common.EndPoint;
import rmit.connectN.protocol.messagebus.server.message.Chat;
import rmit.connectN.protocol.util.messagebus.Message;

public class ChatHandler extends BaseMessageHandler {

	@Override
	public void process(EndPoint src, EndPoint dst, Message _msg) {
		if (src == dst)
			return;
		
		Chat msg = (Chat)_msg;
		
		ChatMessage cm = new ChatMessage();
		cm.text = msg.text;
		cm.time = msg.time;
		try {
			dst.write(cm);
		} catch (Throwable e) {}
	}

}
