package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import member.MemberDTO;

public class ConnManager implements Runnable {

	private Socket sock;
	private DataOutputStream dos;
	private DataInputStream dis;
	private MemberDTO mem = new MemberDTO();
	private boolean idCheckOK;

	private void ConnSocket() throws Exception {
		Socket sock = new Socket("localhost", 9497);
		dis = new DataInputStream(sock.getInputStream());
		dos = new DataOutputStream(sock.getOutputStream());
	}

	public boolean login(LoginPanel log) {
		try {
			mem.setAccount(log.getTxtEmailemailcom().getText());
			mem.setPw(String.valueOf(log.getPasswordField().getPassword()));
			if (mem.getAccount().equals("")) {
				JOptionPane.showMessageDialog(log, "Please enter your account.");
				idCheckOK = false;
			} else if (mem.getPw().equals("")) {
				JOptionPane.showMessageDialog(log, "Please enter your password.");
				idCheckOK = false;
			} else {
				ConnSocket();
				dos.writeUTF("login");
				dos.flush();
				dos.writeUTF(mem.getAccount());
				dos.flush();
				dos.writeUTF(mem.getPw());
				dos.flush();
				boolean loginOK = dis.readBoolean();
				if (loginOK) {
					JOptionPane.showMessageDialog(log, "[" + mem.getAccount() + "] Welcome");
					idCheckOK = loginOK;
				} else {
					JOptionPane.showMessageDialog(log, "Login failed! Check your account or Password!");
					log.getTxtEmailemailcom().setText("");
					log.getPasswordField().setText("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCheckOK;
	}

	public void join(Join jo) {
		try {
			mem.setAccount(jo.getFieldId().getText());
			mem.setPw(String.valueOf(jo.getFieldPw().getPassword()));
			mem.setName(jo.getFieldName().getText());
			mem.setAnswer(jo.getFieldPwAns().getText());
			String rePW = String.valueOf(jo.getFieldRePw().getPassword());
			mem.setQuestion(jo.getBox().getSelectedItem().toString());
			mem.setWith("0");// ��Ʈ�ʶ� ���� �������� ���� ��Ȳ.���Ը� �ϴ� ��Ȳ!
			if (mem.getAccount().equals("")) {
				JOptionPane.showMessageDialog(jo, "Please enter your account ID.");
			} else if (mem.getPw().equals("") || rePW.equals("")) {
				JOptionPane.showMessageDialog(jo, "Please enter your password.");
			} else if (!mem.getPw().equals(rePW)) {
				JOptionPane.showMessageDialog(jo, "Please confirm your password and re-password.");
				jo.getFieldPw().setText("");
				jo.getFieldRePw().setText("");
			} else if (mem.getName().equals("")) {
				JOptionPane.showMessageDialog(jo, "Please enter your name.");
			} else if (mem.getQuestion().equals("")) {
				JOptionPane.showMessageDialog(jo, "Please select a password question.");
			} else if (mem.getAnswer().equals("")) {
				JOptionPane.showMessageDialog(jo, "Please enter the answers to the password question.");
			} else if (!idCheckOK) {
				JOptionPane.showMessageDialog(jo, "Please press 'Check'");
			} else {
				ConnSocket();
				dos.writeUTF("join");
				dos.writeUTF(mem.getAccount());
				dos.flush();
				dos.writeUTF(mem.getPw());
				dos.flush();
				dos.writeUTF(mem.getName());
				dos.flush();
				dos.writeUTF(mem.getQuestion());
				dos.flush();
				dos.writeUTF(mem.getAnswer());
				dos.flush();
				dos.writeUTF(mem.getWith()); // ���Ը� �ϴ°ſ��� 0�� ����.
				dos.flush();

				boolean result = dis.readBoolean();
				if (result) {
					JOptionPane.showMessageDialog(jo, "Accession to membership");
					jo.dispose();
				} else {
					JOptionPane.showMessageDialog(jo, "Failed to join membership. Contact your administrator.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isId(Join jo) {
		try {

			if (jo.getFieldId().equals("")) {
				JOptionPane.showMessageDialog(jo, "������ �Է����ּ���.");
			} else {
				ConnSocket();
				dos.writeUTF("isId");
				String id = jo.getFieldId().getText();
				dos.writeUTF(id);

				dos.flush();

				boolean result = dis.readBoolean();
				idCheckOK = result;
				
				System.out.println(idCheckOK);
				if (result) {
					JOptionPane.showMessageDialog(jo, "�̹� ���̵� �����մϴ�.");
					jo.getFieldId().setText("");
				} else {
					JOptionPane.showMessageDialog(jo, "����� �� �ִ� ���̵��Դϴ�.");
					idCheckOK = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void idCheck(IDPWFind self) {

		try {
			mem.setName(self.getFieldIdName().getText());
			mem.setAnswer(self.getFieldPwAnsA().getText());
			mem.setQuestion(self.getBoxA().getSelectedItem().toString());
			if (mem.getName().equals("")) {
				JOptionPane.showMessageDialog(self, "�̸��� �Է��ϼ���.");
			} else if (mem.getQuestion().equals("")) {
				JOptionPane.showMessageDialog(self, "������ ���� �ϼ���.");
			} else {
				ConnSocket();
				// id��� buffered�� ������ ����ϱ�.
				dos.writeUTF("searchAccount");
				dos.flush();
				dos.writeUTF(mem.getName());
				dos.flush();
				dos.writeUTF(mem.getQuestion());
				dos.flush();
				dos.writeUTF(mem.getAnswer());
				dos.flush();
				mem.setAccount(dis.readUTF());
				if (!mem.getAccount().equals("false")) {
					JOptionPane.showMessageDialog(self, "[" + mem.getName() + "] ���� ���̵��" + mem.getAccount() + "�Դϴ�.");
					self.getFieldIdName().setText("");
					self.getFieldPwAnsA().setText("");
				} else {
					JOptionPane.showMessageDialog(self, "���̵� �������� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.");
					self.getFieldIdName().setText("");
					self.getFieldPwAnsA().setText("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pwCheck(IDPWFind self) {
		try {
			mem.setAccount(self.getFieldPwId().getText());
			mem.setName(self.getFieldPwName().getText());
			mem.setAnswer(self.getFieldPwAnsP().getText());
			mem.setQuestion(self.getBoxP().getSelectedItem().toString());
			// ��� ã�� �ɼ� ����ִ� â..
			if (mem.getAccount().equals("")) {
				JOptionPane.showMessageDialog(self, "���̵� �Է��ϼ���.");
			} else if (mem.getName().equals("")) {
				JOptionPane.showInternalMessageDialog(self, "�̸��� �Է��ϼ���.");
			} else if (mem.getAnswer().equals("")) {
				JOptionPane.showMessageDialog(self, "��й�ȣ ���� �Է��ϼ���.");
			} else if (mem.getQuestion().equals("")) {
				JOptionPane.showMessageDialog(self, "��й�ȣ ������ �����ϼ���.");
			} else {
				ConnSocket();
				dos.writeUTF("searchPw");
				dos.flush();
				dos.writeUTF(mem.getAccount());
				dos.writeUTF(mem.getName());
				dos.writeUTF(mem.getQuestion());
				dos.writeUTF(mem.getAnswer());
				dos.flush();
				mem.setPw(dis.readUTF());
				if (!mem.getPw().equals("false")) {
					JOptionPane.showMessageDialog(self, mem.getAccount() + "���� ��й�ȣ��" + mem.getPw() + "�Դϴ�.");
					self.getFieldPwId().setText("");
					self.getFieldPwName().setText("");
					self.getFieldPwAnsP().setText("");
				} else {
					JOptionPane.showMessageDialog(self, "�Է��Ͻ� ������ Ʋ�Ƚ��ϴ�. Ȯ���ϰ� �ٽ� �õ����ּ���!");
					self.getFieldPwId().setText("");
					self.getFieldPwName().setText("");
					self.getFieldPwAnsP().setText("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean JoinWith(LoginWithPanel with) {
		
		boolean Ok = false;
		mem.setWith(with.accountField.getText());
		
		if(mem.getWith().equals("")) {
			JOptionPane.showMessageDialog(with, "���� ������ �Է��ϼ���!");
		}else {
			try {
			ConnSocket();
			dos.writeUTF(mem.getWith());//��� ������ ������.
			dos.flush();
			
			String result = dis.readUTF();
			if(result.equals("pAccountNotE")) {
				JOptionPane.showMessageDialog(with, "���� ������ �����ϴ�. Ȯ�����ּ���!");
				Ok = false;
			}else if(result.equals("Ok")){
				JOptionPane.showMessageDialog(with, "Welcome to enjoy WithMessenger");
				Ok = true;
			}else if(result.equals("Failed")) {
				JOptionPane.showMessageDialog(with, "���� ���� ���� �߻�! �����ڿ��� �������ּ���!");
				Ok = false;
			}else {
				JOptionPane.showMessageDialog(with, "���� �߻�! �����ڿ��� �������ּ���!");
			}
			}catch(Exception e) {}
		}
		return Ok;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
