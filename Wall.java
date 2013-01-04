import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;

public class Wall
{
	private double x;
	private double y;
	private Image image;

	public Wall(double x, double y, int pixelWidth, int pixelHeight)
	{
		Image temp = createImage(pixelWidth, pixelHeight);
    	this.image = temp;
		this.x = x;
		this.y = y;
	}

	protected BufferedImage createImage(int pixelWidth, int pixelHeight)
	{
		BufferedImage temp = new BufferedImage(pixelWidth, pixelHeight, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < temp.getWidth(); i++)
		{
			for(int j = 0; j < temp.getHeight(); j++)
			{
				temp.setRGB(i, j, Color.GRAY.getRGB());
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
}