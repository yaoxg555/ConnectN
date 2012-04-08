package rmit.connectN.net.common;

import rmit.connectN.protocol.common.Message;

public interface MessageChannelWritable {
	public void write(Message obj) throws Exception;
	public void close();
}