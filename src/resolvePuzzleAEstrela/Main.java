package resolvePuzzleAEstrela;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import resolvePuzzleHorizontal.Soluciona;

public class Main
{
    public static void main(String[] args) 
	{
    	
		Integer [][] iniIntegers = {{null,null,0}, {1,2,3}, {4, 5, 6}, {7, 8, 9}};
		
        Integer [][] objetivo = {{null,null,0}, {4,7,3}, {8, 0, 6}, {2, 5, 1}};
    	
        Soluciona s = new Soluciona(3, iniIntegers, objetivo);
        
        s.processa();
        
		GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.create();
	    FileWriter writer;
		try 
		{
			writer = new FileWriter("possiveis.json");
			//writer.write(gson.toJson(s.salvar));
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
        System.out.println("Passo seguinte");
    }
}
