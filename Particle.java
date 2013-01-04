import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;

public class Particle
{
	private double x;
	private double y;
	private Image image;
	private boolean visible;
	private int BOARD_HEIGHT = 390;
	private double MISSILE_SPEED = 1;

	public Particle(double x, double y, int boardH, int pixelWidth)
	{
		Image temp = createImage(pixelWidth);
    	this.image = temp;
    	this.visible = true;
		this.x = x;
		this.y = y;
		BOARD_HEIGHT = boardH;
	}

	protected BufferedImage createImage(int pixelWidth)
	{
		BufferedImage temp = new BufferedImage(pixelWidth, pixelWidth, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < temp.getWidth(); i++)
		{
			for(int j = 0; j < temp.getHeight(); j++)
			{
				temp.setRGB(i, j, Color.BLUE.getRGB());
			}
		}
		return temp;
	}

	public Image getImage()
	{
		return this.image;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public boolean isVisible()
	{
		return this.visible;
	}

	public void setVisible(boolean to)
	{
		visible = to;
	}

	public void move()
	{
		y += MISSILE_SPEED;
		MISSILE_SPEED += 0.0003;
		if(y > BOARD_HEIGHT)
			visible = false;
		// edit so if bottom left corner touches, move right, vice versa for right corner
	}
}