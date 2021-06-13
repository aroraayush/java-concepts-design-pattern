package concurrency;

import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Shows how to use Logger for debugging.
 * Creates NUM_ROWS threads to search for the maximum value of a large matrix. Each
 * thread searches for max in one row of the matrix.
 */
public class SolvingForMaxUsingExecutorAndLogger {

	public final static int NUM_ROWS = 10;
	// made static as suggested by the Logger API
	private final static Logger log = LogManager.getRootLogger();

	/** Inner class that represents a worker thread that computes the maximum in the given 1D array */
	private static class Worker implements Callable {
		double max = Double.MIN_VALUE;
		double[] row;

		public Worker(double[] row) {
			this.row = row;
		}

		/** Find the maximum value in the given row
		 * 
		 */
		@Override
		public Object call() throws Exception {
			for (int i = 0; i < row.length; i++)
				max = Math.max(max, row[i]);
			log.debug("Computed max: " + max);
			return max;
		}
	}

	public static void main(String[] args) {
		double max = Double.MIN_VALUE;

		double [][] bigMatrix = generateBigMatrix(NUM_ROWS, 1000000);
		ExecutorService exec = Executors.newCachedThreadPool();
		try {
			Future<Double>[] results = new Future[NUM_ROWS];
			for (int i = 0; i < NUM_ROWS; i++) {
				Future<Double> res = exec.submit(new Worker(bigMatrix[i]));
				results[i] = res;
			}
			for (int i = 0; i < NUM_ROWS; i++) {
				double val = results[i].get();
				if (val > max)
					max = val;
			}

		}
		catch (Exception e) {
			log.error("Exception while running the worker: " + e);
		}
		exec.shutdown();

		try {
			exec.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.debug("Computed the maximum for the whole matrix: " + max);
	}

	/**
	 * 
	 * @param n the number of rows in the matrix
	 * @param m the number of columns in the matrix
	 * @return the matrix n by m of random values in the range from 0 to 1.
	 */
	public static double[][] generateBigMatrix(int n, int m) {
		double[][] matrix = new double[n][m];
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				matrix[row][col] = Math.random();
				//System.out.print(matrix[row][col] + "\t");
			}
			//System.out.println();

		}
		return matrix;

	}
}