import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Represents a Markov chain, with connections and the like.
 * @author Alek Ratzloff
 *
 */
public class MarkovChain {
	
	private Hashtable<String, Weighttable> chain = new Hashtable<>();
	private Random random = new Random();
	private int order;
	
	public MarkovChain() {
		order = 1;
	}
	
	public MarkovChain(int order) {
		assert(order > 0);
		this.order = order;
	}
	
	/**
	 * Trains the Markov chain on some snippet of text. 
	 * @param text
	 */
	public void train(String text) {
		Scanner scanner = new Scanner(text);
		// t1, aka current
		String t1;
		// t2, aka next
		String t2 = scanner.next();
		while(scanner.hasNext()) {
			t1 = t2;
			t2 = scanner.next();
			if(!chain.containsKey(t1)) {
				chain.put(t1, new Weighttable());
			}
			// get the hashtable for this key
			Hashtable<String, Integer> markovTable = chain.get(t1);
			if(!markovTable.containsKey(t2)) {
				markovTable.put(t2, 1);
			} else {
				Integer val = markovTable.get(t2) + 1;
				markovTable.put(t2, val);
			}
		}
		scanner.close();
	}
	
	public String generateSentences(int count) {
		// choose a random seed
		int which = random.nextInt(chain.keySet().size());
		String word = (String)chain.keySet().toArray()[which];
		// select the first word to be one that a sentence would start with; that is, have it start with a word that does not
		// start with a lower case letter.
		while(Character.isLowerCase(word.charAt(0))) {
			which = random.nextInt(chain.keySet().size());
			word = (String)chain.keySet().toArray()[which];
		}
		
		return generateSentences(count, word);
	}
	
	public String generateSentences(int count, String seed) {
		String word = seed;
		String sentence = word;
		
		for(int i = 0; i < count; i++) {
			Stack<Character> quotes = new Stack<Character>();
			
			do{
				String append = word = getRandomWord(word);
				
				if(word.startsWith("\"") || word.startsWith("'")) {
					quotes.push(word.charAt(0));
				}
				if(word.endsWith("\"") || word.endsWith("'")) {
					char c = word.charAt(word.length() - 1);
					if(quotes.size() == 0) {
						// there's no quotes on the stack, so just get rid of the quote
						append = word.substring(0, word.length() - 2);
					} else if(c != quotes.peek()) { 
						// there's a quote in the stack - but it's mismatched
						append = word.replace(c, quotes.pop());
					} else {
						// same quotes - pop from the stack
						quotes.pop();
					}
				}
				
				sentence += " " + append;
			} while (!word.endsWith(".") && !word.endsWith("!") && !word.endsWith("?"));
			
			// append quotes at the end
			while(quotes.size() > 0) {
				sentence += quotes.pop();
			}
		}
		return sentence;
	}
	
	/**
	 * Returns a random word in the Markov chain.
	 * @param seed
	 * @return
	 */
	public String getRandomWord(String seed) {
		if(!chain.containsKey(seed)) {
			return ".";
		}
		
		Weighttable weights = chain.get(seed);
		return weights.getRandomWord();
	}
	
	public void saveToFile(String path) {
		// TODO : this	
	}
	
	public void loadFromFile(String path) throws FileNotFoundException {
		File file = new File(path);
		FileReader reader =  new FileReader(file);
		JSONObject root = (JSONObject) JSONValue.parse(reader);
		for(Object word : root.keySet()) {
			// TODO : this
		}
	}
	
	/**
	 * Gets the number of words that have a rule for them.
	 * @return the number of nodes in the Markov chain
	 */
	public int count() {
		return chain.size();
	}
	
	/**
	 * Gets the the number of items to put in the pool when generating a Markov chain.
	 * @return
	 */
	public int getOrder() {
		return order;
	}
}
