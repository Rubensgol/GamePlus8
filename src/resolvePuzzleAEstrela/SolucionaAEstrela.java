package resolvePuzzleAEstrela;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SolucionaAEstrela 
{
    private int tamanho;

    private int[][] objetivo;
    private Integer[][] inicial;
    private List<Rode> abertos;
    private List<Rode> fechados;
    

    public SolucionaAEstrela(int tamanho, Integer [][] inicial, int [][] objetivo) 
    {
        this.tamanho = tamanho;
        this.objetivo = objetivo;
        this.inicial = inicial;
        this.abertos = new ArrayList<>();
        this.fechados = new ArrayList<>();
    }

    public int calculaFuncaoF(Rode start, int [][] objetivo)
    {
        int valor =  calcularI(start, objetivo) +  calcularH(start, objetivo);
        return valor;
    }
    
    public int calcularI(Rode start, int[][] objetivo)
    {
    	int valor = 0;
    	
        for (int i = 0; i < tamanho; i++)
        {
            for (int j = 0; j < tamanho; j++)
            {
            	if (start.getMapaAtual()[i][j] != objetivo[i][j] )
            	{
            		f1 : for (int j2 = 0; j2 < objetivo.length; j2++) 
            		{
                		for (int i2 = 0; i2 < objetivo.length; i2++) 
                		{
    						if (start.getMapaAtual()[i][j] == objetivo[j2][i2])
    						{
    							valor += Math.abs(i - j2) + Math.abs(j - i2);
    							break f1;
    						}
    					}
					}
            	}
            }   
        }
    	
    	return valor;
    }

    public int calcularH(Rode start, int [][] objetivo)
    {
        int valor = 0;

        for (int i = 0; i < tamanho; i++)
        {
            for (int j = 0; j < tamanho; j++)
            {
                if (start.getMapaAtual()[i][j] != objetivo[i][j])
                    valor++;
            }   
        }

        return valor;
    }

    public boolean tem(List<Rode> lis, Rode atual)
    {
    	Rode achou = lis.parallelStream().filter(r -> Arrays.deepEquals(atual.getMapaAtual(),r.getMapaAtual()))
    		    .findFirst()
    		    .orElse(null);
    	
    	if (achou != null)
    		return true;
    	
        return false;
    }
    
    public Rode processa()
    {
        Rode nodoInicial = new Rode(inicial, 0, 0, null);

        Rode passos;
        
        nodoInicial.setValorF(calculaFuncaoF(nodoInicial, objetivo));

        abertos.add(nodoInicial);

        Rode atual;

        while(true)
        {
        	
            atual = abertos.get(0);

            passos = atual;

            if(Arrays.deepEquals(atual.getMapaAtual(),objetivo))
                break;

            Rode [] filhos = atual.criaFilho();

            for(Rode filho : filhos)
            {
                if (filho != null && ! tem(abertos, filho) && ! tem(fechados, filho))
                {
                    filho.setValorF(calculaFuncaoF(filho, objetivo));
                    abertos.add(filho);
                }
            }

            fechados.add(atual);
            
            if (abertos.size() > 0)
            	abertos.remove(0);
            else
            	return passos;
            
            abertos.sort(Comparator.comparing(Rode:: getValorF));
        }

        return passos;
    }
}
