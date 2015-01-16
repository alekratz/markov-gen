import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MarkovGen {
	
	private static boolean verbose = false;
	private static ArrayList<String> paths = new ArrayList<>();
	private static int generateCount = 1;
	private static int paragraphCount = 1;
	private static int order = 1;
	private static boolean generateJson = false;
	private static String jsonOut = "";
	
	public static void printUsage() {
		System.out.println("usage: MarkovGen [options] input1 [input 2 ...]");
		System.out.println("Inputs may either be JSON encodings of a Markov chain, or a text file.");
		System.out.println("File extensions for JSON encodings must be .json to be recognized.");
		System.out.println();
		System.out.println("options:");
		System.out.println("-? -h -help .....................prints this message");
		System.out.println("-v -verbose .....................verbose output");
		System.out.println("-c -count .......................number of sentences to generate. default: 1");
		System.out.println("-p -paragraphs ..................number of paragraphs to generate. default: 1");
		System.out.println("-o -order .......................how many words are in each node. default: 1");
		System.out.println("-g -generate ....................generates a JSON object of the markov chain");
		System.out.println("-j -json ........................the name for the outputted json file (implies -g)");
	}
	
	public static void parseArgs(String[] args) {
		if(args.length == 0) {
			printUsage();
			System.exit(0);
		}
		
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-v") || args[i].equals("-verbose")) {
				verbose = true;
			} else if(args[i].equals("-?") || args[i].equals("-help") || args[i].equals("-h")) {
				printUsage();
				System.exit(0);
			} else if(args[i].equals("-c") || args[i].equals("-count")) {
				String countStr = args[++i];
				try {
					generateCount = Integer.parseInt(countStr);
				} catch(NumberFormatException ex) {
					System.out.println("Error with flag -c/-count: " + ex.getMessage());
					System.exit(1);
				}
			} else if(args[i].equals("-p") || args[i].equals("-paragraph")) {
				String pcountStr = args[++i];
				try {
					paragraphCount = Integer.parseInt(pcountStr);
				} catch(NumberFormatException ex) {
					System.out.println("Error with flag -c/-count: " + ex.getMessage());
					System.exit(1);
				}
			} else if(args[i].equals("-o") || args[i].equals("-order")) {
				String orderStr = args[++i];
				try {
					order = Integer.parseInt(orderStr);
				} catch(NumberFormatException ex) {
					System.out.println("Error with flag -o/-order: " + ex.getMessage());
					System.exit(1);
				}
			} else if(args[i].equals("-g") || args[i].equals("-generate")) {
				generateJson = true;
			} else if(args[i].equals("-j") || args[i].equals("-json")) {
				generateJson = true;
				jsonOut = args[++i];
			} else {
				paths.add(args[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		parseArgs(args);
		MarkovChain chain = new MarkovChain(order);
		
		for(String path : paths) {
			try {
				File input = new File(path);
				FileReader reader = new FileReader(input);
				char[] buffer = new char[(int)input.length()];
				reader.read(buffer);
				reader.close();
				if(buffer[0] == 0)
					buffer[0] = ' ';
				String contents = new String(buffer);
				verbosePrintln("load " + path);
				chain.train(contents); // choo choo
			} catch(IOException ex) {
				System.err.println("Error reading " + path + ": " + ex.getMessage());
			}
		}
		verbosePrintln("Loaded " + chain.count() + " chains of order " + order);
		
		// Print sentences
		for(int i = 0; i < paragraphCount; i++) {
			String sentences = chain.generateParagraph(generateCount);
			System.out.println(sentences);
			System.out.println();
		}
		
		// Generate json
		if(generateJson) {
			// Create a filename if you need to
			if(jsonOut.isEmpty())
				jsonOut = paths.get(0) + ".json";
			try {
				chain.saveToFile(jsonOut);
				verbosePrintln("Saved " + chain.count() + " chains of order " + order + " to " + jsonOut);
			} catch(IOException ex) {
				System.err.println("Error writing " + jsonOut + ": " + ex.getMessage());
			}
		}
	}
	
	public static void verbosePrintln(String text) {
		if(verbose)
			System.out.println(text);
	}
}
