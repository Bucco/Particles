import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

// author Vineet Kosaraju

public class SimpleBoard extends JPanel implements MouseMotionListener, ActionListener, KeyListener
{
  private Timer timer;
  private double boardW;
  private double boardH;
  private JFrame owner;
  private ArrayList<Particle> particles;
  private ArrayList<Wall> walls;


  public SimpleBoard(int x, int y, JFrame owner)
  {
      particles = new ArrayList<Particle>();
      walls = new ArrayList<Wall>();
      addMouseMotionListener(this);
      addKeyListener(this);
      setFocusable(true);
      setBackground(Color.BLACK);
      setDoubleBuffered(true);
      boardW = x;
      boardH = y;
      owner = owner;
      timer = new Timer(10, this);
      timer.start();
  }
/*-------------------------------------------------------------------------------------------------------------------
                                        NOTES BY NIK!           */
//By Nik: remove this completely and then in move, check ONLY 3 POINTS, the one at the bottom-center, the one at the
//bottom left, and the one at the bottom right. A way you can check that is to see if the color at that point is black
//or not. If it is black, that means there is nothing there. If it is not black, then push the particle away from that
//point. Ex: bottom is filled. Push it up. Left is filled, push it right... etc.
  public boolean canMove(Particle part)
  {
    // Replace with better code, this is experimentation
    int tolerance = part.getImage().getWidth(null);
    for(int i = 0; i < walls.size(); i++)
    {
      Wall wall = walls.get(i);
      int size = wall.getImage().getWidth(null);
      // my particle y is less than the wall's y with tolerance of 10
      // wall x - my particle x < length of wall
      if( (part.getX() >= wall.getX()) && ((part.getX() - wall.getX()) < size) && (Math.abs(wall.getY() - part.getY()) < tolerance))
        return false;
    }
    for(int i = 0; i < particles.size(); i++)
    {
      Particle temp = particles.get(i);
      if(!part.equals(temp))
      {
        int size = temp.getImage().getWidth(null);
        if( (part.getX() == temp.getX() ) && ((temp.getY() - part.getY()) < tolerance) && ((temp.getY() > part.getY())) )
          return false;      
      }
    }
    return true;
  }

  public void mouseMoved(MouseEvent b)
  {
    particles.add(new Particle((int) b.getX(), (int) b.getY(), (int) boardH, 4));
  }

  public void mouseDragged(MouseEvent b)
  {
    walls.add(new Wall((int) b.getX(), (int) b.getY(), 8, 4));
  }

  public void keyReleased(KeyEvent e) { }
  public void keyTyped(KeyEvent e) { }
  public void keyPressed(KeyEvent e)
  {
    if(e.getKeyCode() == KeyEvent.VK_R)
    {
      particles.clear();
      walls.clear();
      repaint();
    }
    if(e.getKeyCode() == KeyEvent.VK_W)
    {
      walls.clear();
      repaint();
    }
    if(e.getKeyCode() == KeyEvent.VK_P)
    {
      particles.clear();
      repaint();
    }
  }

  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D)g;
    for(int i = particles.size() - 1; i >= 0; i--)
    {
      Particle temp = particles.get(i);
      if(temp.isVisible())
        g2d.drawImage(temp.getImage(), (int) temp.getX(), (int) temp.getY(), this);
      else
      {
        particles.remove(i);
      }
    }
    for(int i = 0; i < walls.size(); i++)
    {
      Wall tep = walls.get(i);
      g2d.drawImage(tep.getImage(), (int) tep.getX(), (int) tep.getY(), this);
    }
    g.dispose();
  }

  public void actionPerformed(ActionEvent e)
  {

    for(int i = 0; i < particles.size(); i++)
    {
      if(canMove(particles.get(i)))
        particles.get(i).move();
    }
    repaint();
  }

}
