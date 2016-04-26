import java.util.Random;
import java.lang.Math;

public class CustomFormula implements Runnable{

	int globalrow;
	double[] storage;
	double modifyNum;
	double modifiedresult = 0, rowminimum = Double.MAX_VALUE;
	double[][] array;
	Random rand = new Random();
	
	public CustomFormula(double[][] array, int globalrow, double[] storage)
	{
		this.array = array;
		this.globalrow = globalrow;
		this.storage = storage;
	}
	
	public void run()
	{		
		for(int counter = 0; counter < 350; counter++)
		{
			for(int c = 0; c < 100; c++)//first row + modification
			{
				modifyNum = -.5 + 1 * rand.nextDouble();//generating -.5 to .5
				if(array[globalrow][c] + modifyNum >= -5.12 || array[globalrow][c] <= 5.12)
				{
					array[globalrow][c] += modifyNum;//adding generated modify number to previous value
				}
				//result += (c+1) * Math.pow(array[globalrow][c],2);//adding previous values into a to result
				
				modifiedresult += (c+1) * Math.pow(array[globalrow][c],2);//getting and adding the modified result with formula
			}
			if(modifiedresult < rowminimum)
				rowminimum = modifiedresult;
			
			modifiedresult = 0;
		}
		
		storage[globalrow] = rowminimum;
	}
}
