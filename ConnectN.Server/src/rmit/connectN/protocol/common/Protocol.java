package rmit.connectN.protocol.common;

import rmit.connectN.net.common.MessageChannelWritable;

public interface Protocol
{
	public void process(Message msg, MessageChannelWritable mc) throws Throwable;
}