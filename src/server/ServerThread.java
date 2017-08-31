package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import member.MemberDAO;
import member.MemberDTO;

public class ServerThread extends Thread {

	private Socket sock = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private MemberDAO dao = new MemberDAO();
	private MemberDTO dto = new MemberDTO();
	private String id, pw;
	public ServerThread(Socket sock) {
		this.sock = sock;
		try {
			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			System.out.println("���� ���� ����");
		}
	}

	public void run() {
		System.out.println(sock.getInetAddress() + "���� ����Ǿ����ϴ�.");
		try {
			// �����ֱ�.
			String section = dis.readUTF();
			System.out.println(section);
			switch (section) {
			case "join": {
				dto.setAccount(dis.readUTF());
				dto.setPw(dis.readUTF());
				dto.setName(dis.readUTF());
				dto.setQuestion(dis.readUTF());
				dto.setAnswer(dis.readUTF());
				dto.setWith(dis.readUTF());// ó�� ȸ������ ���� 0�� ��.
				// DB�� �־��ֱ�.
				int result = dao.insertNewMember(dto);
				if (result > 0) {// ����
					dos.writeBoolean(true);
				} else {// ����
					dos.writeBoolean(false);
				}
			}
			case "isId": {// �ߺ�Ȯ�� üũ
				String checkId = dis.readUTF();
				boolean result = dao.isIdExist(checkId);
				// ������ ���⼭ �����ʰ� boolean�� cilent���� ���� ���ֱ�!
				dos.writeBoolean(result);
			}
			case "searchAccount": {//����ã�� 
				dto.setName(dis.readUTF());
				dto.setQuestion(dis.readUTF());
				dto.setAnswer(dis.readUTF());
				//��� ������ �ٽ� Account�� ����
				dto.setAccount(dao.searchAccount(dto));

				if (dto.getAccount().equals("false")) {
					dos.writeUTF("false");
				} else {
					dos.writeUTF(dto.getAccount());
				}
			}
			case "searchPw": {//���ã��
				dto.setAccount(dis.readUTF());
				dto.setName(dis.readUTF());
				dto.setQuestion(dis.readUTF());
				dto.setAnswer(dis.readUTF());
				//��������� �ٽ� PW�� ����
				dto.setPw(dao.searchPW(dto));
				
				if (dto.getPw().equals("false")) {
					dos.writeUTF("false");
				} else {//���Ŀ� ��� ������ �����ִ� �� �ʿ�.
					dos.writeUTF(dto.getPw());
				}
			}
			case "JoinWith": { // �����̶� ���� ����.
				dto.setAccount(id);
				dto.setPw(pw);
				dto.setWith(dis.readUTF());
				
				String result = dao.JoinWith(dto);
				dos.writeUTF(result);
			}
			case "login": {
				id = dis.readUTF();
				pw = dis.readUTF(); 
				boolean loginOk = dao.login(id, pw);
				
				dos.writeBoolean(loginOk);
				dos.flush();
				
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}