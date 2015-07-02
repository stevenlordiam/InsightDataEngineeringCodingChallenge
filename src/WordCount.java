import java.io.*;
import java.util.*;
import java.lang.String.*;

/**
  * The WordCount class implement the first feature of the Insight Data Engineering coding challenge
  *
  * @author  Wenyue Liu
  * @version 1.0
  * @since   July 2nd, 2015
  *
  */

public class WordCount {
	
	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	
	/** The main method makes use of all the read/write methods
	  * @param args Unused
	  * @return A file in the ../tweet_output/ folder
	  */
	public static void main(String[] args) {
		String inputPath = "../tweet_input/";
		String outputPath = "../tweet_output/ft1.txt"; 	// output file for WordCount
		String stream =  readFile(inputPath); 
		countWords(stream);
		writeFile(outputPath);
	}
	
	/** This method is used to read in files in the folder path
	  * @param inputPath  This is the input path of the files
	  * @return String This is the string stream of the files in the folder 
	  */
	public static String readFile(String inputPath) {
		
		File inputFile = new File(inputPath);
		BufferedReader bufferedReader = null;
		String line = "";
		String stream = "";
		TreeSet<String> fileNames = new TreeSet<String>();
		File[] fileList = inputFile.listFiles();
		for (File f : fileList) {
			fileNames.add(f.getAbsolutePath());
		}
		for (String fn : fileNames) {
			try {
				bufferedReader = new BufferedReader(new FileReader(fn));
				System.out.println("------------------------------");
				System.out.println("Reading file: " + fn);
				while ((line = bufferedReader.readLine()) != null) {
					stream += line; 
				}
				bufferedReader.close();
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
		return stream;
		
	}
	
	/** This method is used to count the words in the string stream of the files in the folder path
	  * @param stream  This is the string stream of the files in the folder
	  * @return Nothing 
	  */
	public static void countWords(String stream) {	
		StringTokenizer stringTokenizer = new StringTokenizer(stream);
		while(stringTokenizer.hasMoreTokens()){
			String currentWord = stringTokenizer.nextToken();
			Integer wordFreq = map.get(currentWord);
			if (wordFreq==null){
				map.put(currentWord, 1);
			}else{
				map.put(currentWord, wordFreq+1);
			}
		}
	}
	
	/** This method is used to write the word frequency out to a file in the folder path
	  * @param outputPath  This is the outputPath path of the files
	  * @return Nothing 
	  */
	public static void writeFile(String outputPath){
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(outputPath));
			for (Map.Entry<String, Integer> entry : map.entrySet()) { 
				bufferedWriter.write(String.format("%-30s %5s\n",entry.getKey(),entry.getValue())); // left-justified and right-justified answer pairs
			}
			bufferedWriter.close();
			System.out.println("Counting words finished!");
			System.out.println("------------------------------");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}