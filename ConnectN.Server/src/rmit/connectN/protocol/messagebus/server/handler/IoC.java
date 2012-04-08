package rmit.connectN.protocol.messagebus.server.handler;

import rmit.connectN.protocol.messagebus.server.message.Chat;
import rmit.connectN.protocol.util.messagebus.MessageHandler;
import rmit.connectN.util.ioc.Bindable;
import rmit.connectN.util.ioc.Delegate;

public class IoC implements Bindable {

	@Override
	public void bindRelations(rmit.connectN.util.ioc.IoC ioc) throws Throwable {
		ioc.bind(MessageHandler.class, new Delegate(){
			@Override
			public Object create(Object... params) {
				return new ChatHandler();
			}
		}, Chat.class);

	}

}
