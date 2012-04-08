package rmit.connectN.protocol.util.messagebus;

import java.util.*;

import rmit.connectN.util.ioc.IoC;

public class MessageBus {
	rmit.connectN.util.messagebus.MessageBus mb;
	IoC ioc;
	Map<EndPointMessage, rmit.connectN.util.messagebus.MessageHandler> map = Collections.synchronizedMap(new HashMap<EndPointMessage, rmit.connectN.util.messagebus.MessageHandler>());
	
	class EndPointMessage
	{
		EndPoint ep;
		Class c;
		
		public EndPointMessage(EndPoint ep, Class c)
		{
			this.ep = ep;
			this.c = c;
		}

		public boolean equals(Object obj) {
	        if (obj == null)
	        	return false;
	        if (obj == this)
	            return true;
	        if (obj.getClass() != getClass())
	            return false;

	        EndPointMessage foo = (EndPointMessage)obj;
	        return foo.c == c && foo.ep == ep;
	    }
		
	    public int hashCode() {
	        return (ep.hashCode() << 16 + c.hashCode());
	    }		
	}
	
	class MessageIncludesSrcEndPoint
	{
		EndPoint ep;
		Message msg;
		
		public MessageIncludesSrcEndPoint(EndPoint ep, Message msg)
		{
			this.ep = ep;
			this.msg = msg;
		}
	}
	
	public MessageBus(rmit.connectN.util.messagebus.MessageBus mb, IoC ioc)
	{
		this.mb = mb;
		this.ioc = ioc;
	}
	
	public MessageBus subscribe(EndPoint ep, Message msg)
	{
		Class c = msg.getClass();
		EndPointMessage epm = new EndPointMessage(ep, c);
		if (map.containsKey(epm))
			mb.unsubscribe(epm, map.get(epm));		
			
		final EndPoint dst = ep;
		rmit.connectN.util.messagebus.MessageHandler mh = new rmit.connectN.util.messagebus.MessageHandler(){
			@Override
			public void process(Object obj) {
				if(obj instanceof MessageIncludesSrcEndPoint)
				{
					MessageIncludesSrcEndPoint msgEp = (MessageIncludesSrcEndPoint)obj;
					Message msg = msgEp.msg;
					try {
						((MessageHandler)ioc.resolve(MessageHandler.class, msg)).process(msgEp.ep, dst, msg);
					}
					catch (Throwable e) {
					}
				}
			}
		};
		map.put(epm, mh);
		mb.subscribe(c, mh);
		return this;
	}
	public MessageBus unsubscribe(EndPoint ep, Message msg)
	{
		Class c = msg.getClass();
		EndPointMessage epm = new EndPointMessage(ep, c);
		if (!map.containsKey(epm))
			return this;
		mb.unsubscribe(epm, map.get(epm));
		map.remove(c);
		return this;
	}
	
	public MessageBus dispatch(EndPoint src, Message msg)
	{
		mb.dispatch(msg.getClass(), new MessageIncludesSrcEndPoint(src, msg));
		return this;
	}
}
