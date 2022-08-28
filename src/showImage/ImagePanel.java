package showImage;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel 
{
	private BufferedImage img;
	private JPanel panel;
	private JLabel pic;
	
	 public ImagePanel() 
	 {
		  try 
		  {
			   JFrame f = new JFrame("Add an Image to a JPanel");
			   panel = new JPanel();
			
			   img = ImageIO.read(new File("urso.png"));
			   
			   if (img.getHeight() % 2 == 0)
				   img = CortaImage.resizeImage(img, img.getHeight(), img.getHeight());
			   else
				   img = CortaImage.resizeImage(img, img.getHeight() - 1, img.getHeight() - 1 );
			   
			   pic = new JLabel(new ImageIcon(img));
			   panel.setSize(new Dimension(400,400));
			   panel.add(pic);
			
			   f.add(panel);
			   f.setSize(600, 600);
			   f.setLayout(null);
			   f.setVisible(true);
		  } 
		  catch (IOException e) {}
	 }
	 
	 public BufferedImage getImage()
	 {
		 return img;
	 }
	 
	 public void setImage(BufferedImage img)
	 {
		 try
		 {
			 panel.setSize(new Dimension(img.getWidth(), img.getHeight()));
			 pic.setSize(new Dimension(img.getWidth(), img.getHeight()));
			 pic.setIcon(new ImageIcon(img));
		 }
		 catch (Exception e) {}
	 }
}