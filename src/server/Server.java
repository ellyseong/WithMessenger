package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(9497);
			
			while(true) {
				Socket sock = server.accept();
				System.out.println(sock.getInetAddress()+"로 부터 접속 ");
				ServerThread st = new ServerThread(sock);
				st.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
