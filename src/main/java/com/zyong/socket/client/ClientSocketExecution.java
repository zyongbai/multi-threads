package com.zyong.socket.client;

import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;

public class ClientSocketExecution {
	private static Socket socket;
	
	static {
		try {
			socket = new Socket("127.0.0.1", 80);
		} catch (UnknownHostException e) {
			System.out.println("创建socket异常:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("创建socket异常:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sendMessage() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			for (int i = 0; i < 10; i++) {
				String message = "socket data, hello " + i + "\n";
				bw.write(message);
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("socket传输数据异常:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
