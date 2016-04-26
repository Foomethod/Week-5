import java.util.Random;
public class Main {

	public static void main(String args[])
	{
		int counter = 0;
		double genNum, minimum = Double.MAX_VALUE;
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
		for(int r = 0; r < 200; r++)
		{
			task[r] = new CustomFormula(array,r,storage);
			threadarray[r]  = new Thread(task[r]);
			threadarray[r].start();
		}
		
		for(int r = 0; r < 200; r++)
		{
			try {
				threadarray[r].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int a = 0; a < 200; a++)
		{
			if(storage[a] < minimum)
			{
				minimum = storage[a];
			}
		}

		long endtime = System.currentTimeMillis();
		
		long totaltime = (endtime - starttime);
		System.out.printf("200 threads together...\nMinimum value for all 200 rows: %.2f"
				+ "\nTotal time taken: %dms", minimum,totaltime);
	}
}
