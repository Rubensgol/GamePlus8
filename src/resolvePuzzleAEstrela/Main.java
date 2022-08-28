package resolvePuzzleAEstrela;

import java.util.List;

public class Main
{
    public static void main(String[] args) 
	{
        int [][] inicial = {{4,1,3}, {2, 0, 6}, {7, 5, 8}};
        int [][] objetivo = {{1,2,3}, {4, 5, 6}, {7, 8, 0}};
        //SolucionaAEstrela s = new SolucionaAEstrela(3, inicial, objetivo);
        
        Rode soluca = s.processa();

        System.out.println("Passo seguinte");
    }
}
