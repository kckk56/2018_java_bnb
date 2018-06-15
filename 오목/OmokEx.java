package lang;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class OmokEx extends JFrame {  
  private OmokBoard board=new OmokBoard(20,30);      // ������ ��ü
  public OmokEx(String title){                        // ������
  add(board);
  	this.setTitle(title);
    this.setBounds(200,20,650,700);
    this.setVisible(true);
    setLocationRelativeTo(null);
 
  }
  public void ch(String s){
   JOptionPane.showMessageDialog(this,s,"SYSTEM",JOptionPane.INFORMATION_MESSAGE);
  }
  
  class OmokBoard extends Canvas{               // �������� �����ϴ� Ŭ����
  public static final int BLACK=1, WHITE=-1;     // ��� ���� ��Ÿ���� ���
  private int[][] map;                            // ������ �迭
  private int size;               // size�� ������ ���� �Ǵ� ���� ����, 15�� ���Ѵ�.
  private int cell;                           // ������ ũ��(pixel)
  private int color=BLACK;                 // ������� �� ����
  // true�̸� ����ڰ� ���� ���� �� �ִ� ���¸� �ǹ��ϰ�,
  // false�̸� ����ڰ� ���� ���� �� ���� ���¸� �ǹ���
  private Graphics gboard, gbuff;    // ĵ������ ���۸� ���� �׷��Ƚ� ��ü
  private Image buff;                 // ���� ���۸��� ���� ����
  int turn=1;
  OmokBoard(int s, int c){           // �������� ������(s=15, c=30)   
    this.size=s; this.cell=c;
    map=new int[size+2][];            // ���� ũ�⸦ ���Ѵ�.
    for(int i=0;i<map.length;i++)
      map[i]=new int[size+2];
    setBackground(new Color(242,162,33));         // �������� ������ ���Ѵ�.
    setSize(size*(cell+1)+size, size*(cell+1)+size);    // �������� ũ�⸦ ����Ѵ�.
    addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent me){     // ���콺�� ������       

        // ���콺�� ��ǥ�� map ��ǥ�� ����Ѵ�.
        int x=(int)Math.round(me.getX()/(double)cell);
        int y=(int)Math.round(me.getY()/(double)cell);

        // ���� ���� �� �ִ� ��ǥ�� �ƴϸ� ���� ���´�.
        if(x==0 || y==0 || x==size+1 || y==size+1)return;

        // �ش� ��ǥ�� �ٸ� ���� ������ ������ ���� ���´�.
        if(map[x][y]==BLACK || map[x][y]==WHITE)return; 
        // ������� ���� ���� ��ǥ�� �����Ѵ�.
  if(turn==1){
        map[x][y]=color; 
  if(check(new Point(x, y), color)){
            ch("�浹�� �̰���ϴ�");   
        }
  turn=-turn;
  }
  else{
   map[x][y]=-color; 
   if(check(new Point(x, y), -color)){
            ch("�鵹�� �̰���ϴ�");         
        }
   turn=-turn;
  }

        // �̰���� �˻��Ѵ�.

        
        repaint();                                // �������� �׸���.    
      }

    });

  }   
  public void update(Graphics g){        // repaint�� ȣ���ϸ� �ڵ����� ȣ��ȴ�.

    paint(g);                             // paint�� ȣ���Ѵ�.

  }

  public void paint(Graphics g){                // ȭ���� �׸���.

    if(gbuff==null){                             // ���۰� ������ ���۸� �����.

      buff=createImage(getWidth(),getHeight());

      gbuff=buff.getGraphics();

    }

    drawBoard(g);    // �������� �׸���.

  }
  private void drawLine(){                     // �����ǿ� ���� �ߴ´�.

    gbuff.setColor(Color.black);

    for(int i=1; i<=size;i++){

      gbuff.drawLine(cell, i*cell, cell*size, i*cell);

      gbuff.drawLine(i*cell, cell, i*cell , cell*size);

    }

  }

  private void drawBlack(int x, int y){         // �� ���� (x, y)�� �׸���.

    Graphics2D gbuff=(Graphics2D)this.gbuff;

    gbuff.setColor(Color.black);

    gbuff.fillOval(x*cell-cell/2, y*cell-cell/2, cell, cell);  

  }

  private void drawWhite(int x, int y){         // �� ���� (x, y)�� �׸���.

    gbuff.setColor(Color.white);

    gbuff.fillOval(x*cell-cell/2, y*cell-cell/2, cell, cell);   
    //�ٵϾ� �׸��� ��...���콺 ����Ʈ���� �ٵϾ� ���� ����� ���߾ӿ� �����ٰ�...
  }

  private void drawStones(){                  // map ������ ������ ��� �׸���.

    for(int x=1; x<=size;x++)

     for(int y=1; y<=size;y++){

       if(map[x][y]==BLACK)

         drawBlack(x, y);

       else if(map[x][y]==WHITE)

         drawWhite(x, y);

     }

  }

   private void drawBoard(Graphics g){      // �������� �׸���.

    // ���ۿ� ���� �׸��� ������ �̹����� �����ǿ� �׸���.

    gbuff.clearRect(0, 0, getWidth(), getHeight());

    drawLine();

    drawStones();

    gbuff.setColor(Color.red);  

    g.drawImage(buff, 0, 0, this);
    //���²� �׷ȴ� buff�� g ȭ�鿡 �׷��ش�.
  }

  private boolean check(Point p, int col){

    if(count(p, 1, 0, col)+count(p, -1, 0, col)==4)

      return true;

    if(count(p, 0, 1, col)+count(p, 0, -1, col)==4)

      return true;

    if(count(p, -1, -1, col)+count(p, 1, 1, col)==4)

      return true;

    if(count(p, 1, -1, col)+count(p, -1, 1, col)==4)
      return true;
    return false;
  }
  private int count(Point p, int dx, int dy, int col){
    int i=0;
    for(; map[p.x+(i+1)*dx][p.y+(i+1)*dy]==col ;i++); //���ǽ��� ������ �ٷ� �������̵ȴ�.
    return i;
  }

}  // OmokBoard ���� ��
  public static void main(String []args){
   new OmokEx("����");
  }
}


