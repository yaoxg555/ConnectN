package rmit.connectN.protocol.message.listing;

import java.util.List;

import rmit.connectN.protocol.common.Message;

public class ListResult implements Message {
	public List<ListGameViewModel> gameList;
}
