package lang;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class OmokEx extends JFrame {  
  private OmokBoard board=new OmokBoard(20,30);      // 오목판 객체
  public OmokEx(String title){                        // 생성자
  add(board);
  	this.setTitle(title);
    this.setBounds(200,20,650,700);
    this.setVisible(true);
    setLocationRelativeTo(null);
 
  }
  public void ch(String s){
   JOptionPane.showMessageDialog(this,s,"SYSTEM",JOptionPane.INFORMATION_MESSAGE);
  }
  
  class OmokBoard extends Canvas{               // 오목판을 구현하는 클래스
  public static final int BLACK=1, WHITE=-1;     // 흑과 백을 나타내는 상수
  private int[][] map;                            // 오목판 배열
  private int size;               // size는 격자의 가로 또는 세로 개수, 15로 정한다.
  private int cell;                           // 격자의 크기(pixel)
  private int color=BLACK;                 // 사용자의 돌 색깔
  // true이면 사용자가 돌을 놓을 수 있는 상태를 의미하고,
  // false이면 사용자가 돌을 놓을 수 없는 상태를 의미한
  private Graphics gboard, gbuff;    // 캔버스와 버퍼를 위한 그래픽스 객체
  private Image buff;                 // 더블 버퍼링을 위한 버퍼
  int turn=1;
  OmokBoard(int s, int c){           // 오목판의 생성자(s=15, c=30)   
    this.size=s; this.cell=c;
    map=new int[size+2][];            // 맵의 크기를 정한다.
    for(int i=0;i<map.length;i++)
      map[i]=new int[size+2];
    setBackground(new Color(242,162,33));         // 오목판의 배경색을 정한다.
    setSize(size*(cell+1)+size, size*(cell+1)+size);    // 오목판의 크기를 계산한다.
    addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent me){     // 마우스를 누르면       

        // 마우스의 좌표를 map 좌표로 계산한다.
        int x=(int)Math.round(me.getX()/(double)cell);
        int y=(int)Math.round(me.getY()/(double)cell);

        // 돌이 놓일 수 있는 좌표가 아니면 빠져 나온다.
        if(x==0 || y==0 || x==size+1 || y==size+1)return;

        // 해당 좌표에 다른 돌이 놓여져 있으면 빠져 나온다.
        if(map[x][y]==BLACK || map[x][y]==WHITE)return; 
        // 상대편에게 놓은 돌의 좌표를 전송한다.
  if(turn==1){
        map[x][y]=color; 
  if(check(new Point(x, y), color)){
            ch("흑돌이 이겼습니다");   
        }
  turn=-turn;
  }
  else{
   map[x][y]=-color; 
   if(check(new Point(x, y), -color)){
            ch("백돌이 이겼습니다");         
        }
   turn=-turn;
  }

        // 이겼는지 검사한다.

        
        repaint();                                // 오목판을 그린다.    
      }

    });

  }   
  public void update(Graphics g){        // repaint를 호출하면 자동으로 호출된다.

    paint(g);                             // paint를 호출한다.

  }

  public void paint(Graphics g){                // 화면을 그린다.

    if(gbuff==null){                             // 버퍼가 없으면 버퍼를 만든다.

      buff=createImage(getWidth(),getHeight());

      gbuff=buff.getGraphics();

    }

    drawBoard(g);    // 오목판을 그린다.

  }
  private void drawLine(){                     // 오목판에 선을 긋는다.

    gbuff.setColor(Color.black);

    for(int i=1; i<=size;i++){

      gbuff.drawLine(cell, i*cell, cell*size, i*cell);

      gbuff.drawLine(i*cell, cell, i*cell , cell*size);

    }

  }

  private void drawBlack(int x, int y){         // 흑 돌을 (x, y)에 그린다.

    Graphics2D gbuff=(Graphics2D)this.gbuff;

    gbuff.setColor(Color.black);

    gbuff.fillOval(x*cell-cell/2, y*cell-cell/2, cell, cell);  

  }

  private void drawWhite(int x, int y){         // 백 돌을 (x, y)에 그린다.

    gbuff.setColor(Color.white);

    gbuff.fillOval(x*cell-cell/2, y*cell-cell/2, cell, cell);   
    //바둑알 그리는 것...마우스 포인트에서 바둑알 반을 빼줘야 정중앙에 찍힌다고...
  }

  private void drawStones(){                  // map 놓여진 돌들을 모두 그린다.

    for(int x=1; x<=size;x++)

     for(int y=1; y<=size;y++){

       if(map[x][y]==BLACK)

         drawBlack(x, y);

       else if(map[x][y]==WHITE)

         drawWhite(x, y);

     }

  }

   private void drawBoard(Graphics g){      // 오목판을 그린다.

    // 버퍼에 먼저 그리고 버퍼의 이미지를 오목판에 그린다.

    gbuff.clearRect(0, 0, getWidth(), getHeight());

    drawLine();

    drawStones();

    gbuff.setColor(Color.red);  

    g.drawImage(buff, 0, 0, this);
    //여태껏 그렸던 buff를 g 화면에 그려준다.
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
    for(; map[p.x+(i+1)*dx][p.y+(i+1)*dy]==col ;i++); //조건식이 맞으면 바로 증감식이된다.
    return i;
  }

}  // OmokBoard 정의 끝
  public static void main(String []args){
   new OmokEx("오목");
  }
}


