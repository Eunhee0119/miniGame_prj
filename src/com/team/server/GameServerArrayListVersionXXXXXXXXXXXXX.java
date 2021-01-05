package com.team.server;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.entity.EventMsgListener;

public class GameServerArrayListVersionXXXXXXXXXXXXX {

	private ServerSocket svrSock;
	
	
	private boolean running = false;
	private final int MAX_PLAYERS;

	private List<Socket> sockets = new ArrayList<Socket>();

	public GameServerArrayListVersionXXXXXXXXXXXXX() {
		this.MAX_PLAYERS = 0;
		System.out.println("�÷��̾� ���� �Է����ּ���.");
	}

	public GameServerArrayListVersionXXXXXXXXXXXXX(int MAX_PLAYERS, int Port) throws IOException {
		this.MAX_PLAYERS = MAX_PLAYERS;

		svrSock = new ServerSocket(Port);
		running = true;
		System.out.println("Server Started...");

		// ó������ �����尡 �־�� �÷��̾� �޴� while���� ������ �ʴ´�.

		new Thread(() -> {
			try {
				while (running) {
					// if (socketSize == GameServer.this.MAX_PLAYERS)
					// break;
					Socket sock = svrSock.accept();
					sockets.add(sock);
					int playerNum = sockets.size() - 1;
					System.out.println("Ŭ���̾�Ʈ ����: " + sock.getRemoteSocketAddress());

					new Thread(() -> {
						try {
							PrintStream nout = new PrintStream(sock.getOutputStream(), true, "UTF-8");
							Scanner nscan = new Scanner(sock.getInputStream(), "UTF-8");
							try {
								nout.println("youerNum:" + playerNum);
								String msg;
								do {
									msg = nscan.nextLine();

									for (int i = 0; i < sockets.size(); i++)
										new PrintStream(sockets.get(i).getOutputStream(), true, "UTF-8").println(msg);

								} while (!msg.equals("#DISCONNET"));
							} catch (Exception e) {
								System.out.println("���� ����: " + sock.getRemoteSocketAddress());
							} finally {
								nscan.close();
								nscan.close();
								nout.close();
								sock.close();

								for (Socket socket : sockets) {
									if (socket.equals(sock)) {
										System.out.println("���� ����: " + sock.getRemoteSocketAddress());
										sockets.remove(socket);
										break;
									}
								}
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}).start();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}).start();

	}

//	public void send(String msg) throws UnsupportedEncodingException, IOException {
//		for (int i = 0; i < sockets.size(); i++) {
//			new PrintStream(sockets.get(i).getOutputStream(), true, "UTF-8").println(msg);
//		}
//	}
//
//	public void send(int playerNum, String msg) throws UnsupportedEncodingException, IOException {
//
//		for (int i = 0; i < sockets.size(); i++) {
////			System.out.println(msg);
////			if (i == playerNum)
////				continue;
//
//			send(msg);
//		}
//	}

//	public Socket[] getSockets() {
//		return sockets;
//	}

//	public boolean isRunning() {
//		return running;
//	}
//
//	public ServerSocket getSvrSock() {
//		return svrSock;
//	}

}
