package rmit.connectN.protocol.handler.listing.server;

import rmit.connectN.net.server.data.*;
import rmit.connectN.protocol.common.Protocol;
import rmit.connectN.util.ioc.Bindable;
import rmit.connectN.util.ioc.Delegate;

public class IoC implements Bindable {
	GameManager gm;
	
	public IoC(GameManager gm)
	{
		this.gm = gm;
	}
	
	@Override
	public void bindRelations(rmit.connectN.util.ioc.IoC ioc) throws Throwable {
		ioc.bind(Protocol.class, new Delegate(){
			@Override
			public Object create(Object... params) {
				return new RequestListHandler(gm);
			}
		}, rmit.connectN.protocol.message.listing.RequestList.class).bind(Protocol.class, new Delegate(){
			@Override
			public Protocol create(Object... params) {
				return new JoinGameHandler(gm);
			}
		}, rmit.connectN.protocol.message.listing.JoinGame.class).bind(Protocol.class, new Delegate(){
			@Override
			public Protocol create(Object... params) {
				return new CreateGameHandler(gm);
			}
		}, rmit.connectN.protocol.message.listing.CreateGame.class);
	}
}
