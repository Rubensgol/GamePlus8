package showImage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import resolvePuzzleAEstrela.Rode;
import resolvePuzzleHorizontal.Soluciona;

public class MostraImagem 
{

	public static void main(String[] args) 
	{
		Scanner ler = new Scanner(System.in);
		
		ImagePanel tela = new ImagePanel();
		EditaImagem mi = new EditaImagem();
		 
		BufferedImage img = tela.getImage();
		
		System.out.println("Quantas linhas");
		int linhas = 2;
		int partes = linhas + 1;
		
		ArrayList<ImagemJogo> imgs = mi.desmonta(img, partes);
		
		try 
		{
			for (int i = 0; i < 5; i++) 
			{
				Collections.shuffle(imgs);
				
				tela.setImage(mi.monta(img.getHeight(), imgs));
				
				Thread.sleep(2000);
			}
			
			Integer[][] inicial = new Integer[partes + 1][partes];
			
			for (int j = 0; j < inicial[0].length; j++)
			{
				inicial[0][j] = null;
			}
			
			inicial[0][inicial[0].length - 1] = 0;
			
			int pegaImg = 0;
			
			for (int i = 1; i < inicial.length; i++) 
			{
				for (int j = 0; j < inicial[0].length; j++) 
				{
					inicial[i][j] = imgs.get(pegaImg).getPosicao();
					pegaImg++;
				}
			}
			
	        Integer [][] objetivo = {{null,null,0}, {1,2,3}, {4, 5, 6}, {7, 8, 9}};
	       
	        Soluciona s = new Soluciona(3, inicial, objetivo);
	        
	        Rode solucao = s.processa();
	        
	        for (Integer [][] integers : solucao.getSolucao()) 
	        {
	        	ArrayList<ImagemJogo> imgMostra = new ArrayList<>();
	        	
	        	for (int i = 1; i < objetivo.length; i++) 
	        	{
		        	for (int j = 0; j < objetivo.length; i++) 
		        	{
		        		final Integer numero = integers[i][j];
		        		
		        		imgMostra.add(imgs.parallelStream().filter(r -> r.getPosicao() == numero)
		    		    .findFirst()
		    		    .orElse(null));
					}
				}
	        	
	        	tela.setImage(mi.monta(img.getHeight(), imgs));
	        	
	        	Thread.sleep(2000);
			}
	        
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ler.close();
	}
}
