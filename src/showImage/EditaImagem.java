package showImage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class EditaImagem 
{
	public BufferedImage monta(int w, List<ImagemJogo> imgs)
	{
		int partes = (int) Math.sqrt(imgs.size());
		
		int xN = 0;
		int yN = 0;
		int im = 0;
		int xParte = w / partes;
		int yParte = w / partes;
		
		BufferedImage bufferedImage = new BufferedImage(w, w + xParte, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = bufferedImage.createGraphics();
		
		for (int j = 0; j < partes - 1; j++) 
		{
			g2d.draw(new Rectangle(xN, yN, xParte, yParte));
			
			g2d.setColor(Color.WHITE);
			
			g2d.fillRect(xN, yN, xParte, yParte);
			
			xN += xParte;
		}
		
		yN += yParte;
		xN = 0;
		
		for (int i = 0; i < partes; i++) 
		{
			for (int j = 0; j < partes; j++) 
			{
				g2d.drawImage(imgs.get(im).getImagem(), xN, yN, null);
				
				xN += xParte;
				im++;
			}

			xN = 0;
			
			yN += yParte;
		}
		
		g2d.dispose();
		
		return bufferedImage;
	}
	
	public ArrayList<ImagemJogo> desmonta(BufferedImage img, int partes)
	{
		ArrayList<ImagemJogo> imgs = new ArrayList<>();
		
		int xC = 0;
		int yC = 0;
		int hC = img.getHeight() / partes;
		int wC = img.getWidth() / partes;
		int posi = 1;
		
		for (int x = 0; x < partes; x++) 
		{
			for (int y = 0; y < partes; y++)
			{
				imgs.add(new ImagemJogo(img.getSubimage(xC, yC, wC, hC), posi) );
				
				xC += hC;
				posi++;
			}
			
			xC = 0;
			
			yC += wC;
		}
		
		return imgs;
	}
}
