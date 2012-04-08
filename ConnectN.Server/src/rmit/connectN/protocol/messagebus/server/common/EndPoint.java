package rmit.connectN.protocol.messagebus.server.common;

import rmit.connectN.net.common.MessageChannelWritable;
import rmit.connectN.protocol.common.Message;

public class EndPoint implements
		rmit.connectN.protocol.util.messagebus.EndPoint {
	MessageChannelWritable mc;
	
	public EndPoint(MessageChannelWritable mc)
	{
		this.mc = mc;
	}
	
	public EndPoint write(Message m) throws Throwable
	{
		mc.write(m);
		return this;
	}
	
	public boolean equals(Object obj) {
        if (obj == null)
        	return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;

        EndPoint foo = (EndPoint)obj;
        return foo.mc == mc;
    }
	
    public int hashCode() {
        return mc.hashCode();
    }	
}
