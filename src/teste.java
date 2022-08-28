import java.util.Arrays;

public class teste {

	public static void main(String[] args) 
	{
        int [][] inicial = {{1,2,3}, {5, 6, 7}, {4, 8, 0}};
        int [][] objetivo =  {{1,2,3}, {4, 5, 6}, {7, 8, 0}};
        
        System.out.println(Arrays.toString(inicial));
        
        if (Arrays.deepEquals(inicial,objetivo))
        	System.out.println("igual");
	}

}
