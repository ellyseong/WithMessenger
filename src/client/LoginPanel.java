package client;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import java.awt.Font;

public class LoginPanel extends JPanel {
	
	LoginPanel self = this;
	JPanel panel = new JPanel();
	JLabel lblAccount = new JLabel("Account :");
	JLabel lblPassword = new JLabel("Password :");
	JLabel lblNeedAccount = new JLabel("Need Account?");
	JLabel lblForgetAccountpassword = new JLabel("Forget Account/Password?");
	JButton btnLogin = new JButton("Login");
	ImageIcon backgroundImg = new ImageIcon();//이미지 찾아 넣기!
	JPanel back = new JPanel();
	private JTextField txtEmailemailcom =new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	JLabel label = new JLabel("Use next time");
	
	public JTextField getTxtEmailemailcom() {
		return txtEmailemailcom;
	}
	public void setTxtEmailemailcom(JTextField txtEmailemailcom) {
		this.txtEmailemailcom = txtEmailemailcom;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
	public void compInint() {
		
		back = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImg.getImage(), 0, 0, null);
				setOpaque(false);//그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 600, 1000);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(137, 373, 333, 301);
		panel.add(panel_1);
		panel_1.setLayout(null);
		lblAccount.setFont(new Font("Bradley Hand ITC", Font.BOLD, 17));
		
		lblAccount.setBounds(59, 58, 87, 28);
		panel_1.add(lblAccount);
		lblPassword.setFont(new Font("Bradley Hand ITC", Font.BOLD, 17));
		
		lblPassword.setBounds(59, 98, 87, 31);
		panel_1.add(lblPassword);
		lblNeedAccount.setFont(new Font("Bradley Hand ITC", Font.BOLD, 17));
		
		lblNeedAccount.setBounds(59, 184, 124, 25);
		panel_1.add(lblNeedAccount);
		lblForgetAccountpassword.setFont(new Font("Bradley Hand ITC", Font.BOLD, 17));
		
		lblForgetAccountpassword.setBounds(59, 208, 217, 28);
		panel_1.add(lblForgetAccountpassword);
		
		txtEmailemailcom.setText("email@email.com");
		txtEmailemailcom.setBounds(148, 58, 128, 31);
		panel_1.add(txtEmailemailcom);
		txtEmailemailcom.setColumns(10);
		btnLogin.setFont(new Font("Bradley Hand ITC", Font.BOLD, 19));
		
		btnLogin.setBounds(191, 141, 87, 31);
		panel_1.add(btnLogin);
		
		passwordField.setBounds(148, 98, 128, 31);
		panel_1.add(passwordField);
		
		this.btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.lblForgetAccountpassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.lblNeedAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setFont(new Font("Bradley Hand ITC", Font.BOLD, 17));
		label.setBounds(59, 235, 217, 28);
		this.label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(label);
	}

	public void eventInit() {
		this.lblNeedAccount.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Join(self).setVisible(true);
			}
		});
		
		this.lblForgetAccountpassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new IDPWFind(self).setVisible(true);
			}
		});
		
		this.txtEmailemailcom.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				self.txtEmailemailcom.setText("");
			}
		});
		
		this.label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(self, "정말로 끝내시겠습니까?");
				if(result== 0) {
					System.exit(0);
				}
			}
		});
	}
	public LoginPanel() {
		this.setSize(600,1000);
		this.compInint();
		this.eventInit();
	}
}
