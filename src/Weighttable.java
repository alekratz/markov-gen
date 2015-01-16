import java.util.Hashtable;
import java.util.Random;

/**
 * A weight table is used to have keys that contain an ordered set of N strings corresponding to values that are string-
 * integer pairs.
 * @author Alek Ratzloff <alekratz@gmail.com>
 *
 */
public class Weighttable extends Hashtable<String, Integer> {
	/**
	 * This is to kill the warnings
	 */
	private static final long serialVersionUID = 570882516693488244L;
	private Random random = new Random();
	
	/**
	 * Constructs a new weight table with default everything for the hashtable.
	 */
	public Weighttable() {
		super();
	}
	
	/**
	 * Gets a string, based on the weight that it holds in the table.
	 * @return
	 */
	public String getRandomWord() {

		Object[] words = this.keySet().toArray();
		int range = 0;
		for(Object word : words) {
			int weight = this.get(word);
			range += weight;
		}
		
		int choice = random.nextInt(range);
		int sum = 0;
		for(Object word : words) {
			int weight = this.get(word);
			sum += weight;
			if(sum > choice)
				return (String)word;
		}
		
		return ".";
	}
}
