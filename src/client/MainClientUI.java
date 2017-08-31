package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import member.MemberDTO;

public class MainClientUI extends JFrame implements ActionListener {

	private CardLayout card = new CardLayout();
	private LoginPanel login = new LoginPanel();
	private LoginWithPanel with = new LoginWithPanel();
	private WithMainPanel w_main = new WithMainPanel();
	private ChattingPanel chat = new ChattingPanel();
	private DiaryPanel diary = new DiaryPanel();
	private SettingPanel set = new SettingPanel();
	private ConnManager manager = new ConnManager();
	private MemberDTO mem = new MemberDTO();
	private void addContentPane() {

		this.getContentPane().setLayout(card);

		this.getContentPane().add("Login", login);
		this.getContentPane().add("with", with);
		this.getContentPane().add("w_main", w_main);
		this.getContentPane().add("Chat", chat);
		this.getContentPane().add("Diary", diary);
		this.getContentPane().add("Setting", set);

	}

	private void eventInit() {

		this.login.btnLogin.addActionListener(this);
		this.with.btnContact.addActionListener(this);
		this.w_main.diaryBtn.addActionListener(this);
		this.w_main.chatBtn.addActionListener(this);
		this.w_main.setBtn.addActionListener(this);
		this.set.btnBack.addActionListener(this);
	}

	private void compInit() {

	}

	public MainClientUI() {
		this.setTitle("Couple Messenger");
		this.setUndecorated(true);
		this.setSize(600, 1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.addContentPane();
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		new MainClientUI();
		/*
		 * SwingUtilities.invokeLater(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub // ����ó�� try
		 * { UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel"); }
		 * catch (Exception ex) { ex.printStackTrace(); } new MainClientUI(); } });
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login.btnLogin) {
			/*boolean loginOk = manager.login(login);
			if(loginOk) {
				if(!mem.getWith().equals("0")) {
					card.show(getContentPane(), "w_main");
				}*/
				card.show(getContentPane(), "with");
			/*}else {}*/ //Ȥ�� �˸� �޽����� ���� �ʴ´ٸ� �˸� �ֱ�!
		} else if (e.getSource() == with.button) {
			card.show(getContentPane(), "Login");
		} // ��Ʈ�� ���� ��� ��ҹ�ư ��ɺο��� ���߿� �����ϱ�.
		else if (e.getSource() == with.btnContact) {
			card.show(getContentPane(), "w_main");
			// ���� ���� �Ǹ� ����ֱ�!!!!!
			/*boolean result = manager.JoinWith(with);// ���� ó�����ֱ�.
			if (result) {*/
				card.show(getContentPane(), "w_main");
			//}
		} else if (e.getSource() == set.btnBack) {
			card.show(getContentPane(), "w_main");
		} else if (e.getSource() == w_main.diaryBtn) {
			card.show(getContentPane(), "Diary");
		} else if (e.getSource() == w_main.chatBtn) {
			card.show(getContentPane(), "Chat");
		} else if (e.getSource() == w_main.setBtn) {
			card.show(getContentPane(), "Setting");
		}
	}
}
