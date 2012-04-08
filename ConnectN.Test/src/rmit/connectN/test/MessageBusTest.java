package rmit.connectN.test;

import static org.junit.Assert.*;

import org.junit.Test;

import rmit.connectN.util.messagebus.MessageBus;
import rmit.connectN.util.messagebus.MessageHandler;

public class MessageBusTest {

	@Test
	public final void testSubscribe_ShouldReceiveMessage() {
		MessageBus m = new MessageBus();
		final String myMsg = "MyMsg";
		m.subscribe("abc", new MessageHandler() {
			@Override
			public void process(Object msg) {
				assertSame((String)msg, myMsg);
			}
		});
		m.subscribe("abc1", new MessageHandler() {
			@Override
			public void process(Object msg) {
				assertSame((String)msg, myMsg);
			}
		});
		m.dispatch("abc", myMsg);
		m.dispatch("abc1", myMsg);
	}

	@Test
	public final void testSubscribeTwice_ShouldReceiveMessageTwice() {
		MessageBus m = new MessageBus();
		class MyMsg
		{
			String myMsg = "MyMsg";
			int i = 0;
		}
		final MyMsg myMsg = new MyMsg();
		m.subscribe("abc", new MessageHandler() {
			@Override
			public void process(Object obj) {
				MyMsg msg = (MyMsg)obj;
				assertSame(msg, myMsg);
				msg.i++;
			}
		});
		m.subscribe("abc", new MessageHandler() {
			@Override
			public void process(Object obj) {
				MyMsg msg = (MyMsg)obj;
				assertSame(msg, myMsg);
				msg.i++;
				assertEquals(msg.i, 2);
			}
		});
		m.dispatch("abc", myMsg);
	}
	
	@Test
	public final void testSubscribeAndUnsubscribe_ShouldNotReceiveMessage() {
		final MessageBus m = new MessageBus();
		class MyMsg
		{
			String myMsg = "MyMsg";
			int i = 0;
		}
		final MyMsg myMsg = new MyMsg();
		MessageHandler mg = new MessageHandler() {
			@Override
			public void process(Object obj) {
				MyMsg msg = (MyMsg)obj;
				assertSame(msg, myMsg);
				msg.i++;
				m.unsubscribe("abc", this);
			}
		};
		m.subscribe("abc", mg);
		m.dispatch("abc", myMsg);
		m.dispatch("abc", myMsg);
		assertEquals(myMsg.i, 1);
	}	
}
