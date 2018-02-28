package com.zyong.socket;

import com.zyong.socket.server.ServerSocketExecution;

public class ServerSocketMain {
	public static void main(String[] args) {
		ServerSocketExecution serverSocketExecution = new ServerSocketExecution();
		serverSocketExecution.start();
	}
}
