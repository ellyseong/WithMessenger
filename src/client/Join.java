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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import member.MemberDTO;


public class Join extends JDialog {

	private LoginPanel parent = null;

	private Join self = this;

	private JLabel labelId = new JLabel("Account : ");
	private JTextField fieldId = new JTextField();

	private JButton overlap = new JButton("Check");

	private JLabel labelPw = new JLabel("Password :");
	private JPasswordField fieldPw = new JPasswordField();

	private JLabel labelRePw = new JLabel("Re-Password :");
	private JPasswordField fieldRePw = new JPasswordField();

	private JLabel labelName = new JLabel("Name :");
	private JTextField fieldName = new JTextField();

	private JLabel labelPwQue = new JLabel("Find Question :");
	private JComboBox<String> box = new JComboBox<String>();

	private JLabel labelPwAns = new JLabel("Answer :");
	private JTextField fieldPwAns = new JTextField();

	private JButton buttonOk = new JButton("Sign up");
	private JButton buttonCancel = new JButton("Cancel");

	private ImageIcon backgroundImg;
	private JPanel background;
	private JPanel panelInput = new JPanel(new GridBagLayout());

	private MemberDTO mem = new MemberDTO();
	private ConnManager manager = new ConnManager();
	
	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JTextField getFieldPwAns() {
		return fieldPwAns;
	}

	public void setFieldPwAns(JTextField fieldPwAns) {
		this.fieldPwAns = fieldPwAns;
	}

	public JTextField getFieldId() {
		return fieldId;
	}

	public void setFieldId(JTextField fieldId) {
		this.fieldId = fieldId;
	}

	public JPasswordField getFieldPw() {
		return fieldPw;
	}

	public void setFieldPw(JPasswordField fieldPw) {
		this.fieldPw = fieldPw;
	}
	
	public JPasswordField getFieldRePw() {
		return fieldRePw;
	}

	public void setFieldRePw(JPasswordField fieldRePw) {
		this.fieldRePw = fieldRePw;
	}

	public JComboBox<String> getBox() {
		return box;
	}

	public void setBox(JComboBox<String> box) {
		this.box = box;
	}

	public JLabel getLabelId() {
		return labelId;
	}

	public void setLabelId(JLabel labelId) {
		this.labelId = labelId;
	}

	public JLabel getLabelPw() {
		return labelPw;
	}

	public void setLabelPw(JLabel labelPw) {
		this.labelPw = labelPw;
	}

	public JLabel getLabelRePw() {
		return labelRePw;
	}

	public void setLabelRePw(JLabel labelRePw) {
		this.labelRePw = labelRePw;
	}

	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(JLabel labelName) {
		this.labelName = labelName;
	}

	public JLabel getLabelPwQue() {
		return labelPwQue;
	}

	public void setLabelPwQue(JLabel labelPwQue) {
		this.labelPwQue = labelPwQue;
	}

	public JLabel getLabelPwAns() {
		return labelPwAns;
	}

	public void setLabelPwAns(JLabel labelPwAns) {
		this.labelPwAns = labelPwAns;
	}

	private void eventInit() {
		// 중복확인 버튼
		this.overlap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.isId(self);
			}
		});
		// 취소버튼
		this.buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		// 질문 답 저장.
		this.box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem.setQuestion(box.getSelectedItem().toString());
				System.out.println(mem.getQuestion());
			}
		});
		// 회원가입 버튼
		this.buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.join(self);
			}
		});
	}
	private void compInit() {
		
		setForeground(Color.WHITE);
		
		backgroundImg = new ImageIcon("img/join.png");
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
		this.panelInput.add(labelId,c);
		c.gridx = 2;
		this.fieldId.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldId,c);
		c.gridx = 3;
		this.panelInput.add(overlap,c);
		
		c.gridy = 2; c.gridx = 1;
		this.panelInput.add(labelPw,c);
		c.gridy = 2; c.gridx = 2;
		this.fieldPw.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldPw,c);

		c.gridy = 3; c.gridx = 1;
		this.panelInput.add(labelRePw,c);
		c.gridy = 3; c.gridx = 2;
		this.fieldRePw.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldRePw,c);

		c.gridy = 4; c.gridx = 1;
		this.panelInput.add(labelName,c);
		c.gridy = 4; c.gridx = 2;
		this.fieldName.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldName,c);

		c.gridy = 5; c.gridx = 1;
		this.panelInput.add(labelPwQue,c);
		c.gridy = 5; c.gridx = 2;
		this.box.setPreferredSize(new Dimension(150,30));
		this.box.addItem("초등학교 이름은?");
		this.box.addItem("첫사랑의 이름은?");
		this.box.addItem("가장 기억에 남는 영화 이름은?");
		this.box.addItem("갖고 싶은 드림카는?");
		this.box.addItem("가장 감명깊게 읽은 책은?");
		this.box.addItem("지금 사는 곳은?");
		this.panelInput.add(box,c);

		c.gridy = 6; c.gridx = 1;
		this.panelInput.add(labelPwAns,c);
		c.gridy = 6; c.gridx = 2;
		this.fieldPwAns.setPreferredSize(new Dimension(150,30));
		this.panelInput.add(fieldPwAns,c);

		c.gridy = 7; c.gridx = 2;
		c.insets = new Insets(20,70,0,0);
		this.panelInput.add(buttonOk,c);
		c.gridy = 7; c.gridx = 3;
		c.insets = new Insets(20,0,0,20);
		this.panelInput.add(buttonCancel,c);
		this.panelInput.setOpaque(false);
		
		this.background.add(panelInput);
		this.add(background);
		
		this.overlap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.buttonOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}

	public Join(LoginPanel self2) {
		this.parent = self2;
		this.setUndecorated(true);
		this.setSize(420,570);
		this.setLocationRelativeTo(self2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.compInit();
		this.eventInit();
		this.setModal(true);
	}
}