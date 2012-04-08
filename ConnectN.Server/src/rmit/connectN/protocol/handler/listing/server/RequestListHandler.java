package rmit.connectN.protocol.handler.listing.server;

import java.util.*;

import rmit.connectN.net.common.MessageChannelWritable;
import rmit.connectN.net.server.data.*;
import rmit.connectN.protocol.common.Message;
import rmit.connectN.protocol.common.Protocol;
import rmit.connectN.protocol.message.listing.ListGameViewModel;
import rmit.connectN.protocol.message.listing.ListResult;


public class RequestListHandler implements Protocol {
	GameManager gm;
	@Override
	public void process(Message msg, MessageChannelWritable mc) throws Throwable {
		ListResult lr = new ListResult();
		lr.gameList = new ArrayList<ListGameViewModel>();
		for(Game g : gm.toIterable())
		{
			ListGameViewModel gvm = new ListGameViewModel();
			gvm.id = g.id;
			gvm.name = g.name;
			lr.gameList.add(gvm);
		}
		mc.write(lr);
	}
	
	public RequestListHandler(GameManager gm)
	{
		this.gm = gm;
	}
}
