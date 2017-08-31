package client;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Font;

public class SettingPanel extends JPanel {

	JPanel panel = new JPanel();
	JMenu mnBasic = new JMenu("basic");
	JMenuItem mntmSound = new JMenuItem("sound");
	JMenuItem mntmConcept = new JMenuItem("concept");
	JMenuItem mntmAccountSetting = new JMenuItem("account setting");
	JMenuItem mntmApps = new JMenuItem("apps");
	JMenu mnAnother = new JMenu("another");
	JMenu mnInformation = new JMenu("information");
	JMenuItem mntmHowToUse = new JMenuItem("how to use app");
	JMenuItem mntmInformationApp = new JMenuItem("information app");
	JMenuItem mntmContactUs = new JMenuItem("contact us");
	JButton btnBack = new JButton("Back");
	
	public void compInit() {
		setLayout(null);

		panel.setBounds(0, 0, 600, 1000);
		add(panel);
		panel.setLayout(null);
		mnBasic.setBounds(14, 150, 127, 24);
		panel.add(mnBasic);
		mnBasic.add(mntmSound);
		mnBasic.add(mntmConcept);
		mnBasic.add(mntmAccountSetting);
		mnAnother.setBounds(14, 214, 127, 24);
		panel.add(mnAnother);
		mnAnother.add(mntmApps);
		mnInformation.setBounds(14, 267, 127, 24);
		panel.add(mnInformation);
		mnInformation.add(mntmHowToUse);
		mnInformation.add(mntmInformationApp);
		mnInformation.add(mntmContactUs);
		btnBack.setFont(new Font("Bradley Hand ITC", Font.BOLD, 25));
		btnBack.setBounds(36, 82, 115, 35);
		
		panel.add(btnBack);
		
		JMenu mnAbout = new JMenu("about");
		mnAbout.setBounds(14, 342, 127, 24);
		panel.add(mnAbout);
		
		JMenuItem mntmInfomation = new JMenuItem("infomation");
		mnAbout.add(mntmInfomation);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnAbout.add(mntmNewMenuItem);
	}

	public SettingPanel() {
		this.setSize(600, 1000);

		this.compInit();

	}
}
