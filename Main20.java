import java.util.Random;
public class Main20 {
	
	public static void main(String args[])
	{
		int counter = 0;
		double genNum;
		double minimum = Double.MAX_VALUE;
		double[] storage = new double[200];
		Random rand = new Random();
		double[][] array = new double[200][100];
		CustomFormula[] task = new CustomFormula[200];
		Thread[] threadarray = new Thread[200];
		
		for(int r = 0; r < 200; r++)//doesn't require synchronization as all threads are working on a different row
		{		
			for(int c = 0; c < 100; c++)
			{
				genNum = -5.12 + 10.24 * rand.nextDouble();
				array[r][c] = genNum;
			}			
		}
		
		long starttime = System.currentTimeMillis();
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				task[counter] = new CustomFormula(array,counter,storage);
				threadarray[counter] = new Thread(task[counter]);
				threadarray[counter].start();
				counter++;
			}
			
			for(int j = 0; j < 10; j++)//joins every 10 thread created
			{
				try {
					threadarray[j + i*10].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}				
		}
		for(int a = 0; a < 200; a++)
		{
			if(storage[a] < minimum)
			{
				minimum = storage[a];
			}				
		}
		/*
		for(int j = 0; j < 200; j++)
		{
			System.out.println(storage[j]);
		}*/
		long endtime = System.currentTimeMillis();
		
		System.out.printf("Joining every 10 threads...\nMinimum value for all 200 rows: %.2f\n"
				+ "Total time taken: %dms",minimum,(endtime - starttime));
		
	}
}
