package rmit.connectN.util.messagebus;

import rmit.connectN.util.common.MapSet;


public class MessageBus {
	MapSet<Object, MessageHandler> ms = new MapSet<Object, MessageHandler>();
	
	public void subscribe(Object key, MessageHandler mh)
	{
		ms.put(key, mh);
	}
	
	public void unsubscribe(Object key, MessageHandler mh)
	{
		ms.remove(key, mh);
	}
	
	public void dispatch(Object key, Object msg)
	{
		Object[] hs = ms.get(key);
		if (hs == null)
			return;
		
		for(Object h:hs)
		{
			((MessageHandler)h).process(msg);
		}
	}
}
