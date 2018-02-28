package com.zyong.socket.server;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServerSocketExecution {
	private final static int NTHREADS = 2;
	private final static Executor executor = Executors.newFixedThreadPool(NTHREADS);
	private static ServerSocket serverSocket;
	
	static {
		try {
			serverSocket = new ServerSocket(80);
		} catch (IOException e) {
			System.out.println("����ServerSocket�쳣:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void start() {
		while (true) {
			try {
				final Socket socket = serverSocket.accept();
				executor.execute(new Runnable() {
					@Override
					public void run() {
						handRequest(socket);
					}
				});
			} catch (IOException e) {
				System.out.println("��ȡSocket�쳣:" + e.getMessage());
				e.printStackTrace();
				continue;
			}
		}
	}
	
	public void handRequest(Socket socket) {
		try {
			InputStream inputStream = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String readStr = "";
			while ((readStr = br.readLine()) != null) {
				System.out.println("socket data:" + readStr);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("��ȡsocket�����쳣:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
