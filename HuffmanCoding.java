import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {

	char[] textArray;

	Map<Character, Integer> chars = new HashMap<>();

	Leaf root;

	HashMap<Character, String>codeMap = new HashMap<>();

	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		// TODO fill this in.
		textArray = text.toCharArray();

		PriorityQueue<Leaf> queue = new PriorityQueue<>(new Comparator<Leaf>() {
			@Override
			public int compare(Leaf a, Leaf b) {
				if (a.frequency > b.frequency) {
					return 1;
				} else if (a.frequency < b.frequency) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		for (char c : textArray) {
			if (chars.containsKey(c)) {
				int freq = chars.get(c);
				freq = freq + 1;
				chars.remove(c);
				chars.put(c, freq);
			} else {
				chars.put(c, 1);
			}
		}

		System.out.println("tree print");								// print method begin

		for(char c : chars.keySet()){
			int freq = chars.get(c);
			System.out.println(c + " " + freq);
		}

		int total = 0;
		for(char c : chars.keySet()){
			total = total + chars.get(c);
		}
		System.out.println("total " + total);							// ends

		for (char c : chars.keySet()) {
			Leaf l = new Leaf(c, chars.get(c), null, null);
			queue.offer(l);
		}

		while (queue.size() > 1) {
			Leaf a = queue.poll();
			Leaf b = queue.poll();

			int frequency = a.frequency + b.frequency;

			Leaf leaf = new Leaf('\0', frequency, a, b);

			queue.offer(leaf);
		}

		if(queue.size() == 1){
			this.root = queue.peek();
		}
	}

	/**
	 * find every root to leaf path and then assign the series of binary digits
	 * to the character at the leaf as the code.
	 */

	public String findPath(char input, Leaf root, String path) {

		String result;

		if (root.left != null && root.right != null) {
			if ((result = findPath(input, root.left, path + '0')) == null) {
				result = findPath(input, root.right, path + '1');
			}
		}
		else {
			result = (input == root.character) ? path : null;
		}
		return result;
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		String binaryCode = "";

		char[] array = text.toCharArray();

		for(char c : chars.keySet()){
			String code = findPath(c, root, "");
			codeMap.put(c, code);
			System.out.println(c + " " + code);							// print char codes
		}

		for(char t : array){
			String charCode = codeMap.get(t);
			binaryCode = binaryCode + charCode;
		}

		return binaryCode;
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		char[] encodedArray = encoded.toCharArray();

		String decoded = "";

		Leaf current = root;

		for(char c : encodedArray) {

			if (c == '0' && current.left != null) {
				current = current.left;
			}
			else if(c == '1' && current.right != null){
				current = current.right;
			}
			else {
				char letter = current.character;
				decoded = decoded + letter;
				current = root;
			}
		}

		System.out.println(textArray);
		System.out.println(decoded);

		return decoded;
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
