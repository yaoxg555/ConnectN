package rmit.connectN.net.server;

import java.net.Socket;

import rmit.connectN.protocol.common.Message;
import rmit.connectN.protocol.common.Protocol;
import rmit.connectN.util.ioc.IoC;

final class ConnectionDaemon implements Runnable {
	MessageChannel mc;
	IoC ioc;
	boolean stopFlag = false;
	
	public ConnectionDaemon(Socket socket, IoC ioc) {
		mc = new MessageChannel(socket);
		this.ioc = ioc;
	}

	@Override
	public void run() {
		while(!stopFlag)
		{
			Message m;
			try {
				Object obj = mc.read();
				if (obj instanceof Message)
				{
					m = (Message)obj;
					((Protocol)ioc.resolve(Protocol.class, m)).process(m, mc);
				}
				else
					throw new Exception("Unregconised Message");
			}
			catch(Throwable e)
			{
				e.printStackTrace();
			}
		}
	}
}
