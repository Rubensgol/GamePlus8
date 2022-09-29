package showImage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import resolvePuzzleAEstrela.Rode;
import resolvePuzzleAEstrela.SolucionaAEstrela;

public class Jogo
{
	private ImagePanel tela;
	private EditaImagem mi;
	
	public Jogo(ImagePanel tela)
	{
		this.tela = tela;
		mi = new EditaImagem();
	}
	
	public boolean jogar(BufferedImage img)
	{
		int linhas = 2;
		int partes = linhas + 1;
		
		ArrayList<ImagemJogo> imgs = mi.desmonta(img, partes);
		
		try 
		{
			BufferedReader bufferedReader;
			bufferedReader = new BufferedReader(new FileReader("possiveis.json"));
			Type listType = new TypeToken<ArrayList<Integer[][]>>(){}.getType();

			List<Integer[][]> lista = new ArrayList<Integer[][]>();
				   
			lista = new Gson().fromJson(bufferedReader, listType);
				
			ArrayList<Integer[][]> embaralha = new ArrayList<>();
			Random r = new Random();
			
			for (int i = 0; i < 5; i++) 
			{
				embaralha.add(lista.get(r.nextInt(180000)));
				tela.setImage(mi.monta(embaralha.get(i), imgs, img.getHeight()));
				
				Thread.sleep(2000);
			}
			
	        Integer [][] objetivo = {{null,null,0}, {1,2,3}, {4, 5, 6}, {7, 8, 9}};
	       
	        SolucionaAEstrela s = new SolucionaAEstrela(3, embaralha.get(embaralha.size() - 1 ), objetivo);
	        
	        Rode solucao = s.processa();
	        
	        s = null;
	        
	        if (solucao != null)
	        {
	        	ArrayList<Integer[][]> passos = solucao.getSolucao();
	        	
		        for (Integer [][] integers : passos) 
		        {
		        	tela.setImage(mi.monta(integers, imgs, img.getHeight()));
		        	
		        	Thread.sleep(2000);
				}
	        }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return true;
	}
}
