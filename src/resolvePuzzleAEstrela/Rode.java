package resolvePuzzleAEstrela;

import java.util.ArrayList;
import java.util.Collections;

public class Rode
{
    private  Integer[][] mapaAtual;
    private int level;
    private int valorF;
    private Rode pai;

    public Rode(Integer[][] mapaAtual, int level, int valorF, Rode pai) 
    {
        this.mapaAtual = mapaAtual;
        this.level = level;
        this.valorF = valorF;
        this.pai = pai;
    }

    public Rode [] criaFilho()
    {
        int [][] moves = movesPossiveis();

        Integer [][] filho;

        Rode [] filhos =  new Rode[4];

        for (int i = 0; i < 4; i++)
        {
            filho = andar(moves[i]);

            if (filho != null)
                filhos[i] = new Rode(filho, level + 1, 0, this);
        }

        return filhos;
    }

    public Integer [][] andar(int[] objetivo)
    {
        int[] posicaoEspacoBranco = encontraEsapacoVazio();

        boolean podeX = objetivo[0] >= 0 && objetivo[0] < mapaAtual.length;
        boolean podeY = objetivo[1] >= 0 && objetivo[1] < mapaAtual[0].length;
        
        if ( podeX && podeY && mapaAtual[objetivo[0]][objetivo[1]] != null)
        {
        	Integer [][] mapaTemporario = copiaMapa();

           int valorMexido = mapaTemporario[objetivo[0]][ objetivo[1]];

           mapaTemporario[objetivo[0]][ objetivo[1]] = mapaTemporario[posicaoEspacoBranco[0]][posicaoEspacoBranco[1]];
           mapaTemporario[posicaoEspacoBranco[0]][posicaoEspacoBranco[1]] = valorMexido;
            
            return mapaTemporario;
        }
        else
            return null;
    }

    public int[] encontraEsapacoVazio()
    {
        int [] posicaoEspacoBranco;

        for (int i = 0; i < mapaAtual.length; i++)
        {
            for (int j = 0; j < mapaAtual[i].length; j++)
            {
                if (mapaAtual[i][j] != null && mapaAtual[i][j] == 0)
                {
                    posicaoEspacoBranco = new int[] {i,j};
                    return posicaoEspacoBranco;
                }
            }
        }

        return null;
    }

    public Integer [][] copiaMapa()
    {
    	Integer[][] novoMapa = new Integer[mapaAtual.length][mapaAtual[0].length];
        
        for (int i = 0; i < mapaAtual.length; i++)
        {
            for (int j = 0; j < mapaAtual[i].length; j++)
            {
                novoMapa[i][j] = mapaAtual[i][j];
            }
        }

        return novoMapa;
    }

    public int [][] movesPossiveis()
    {
        int[] posicaoEspacoBranco = encontraEsapacoVazio();

        int[] esquerda = {posicaoEspacoBranco[0],posicaoEspacoBranco[1] - 1};

        int[] direita = {posicaoEspacoBranco[0],posicaoEspacoBranco[1] + 1};

        int[] baixo = {posicaoEspacoBranco[0] + 1,posicaoEspacoBranco[1]};

        int[] cima =  {posicaoEspacoBranco[0] - 1,posicaoEspacoBranco[1]};

        int [][] possiveis = {cima, baixo, direita, esquerda};

        return possiveis;
    }

    public ArrayList<Integer[][]> getSolucao()
    {
    	ArrayList<Integer[][]> sol = new ArrayList<>();
    	
    	sol.add(mapaAtual);
    	
    	Rode aux = pai;
    	
    	while (aux != null)
    	{
    		sol.add(aux.getMapaAtual());
    		
    		aux = aux.getPai();
    	}
    	
    	Collections.reverse(sol);
    	
    	return sol;
    }
    
    public Rode getPai()
    {
    	return pai;
    }
    
    public Integer[][] getMapaAtual()
    {
        return this.mapaAtual;
    }

    public int getLevel()
    {
        return this.level;
    }

    public int getValorF()
    {
        return this.valorF;
    }

    public void setValorF(int valorF)
    {
        this.valorF = valorF;
    }
}