package lang;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class menu extends JFrame{
	public menu() {
		setTitle("�޴�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		ImageIcon backmain = new ImageIcon("a\\background.png");
		JLabel imagela = new JLabel(backmain);
		JButton start_btn = new JButton("���� ����");
		JButton exit_btn = new JButton("���� ������");
		
		imagela.setSize(600,600);
		
		start_btn.setSize(140, 50);
		start_btn.setLocation(220, 350);
		start_btn.addMouseListener(new MyMouseListner());
		
		exit_btn.setSize(140, 50);
		exit_btn.setLocation(220, 450);
		exit_btn.addMouseListener(new MyMouseListner());
		
		c.add(exit_btn);
		c.add(start_btn);
		c.add(imagela);
		
		setSize(600, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	
	public class MyMouseListner extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
			JButton start_btn = (JButton)e.getSource();
			if(start_btn.getText().equals("���� ����"))
				new OmokEx("����");
			
			JButton exit_btn = (JButton)e.getSource();
			if(exit_btn.getText().equals("���� ������"))
				System.exit(0);
			
		}
	}

	public static void main(String[] args) {
		 new menu();
	}

}
