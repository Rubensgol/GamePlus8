package resolvePuzzleHorizontal;

import resolvePuzzleAEstrela.Rode;

public class Main {

	public static void main(String[] args) 
	{
        Integer [][] inicial = {{null,null,3},  {1,2,0}, {4, 5, 6}, {7, 8, 9}};
        Integer [][] objetivo = {{null,null,0}, {1,2,3}, {4, 5, 6}, {7, 8, 9}};
        Soluciona s = new Soluciona(3, inicial, objetivo);

        Rode soluca = s.processa();

	}

}
