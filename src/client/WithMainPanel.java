package client;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuBar;
import java.awt.Panel;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WithMainPanel extends JPanel {
	JPanel panel_1 = new JPanel();
	JLabel lblStart = new JLabel("Start ");
	JLabel label = new JLabel("0 ");
	
	Button diaryBtn = new Button("일정");
	Button chatBtn = new Button("채팅");
	Button setBtn = new Button("설정");
	
	
	public void compInit() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 1000);
		add(panel);
		panel.setLayout(null);
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(0, 63, 600, 178);
		
		panel.add(panel_1);
		panel_1.setLayout(null);
		lblStart.setFont(new Font("Bradley Hand ITC", Font.BOLD, 67));
		lblStart.setBounds(198, 12, 193, 62);
		
		panel_1.add(lblStart);
		label.setFont(new Font("Bradley Hand ITC", Font.BOLD, 67));
		label.setBounds(198, 88, 193, 62);
		
		panel_1.add(label);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(0, 841, 600, 126);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		diaryBtn.setBounds(0, 0, 200, 126);
		panel_2.add(diaryBtn);
		chatBtn.setBounds(200, 0, 200, 126);
		panel_2.add(chatBtn);
		setBtn.setBounds(400, 0, 200, 126);
		panel_2.add(setBtn);
		
	}

	public void eventInit() {

	}

	public WithMainPanel() {
		this.setSize(600, 1000);

		this.compInit();
		this.eventInit();
	}
}
