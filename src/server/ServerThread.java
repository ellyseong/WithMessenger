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
			System.out.println("서버 접속 끊김");
		}
	}

	public void run() {
		System.out.println(sock.getInetAddress() + "님이 연결되었습니다.");
		try {
			// 보내주기.
			String section = dis.readUTF();
			System.out.println(section);
			switch (section) {
			case "join": {
				dto.setAccount(dis.readUTF());
				dto.setPw(dis.readUTF());
				dto.setName(dis.readUTF());
				dto.setQuestion(dis.readUTF());
				dto.setAnswer(dis.readUTF());
				dto.setWith(dis.readUTF());// 처음 회원가입 때는 0값 들어감.
				// DB에 넣어주기.
				int result = dao.insertNewMember(dto);
				if (result > 0) {// 성공
					dos.writeBoolean(true);
				} else {// 실패
					dos.writeBoolean(false);
				}
			}
			case "isId": {// 중복확인 체크
				String checkId = dis.readUTF();
				boolean result = dao.isIdExist(checkId);
				// 구분은 여기서 하지않고 boolean값 cilent에서 구분 해주기!
				dos.writeBoolean(result);
			}
			case "searchAccount": {//계정찾기 
				dto.setName(dis.readUTF());
				dto.setQuestion(dis.readUTF());
				dto.setAnswer(dis.readUTF());
				//결과 나오면 다시 Account에 셋팅
				dto.setAccount(dao.searchAccount(dto));

				if (dto.getAccount().equals("false")) {
					dos.writeUTF("false");
				} else {
					dos.writeUTF(dto.getAccount());
				}
			}
			case "searchPw": {//비번찾기
				dto.setAccount(dis.readUTF());
				dto.setName(dis.readUTF());
				dto.setQuestion(dis.readUTF());
				dto.setAnswer(dis.readUTF());
				//결과나오면 다시 PW에 셋팅
				dto.setPw(dao.searchPW(dto));
				
				if (dto.getPw().equals("false")) {
					dos.writeUTF("false");
				} else {//추후에 비번 가려서 보여주는 것 필요.
					dos.writeUTF(dto.getPw());
				}
			}
			case "JoinWith": { // 상대방이랑 계정 연동.
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