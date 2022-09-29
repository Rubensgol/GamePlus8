package resolvePuzzleHorizontal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import resolvePuzzleAEstrela.Rode;

public class Soluciona 
{
    private Integer[][] objetivo;
    private Integer[][] inicial;
    private List<Rode> abertos;
    private List<Rode> fechados;
    
    public Soluciona(int tamanho, Integer [][] inicial, Integer [][] objetivo) 
    {
        this.objetivo = objetivo;
        this.inicial = inicial;
        this.abertos = new ArrayList<>();
        this.fechados = new ArrayList<>();
    }
    
    public boolean chegou(Rode atual)
    {
    	if(Arrays.deepEquals(atual.getMapaAtual(),objetivo))
    		return true;
    	
        return false;
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
        Rode nodoInicial = new Rode(inicial, 0, 0, null, 0);

        Rode passos = null;
        
        abertos.add(nodoInicial);

        Rode atual;

        while(true)
        {
            atual = abertos.get(0);
            
            if (chegou(atual))
            {
            	passos = atual;
                break;
            }
            
            Rode [] filhos = atual.criaFilho();

            for(Rode filho : filhos)
            {
            	if (filho != null && ! tem(fechados, filho) && ! tem(abertos, filho))
            		abertos.add(filho);
            }
            
            abertos.remove(0);
            fechados.add(atual);
            
            if (abertos.size() == 0)
            	break;
        }

        return passos;
    }
}
