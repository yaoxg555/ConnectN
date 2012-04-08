package rmit.connectN.protocol.util.messagebus;

public interface MessageHandler {
	void process(EndPoint src, EndPoint dst, Message msg);
}
