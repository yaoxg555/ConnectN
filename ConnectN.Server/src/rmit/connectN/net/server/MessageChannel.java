package rmit.connectN.net.server;

import java.io.*;
import java.net.*;

import rmit.connectN.net.common.MessageChannelWritable;
import rmit.connectN.protocol.common.Message;

class MessageChannel implements MessageChannelWritable {
	Socket socket;
	boolean isClosed = false;

	public MessageChannel(Socket socket) {
		this.socket = socket;
	}
	
	public Object read() throws Exception
	{
		if (!isClosed)
		{
			return (new ObjectInputStream(socket.getInputStream())).readObject();
		}
		else
			throw new Exception("Read error!");
	}	
	
	public synchronized void write(Message obj) throws Exception
	{
		if (!isClosed)
		{
			(new ObjectOutputStream(socket.getOutputStream())).writeObject(obj);
		}
	}
	
	public synchronized void close()
	{
		isClosed = true;
		try {
			socket.close();
		} 
		catch(Exception e)
		{
		}
	}
}
