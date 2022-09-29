package showImage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel  extends JDialog
{
	private static final long serialVersionUID = -2807200265369750472L;
	
	private JPanel panel;
	private JLabel pic;
	private JButton butao;
	private Container c;
	
	private Jogo jg;
	
	 public ImagePanel() 
	 {
		  try 
		  {
			  c = getContentPane();
			  
			   panel = new JPanel(new BorderLayout());
			
		       JButton btnBrowse = new JButton("Browse");
		       btnBrowse.addActionListener(e -> browseButtonActionPerformed(e));

		       panel.add(btnBrowse, BorderLayout.NORTH);
		        
			   pic = new JLabel();
			   panel.setSize(new Dimension(300,300));
			   panel.add(pic, BorderLayout.CENTER);
			  
			   butao = new JButton("Jogar");
			   butao.setVisible(true);
			   butao.addActionListener(e ->
			   {
				   new Thread() 
				   {
	                    @Override
	                    public void run() 
	                    {
	                    	 jg.jogar(getImage());
	                    }
				   }.start();
			   });
			   panel.add(butao, BorderLayout.SOUTH);
			  
			   c.add(panel);
			   setSize(new Dimension(700,700));
			   setLayout(null);
			   setVisible(true);
			   
			   setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			   
			   jg = new Jogo(this);
		  } 
		  catch (Exception e) {
			  e.printStackTrace();
		  }
	 }

	 private void setIagem(File f) throws IOException
	 {
		 BufferedImage img = ImageIO.read(f);
		 
		 if (img.getHeight() % 2 == 0)
			 img = CortaImage.resizeImage(img, img.getHeight(), img.getHeight());
		 else
			 img = CortaImage.resizeImage(img, img.getHeight() - 1, img.getHeight() - 1 );
		 
		 setImage(img);
	 }
	 
	 private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        JFileChooser fc = new JFileChooser("C:\\Users\\arali\\eclipse-workspace\\GamePlus8\\src");
	        int res = fc.showOpenDialog(null);
	        // We have an image!
	        try {
	            if (res == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                setIagem(file);
	            } // Oops!
	            else {
	                JOptionPane.showMessageDialog(null,
	                        "You must select one image to be the reference.", "Aborting...",
	                        JOptionPane.WARNING_MESSAGE);
	            }
	        } catch (Exception iOException) {
	        }
	    
	    }
	 
	 public BufferedImage getImage()
	 {
		 BufferedImage buffer;
		 
		 int x = 0;

		 buffer = new BufferedImage(pic.getIcon().getIconWidth(), pic.getIcon().getIconWidth(),
	                BufferedImage.TYPE_INT_ARGB);
		 
		 if (pic.getIcon().getIconWidth() != pic.getIcon().getIconWidth())
		 {
			 x = pic.getIcon().getIconWidth() / 3;
		 }
		 
		 Graphics g = buffer.getGraphics();
	        pic.getIcon().paintIcon(new JLabel(), g, x, 0);
	        g.dispose();
		 
	      return buffer;
	 }
	 
	 public JButton getJogar()
	 {
		 return butao;
	 }
	 
	 public void setImage(BufferedImage img)
	 {
		 try
		 {
			 panel.setSize(new Dimension(img.getWidth() + 100, img.getHeight() + 100));
			 pic.setSize(new Dimension(img.getWidth() + 100, img.getHeight() + 100));
			 pic.setIcon( new ImageIcon(img));
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
}