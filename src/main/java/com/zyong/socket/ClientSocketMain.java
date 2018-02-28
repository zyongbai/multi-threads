package com.zyong.socket;

import com.zyong.socket.client.ClientSocketExecution;

public class ClientSocketMain {
	public static void main(String[] args) {
		ClientSocketExecution clientSocketExecution = new ClientSocketExecution();
		clientSocketExecution.sendMessage();
	}
}
