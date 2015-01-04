import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MarkovGen {
	
	private static boolean verbose = false;
	private static ArrayList<String> paths = new ArrayList<>();
	private static int generateCount = 1;
	
	public static void printUsage() {
		System.out.println("usage: MarkovGen [options] input1 [input 2 ...]");
		System.out.println("options:");
		System.out.println("-? -h -help .....................prints this message");
		System.out.println("-v -verbose .....................verbose output");
		System.out.println("-c -count .......................number of sentences to generate. default: 1");
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
			} else if(args[i].equals("-c")) {
				String countStr = args[++i];
				generateCount = Integer.parseInt(countStr);
			} else {
				paths.add(args[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		parseArgs(args);
		MarkovChain chain = new MarkovChain();
		
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
				System.out.println("Error reading " + path + ": " + ex.getMessage());
			}
		}
		verbosePrintln("Loaded " + chain.count() + " words");
		
		String sentences = chain.generateSentences(generateCount);
		System.out.println(sentences);
		System.out.println();
	}
	
	public static void verbosePrintln(String text) {
		if(verbose)
			System.out.println(text);
	}
}
