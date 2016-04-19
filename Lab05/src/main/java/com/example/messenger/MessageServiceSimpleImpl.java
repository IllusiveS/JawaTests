package com.example.messenger;

import java.util.Random;

public class MessageServiceSimpleImpl implements MessageService {

	public ConnectionStatus checkConnection(String server) {
		if (!server.endsWith(".pl")) {
			return ConnectionStatus.FAILURE;
		}
		return ConnectionStatus.SUCCESS;
	}

	public SendingStatus send(String server, String message)
			throws MalformedRecipientException {

		if (message == null || message.length() < 3)
			throw new MalformedRecipientException();
		
		if (server == null || server.length() < 4)
			throw new MalformedRecipientException();
		
		if (checkConnection(server) == ConnectionStatus.FAILURE) {
			return SendingStatus.SENDING_ERROR;
		}
		return SendingStatus.SENT;
	}

}
