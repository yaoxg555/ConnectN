package rmit.connectN.net.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Socket socket;
	
	public Client() throws Exception
	{
		if (socket == null)
			socket = new Socket(Configuration.host, Configuration.port);
	}
	
	public Object read() throws Exception
	{
		return (new ObjectInputStream(socket.getInputStream())).readObject();
	}
	
	public void write(Object obj) throws Exception
	{
		(new ObjectOutputStream(socket.getOutputStream())).writeObject(obj);
	}
	
	public void close() throws Exception
	{
		if (socket == null)
			socket.close();
	}
}
