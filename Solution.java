import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sizeOfArray = scanner.nextInt();
		int[] rank = new int[sizeOfArray];
		for (int i = 0; i < rank.length; i++) {
			rank[i] = scanner.nextInt();
		}
		scanner.close();
		long result = getTotalDistributedCandies(rank);
		System.out.println(result);
	}

	/**
	 * The method calculates the distribution of candies in accord to the ranks of
	 * the persons (a higher ranking neighbor gets more candies from a lower ranking
	 * one; neighbors with the same ranking can have different number of candies),
	 * while minimizing the total number of distributed candies.
	 * 
	 * @return An array of long values, containing the minimum candies per person.
	 */
	private static long[] calculateCandiesDistribution(int[] rank) {
		long[] distribution = new long[rank.length];
		/**
		 * Each person can get a at least one candy.
		 */
		Arrays.fill(distribution, 1);

		/**
		 * Iterating in one direction, searching for a series of increasing rankings and
		 * distributing candies accordingly.
		 */
		for (int i = 1; i < rank.length; i++) {
			if (rank[i - 1] < rank[i]) {
				distribution[i] = distribution[i - 1] + 1;
			}
		}

		/**
		 * Iterating in the reverse direction, searching for a series of increasing
		 * rankings. If the persons get more candies than the candies distributed to
		 * them during the first iteration, the persons are ascribed the current number
		 * of candies. Otherwise they retain the number of candies from the first
		 * iteration.
		 */
		for (int i = rank.length - 1; i >= 1; i--) {
			if (rank[i] < rank[i - 1]) {
				if (distribution[i - 1] < distribution[i] + 1) {
					distribution[i - 1] = distribution[i] + 1;
				}
			}
		}
		return distribution;
	}

	/**
	 * The method sums the total number of distributed candies.
	 * 
	 * @return A long value, representing the minimum number of candies that can be
	 *         distributed in accord to the rules.
	 */
	private static long getTotalDistributedCandies(int[] rank) {
		long[] distribution = calculateCandiesDistribution(rank);
		long total = 0;
		for (int i = 0; i < distribution.length; i++) {
			total += distribution[i];
		}
		return total;
	}
}
