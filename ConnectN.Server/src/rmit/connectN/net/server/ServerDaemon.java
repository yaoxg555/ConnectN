package rmit.connectN.net.server;

import java.io.*;
import java.net.*;

import rmit.connectN.util.ioc.IoC;

public class ServerDaemon implements Runnable{
	IoC ioc;
	boolean stopFlag = false;
	
	public ServerDaemon(IoC ioc) {
		this.ioc = ioc;
	}
	
	@Override
	public void run()
	{
		ServerSocket serverSocket = null;
		try {
		    serverSocket = new ServerSocket(Configuration.port);
		} 
		catch (IOException e) {
		    System.out.printf("Could not listen on port: %d\n", Configuration.port);
		    System.exit(-1);
		}
		System.out.println("Start Daemon");
		while(!stopFlag)
		{
			try {
				Thread t = new Thread(new ConnectionDaemon(serverSocket.accept(), ioc));
				System.out.println("Spawn new thread");
				t.start();
			}
			catch(Exception e)
			{
				System.out.println("Unable to create/start thread");
			}
		}
		System.out.println("End Daemon");
	}
}

