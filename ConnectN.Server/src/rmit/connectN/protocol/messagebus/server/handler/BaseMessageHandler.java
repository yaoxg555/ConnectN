package rmit.connectN.protocol.messagebus.server.handler;

import rmit.connectN.protocol.messagebus.server.common.EndPoint;
import rmit.connectN.protocol.util.messagebus.Message;

public abstract class BaseMessageHandler implements
		rmit.connectN.protocol.util.messagebus.MessageHandler {

	@Override
	public void process(rmit.connectN.protocol.util.messagebus.EndPoint src, rmit.connectN.protocol.util.messagebus.EndPoint dst, Message msg)
	{
		this.process((EndPoint)src, (EndPoint)dst, msg);
	}
	public abstract void process(EndPoint src, EndPoint dst, Message msg);

}
