package client;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ChattingPanel extends JPanel{
	private JTextField textField;
	
	
	public void compInit() {
		
	}
	
	public ChattingPanel() {
		this.setSize(600,1000);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 1000);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlLtHighlight);
		panel_1.setBounds(0, 76, 600, 795);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(573, 0, 27, 795);
		panel_1.add(scrollBar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 600, 795);
		panel_1.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(0, 870, 518, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("send");
		btnSend.setBounds(518, 870, 82, 29);
		panel.add(btnSend);
		this.compInit();
	}
}
