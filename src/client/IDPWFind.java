package client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import member.MemberDTO;

public class IDPWFind extends JDialog {

	private LoginPanel parent = null;

	private IDPWFind self = this;

	private JLabel idFind = new JLabel("Find Account");

	private JLabel labelIdName = new JLabel("Name : ");
	private JTextField fieldIdName = new JTextField();
	
	private JLabel labelPwQueA = new JLabel("Question : ");
	private JComboBox<String> boxA = new JComboBox<String>();

	private JLabel labelPwAnsA = new JLabel("Answer : ");
	private JTextField fieldPwAnsA = new JTextField();

	private JButton idCheck = new JButton("check");

	private JLabel pwFind = new JLabel("Find Password");

	private JLabel labelPwId = new JLabel("Account : ");
	private JTextField fieldPwId= new JTextField();
	
	private JLabel labelPwName = new JLabel("Name : ");
	private JTextField fieldPwName = new JTextField();

	private JLabel labelPwQueP = new JLabel("Question : ");
	private JComboBox<String> boxP = new JComboBox<String>();

	private JLabel labelPwAnsP = new JLabel("Answer : ");
	private JTextField fieldPwAnsP = new JTextField();

	private JButton pwCheck = new JButton("Check");
	private JButton buttonCancel = new JButton("Cancel");
	
	private ImageIcon backgroundImg;
	private JPanel background;
	private JPanel panelInput = new JPanel(new GridBagLayout());

	private ConnManager manager = new ConnManager();
	private MemberDTO mem = new MemberDTO();

	public JTextField getFieldIdName() {
		return fieldIdName;
	}

	public void setFieldIdName(JTextField fieldIdName) {
		this.fieldIdName = fieldIdName;
	}
	public JTextField getFieldPwId() {
		return fieldPwId;
	}
	public void setFieldPwId(JTextField fieldPwId) {
		this.fieldPwId = fieldPwId;
	}
	public JTextField getFieldPwName() {
		return fieldPwName;
	}
	public void setFieldPwName(JTextField fieldPwName) {
		this.fieldPwName = fieldPwName;
	}

	
	public JComboBox<String> getBoxA() {
		return boxA;
	}

	public void setBoxA(JComboBox<String> boxA) {
		this.boxA = boxA;
	}
	public JComboBox<String> getBoxP() {
		return boxP;
	}

	public void setBoxP(JComboBox<String> boxP) {
		this.boxP = boxP;
	}

	public JTextField getFieldPwAnsA() {
		return fieldPwAnsA;
	}

	public void setFieldPwAnsA(JTextField fieldPwAnsA) {
		this.fieldPwAnsA = fieldPwAnsA;
	}

	public JTextField getFieldPwAnsP() {
		return fieldPwAnsP;
	}

	public void setFieldPwAnsP(JTextField fieldPwAnsP) {
		this.fieldPwAnsP = fieldPwAnsP;
	}

	private void eventInit() {
		this.idCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.idCheck(self);
			}
		});
		
		this.boxA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem.setQuestion(boxA.getSelectedItem().toString());
			}
		});
		this.boxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem.setQuestion(boxP.getSelectedItem().toString());
			}
		});
		this.pwCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.pwCheck(self);
			}
		});
		
		this.buttonCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	}

	private void compInit() {
		
		setForeground(Color.WHITE);
		backgroundImg = new ImageIcon("img/find.png");
		background = new JPanel() {
            public void paintComponent(Graphics g) {
                 g.drawImage(backgroundImg.getImage(), 0, 0, null);
                 setOpaque(false);//그림을 표시하게 설정,투명하게 조절
                 super.paintComponent(g);
            }
        };
        
        background.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,5,10,5);

		c.gridx = 1;
		this.panelInput.add(idFind,c);

		c.gridy = 2; c.gridx = 1;
		this.panelInput.add(labelIdName,c);
		c.gridy = 2; c.gridx = 2;
		this.fieldIdName.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldIdName,c);
		
		c.gridy = 3; c.gridx = 1;
		this.panelInput.add(labelPwAnsA, c);
		c.gridy = 3; c.gridx = 2;
		this.boxA.setPreferredSize(new Dimension(150,30));
		this.boxA.addItem("초등학교 이름은?");
		this.boxA.addItem("첫사랑의 이름은?");
		this.boxA.addItem("가장 기억에 남는 영화 이름은?");
		this.boxA.addItem("갖고 싶은 드림카는?");
		this.boxA.addItem("가장 감명깊게 읽은 책은?");
		this.boxA.addItem("지금 사는 곳은?");
		this.panelInput.add(boxA, c);
		
		c.gridy = 4; c.gridx = 1;
		this.panelInput.add(labelPwAnsA,c);
		c.gridy = 4; c.gridx = 2;
		this.fieldPwAnsA.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldPwAnsA,c);

		c.gridy = 5; c.gridx = 3;
		this.panelInput.add(idCheck,c);

		c.gridy = 6; c.gridx = 1;
		this.panelInput.add(pwFind,c);

		c.gridy = 7; c.gridx = 1;
		this.panelInput.add(labelPwId,c);
		c.gridy = 7; c.gridx = 2;
		this.fieldPwId.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldPwId,c);

		c.gridy = 8; c.gridx = 1;
		this.panelInput.add(labelPwName,c);
		c.gridy = 8; c.gridx = 2;
		this.fieldPwName.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldPwName,c);

		c.gridy = 9; c.gridx = 1;
		this.panelInput.add(labelPwQueP,c);
		c.gridy = 9; c.gridx = 2;
		this.boxP.setPreferredSize(new Dimension(150,30));
		this.boxP.addItem("초등학교 이름은?");
		this.boxP.addItem("첫사랑의 이름은?");
		this.boxP.addItem("가장 기억에 남는 영화 이름은?");
		this.boxP.addItem("갖고 싶은 드림카는?");
		this.boxP.addItem("가장 감명깊게 읽은 책은?");
		this.boxP.addItem("지금 사는 곳은?");
		this.panelInput.add(boxP,c);

		c.gridy = 10; c.gridx = 1;
		this.panelInput.add(labelPwAnsP,c);
		c.gridy = 10; c.gridx = 2;
		this.fieldPwAnsP.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldPwAnsP,c);
		
		c.gridy = 11; c.gridx = 2;
		this.panelInput.add(pwCheck,c);
		c.gridy = 11; c.gridx = 3;
		this.panelInput.add(buttonCancel,c);
		this.panelInput.setOpaque(false);
		
		this.background.add(panelInput);
		this.add(background);
		
		this.idCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.pwCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public IDPWFind(LoginPanel parent) {
		this.parent = parent;
		this.setTitle("계정/비밀번호 찾기");
		this.setUndecorated(true);
		this.setSize(400, 600);
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setModal(true);
	}
}