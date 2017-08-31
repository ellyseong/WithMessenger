package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWithPanel extends JPanel {
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JLabel PartnerLabel = new JLabel("Ur Partner's Account");
	JTextField accountField = new JTextField();
	JButton btnContact = new JButton("contact");
	JButton button = new JButton("cancel"); 
	
	public void compInit() {
		setLayout(null);
		panel.setBounds(0, 0, 600, 1000);
		add(panel);
		panel.setLayout(null);
		panel_1.setBackground(Color.PINK);
		
		panel_1.setBounds(134, 373, 322, 275);
		panel.add(panel_1);
		panel_1.setLayout(null);
		PartnerLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		
		PartnerLabel.setBounds(59, 78, 208, 30);
		panel_1.add(PartnerLabel);
		accountField.setText("email@email.com");
		accountField.setBounds(59, 112, 208, 30);
		accountField.setColumns(10);
		panel_1.add(accountField);
		btnContact.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		btnContact.setBounds(174, 170, 93, 33);
		
		panel_1.add(btnContact);
		button.setFont(new Font("Bradley Hand ITC", Font.BOLD, 18));
		button.setBounds(59, 170, 93, 33);
		
		panel_1.add(button);
		
	}
	public void eventInit() {
		this.accountField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				accountField.setText("");
			}
		});
	}
	public LoginWithPanel() {
		
		this.setSize(600,1000);
		this.compInit();
		this.eventInit();
	}

}
