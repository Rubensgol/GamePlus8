package showImage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import resolvePuzzleAEstrela.Rode;
import resolvePuzzleAEstrela.SolucionaAEstrela;
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
			
			Integer [][] iniIntegers = {{null,null,0}, {1,2,3}, {4, 6, 7}, {5, 8, 9}};
			
	        Integer [][] objetivo = {{null,null,0}, {1,2,3}, {4, 5, 6}, {7, 8, 9}};
	       
	        SolucionaAEstrela s = new SolucionaAEstrela(3, iniIntegers, objetivo);
	        
	        Rode solucao = s.processa();
	        
	        if (solucao != null)
	        {
	        	
	        	ArrayList<Integer[][]> passos = solucao.getSolucao();
	        	
		        for (Integer [][] integers : passos) 
		        {
		        	ArrayList<ImagemJogo> imgMostra = new ArrayList<>();
		        	
		        	for (int i = 0; i < integers.length; i++) 
		        	{
			        	f1 : for (int j = 0; j < integers[0].length; j++) 
			        	{
			        		Integer numero = integers[i][j];
			        		if (numero == null)
			        		{
			        			continue f1;
			        		}
			        		
			        		if (numero == 0)
			        		{
			        			imgMostra.add(null);
			        			continue f1;
			        		}
			        			
			        		for (ImagemJogo posicao : imgs) 
			        		{
			        			if (posicao.getPosicao() == numero)
			        				imgMostra.add(posicao);
			        			
							}
						}
					}
		        	
		        	tela.setImage(mi.monta(img.getHeight(), imgMostra));
		        	
		        	Thread.sleep(2000);
				}
	        }
	        
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ler.close();
	}
}
