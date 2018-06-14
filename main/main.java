package java_bnb;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class main extends JFrame{
	private Container c= getContentPane();
	
	class Btnevent extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			JButton btn = (JButton) e.getSource();
			btn.setBackground(new Color(76,176,249));
		}
		public void mouseExited(MouseEvent e) {
			JButton btn = (JButton) e.getSource();
			btn.setBackground(new Color(27, 154, 247));
		}
	}
	class exitclick extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			System.exit(1);			
		}
	}
	public main() {
		setTitle("test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		//logo
			ImageIcon logo= new ImageIcon("download.jpg");
			JLabel imageLabel= new JLabel(logo);
			c.add(imageLabel, BorderLayout.NORTH);
		//buttons
			String text[]= {"Start", "How to play", "Exit"};//button text list
			Dimension size= new Dimension(330, 60);//button size
			JPanel ButtonPanel= new JPanel();//panel
			ButtonPanel.setBorder(BorderFactory.createEmptyBorder(150 , 434 , 0 , 434));//button center
			for(int i=0;i<text.length;i++) { 
				JButton btn= new JButton(text[i]);//make button add text				
				//setting
				if(i==0){//add event
//					start();
				}else if(i==1){
//					how();					
				}else{					
					btn.addMouseListener(new exitclick());//add event
				}
				
				btn.setPreferredSize(size);//set size
				btn.setBackground(new Color(27, 154, 247));//background color
				btn.setFont(new Font("Verdana", Font.BOLD, 25));//font
				btn.setForeground(Color.WHITE);//font color
				btn.setHorizontalAlignment(SwingConstants.CENTER);//text-align-center
				btn.setBorderPainted(false);//border remove
				btn.addMouseListener(new Btnevent());//add event
				//add in panel
				ButtonPanel.add(btn);//button add in panel
				ButtonPanel.add(Box.createRigidArea(new Dimension(15,15)));// gap between button
			}
			c.add(ButtonPanel, BorderLayout.CENTER);//panel add in container
		c.setBackground(Color.WHITE);//container background
		ButtonPanel.setBackground(Color.WHITE);//panel background
		setSize(1100, 800);//tab size
		setVisible(true);
	}
	public static void main(String[] args) {
		 new main();
	}
}
