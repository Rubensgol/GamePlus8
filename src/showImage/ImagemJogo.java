package showImage;

import java.awt.image.BufferedImage;

public class ImagemJogo 
{
	private BufferedImage imagem;
	private int posicao;
	
	public ImagemJogo(BufferedImage imagem, int posicao)
	{
		this.imagem = imagem;
		this.posicao = posicao;
	}
	
	public BufferedImage getImagem() {
		return imagem;
	}
	
	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}
	
	public int getPosicao() {
		return posicao;
	}
	
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	
}
