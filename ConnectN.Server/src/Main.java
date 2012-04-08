import rmit.connectN.net.client.Client;
import rmit.connectN.net.server.ServerDaemon;
import rmit.connectN.net.server.data.GameManager;
import rmit.connectN.protocol.common.Message;
import rmit.connectN.protocol.message.listing.CreateGame;
import rmit.connectN.protocol.message.listing.ListResult;
import rmit.connectN.protocol.message.listing.RequestList;
import rmit.connectN.util.ioc.IoC;

public class Main {
	
	public static void main(String[] args)
	{
		IoC ioc = new IoC();
		try {
			ioc.bind(new rmit.connectN.protocol.handler.listing.server.IoC(GameManager.instance()));
		}
		catch(Throwable e){}
		
		
		new Thread(new ServerDaemon(ioc)).start();
		Client mc;
		try {			
			mc = new Client();
			Message msg = new RequestList();
			mc.write(msg);
			msg = (ListResult)mc.read();
			System.out.printf("%d\n",((ListResult)msg).gameList.size());
			msg = new CreateGame();
			((CreateGame)msg).name = "test";
			mc.write(msg);
			msg = new RequestList();
			mc.write(msg);
			msg = (ListResult)mc.read();
			System.out.printf("%d\n",((ListResult)msg).gameList.size());
			System.out.printf("%s\n",((ListResult)msg).gameList.get(0).name);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
