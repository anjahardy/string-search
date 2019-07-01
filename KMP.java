/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	private int[] M;

	double start = 0;

	public KMP(String pattern, String text) {
		// TODO maybe fill this in.
		start = System.currentTimeMillis();							// start algorithm timer

		int m = pattern.length();
		this.M = new int[m];
		this.M[0] = -1;
		this.M[1] = 0;

		int j = 0;
		int pos = 2;

		while(pos < m){
			if(pattern.charAt(pos - 1) == pattern.charAt(j)){
				this.M[pos] = j + 1;
				pos += 1;
				j += 1;
			}
			else if(j > 0){
				j = this.M[j];
			}
			else{
				this.M[pos] = 0;
				pos += 1;
			}
		}
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		// TODO fill this in.
		int m = pattern.length();
		int n = text.length();

		int k = 0;
		int i = 0;

		while((k + i) < n){
			if(pattern.charAt(i) == text.charAt(k+i)){
				i += 1;
				if(i == m){

					double end1 = System.currentTimeMillis();
					double totalTime1 = end1 - start;
					System.out.println("kmp time " + totalTime1);

					return k;
				}
			}
			else if(this.M[i] == -1){
				k = k + i + 1;
				i = 0;
			}
			else{
				k = k + i - this.M[i];
				i = this.M[i];
			}
		}

		double end2 = System.currentTimeMillis();						// stop timer
		double totalTime2 = end2 - start;								// find total time
		System.out.println("kmp time " + totalTime2);

		return -1;
	}
}
