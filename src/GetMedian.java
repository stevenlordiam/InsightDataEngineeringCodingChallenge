import java.io.*;
import java.util.*;
import java.lang.*;

/**
* The GetMedian class implement the second feature of the Insight Data Engineering coding challenge
*
* @author  Wenyue Liu
* @version 1.0
* @since   July 2nd, 2015
*
*/

public class GetMedian {
	
	/** The main method makes use of getMedian method and return the running median count into a file
	  * @param args Unused
	  * @return A file in the ../tweet_output/ folder
	  */
	public static void main(String[] args)  {
		String inputFile = "../tweet_input/"; //input directory
		String outputFile = "../tweet_output/ft2.txt"; //output file
		getMedian(inputFile, outputFile);
	}
	
	/** This method is used to get the running median of the words in the files in the folder path
	  * @param inputPath  This is the input path of the files
	  * @param outputPath  This is the output path of the files
	  * @return Nothing
	  */
	public static void getMedian(String inputPath, String outputPath) {
		File inputFile = new File(inputPath);
		File[] fileList = inputFile.listFiles();
		TreeSet<String> fileNames = new TreeSet<String>(); 
		for (File f : fileList) {
			fileNames.add(f.getAbsolutePath());
		}
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		String line = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(outputPath));
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		//For each file in TreeSet find Median
		for(String fileName: fileNames){
			try {
				//open a file using BufferedReader
				bufferedReader = new BufferedReader(new FileReader(fileName));
				System.out.println("Reading file: "+ fileName);
				while ((line = bufferedReader.readLine()) != null) { //Read a line
					int len = line.split(" ").length; 
					if(line.split(" ")[0].equals("") && line.split(" ").length == 1) {  
						len = 0; 	//if line length is 1 and first character is "", it is a blank line
					}
					MedianHeap.push((float) len);
					bufferedWriter.write(String.format("%.2f",MedianHeap.getMedianNumber()) + "\n");
				}
				bufferedReader.close(); //close reading stream
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
		
		try {
			bufferedWriter.close();    //close writing stream
			System.out.println("Getting medians finished!");
			System.out.println("------------------------------");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}

/**
* The MedianHeap class implement the median heap by two heap
*/
class MedianHeap {	
	private static int elementNumber = 0;
	private static PriorityQueue<Float> minHeap = new PriorityQueue<Float>();
	private static PriorityQueue<Float> maxHeap = new PriorityQueue<Float>(20, new Comparator<Float>(){
		 	// implement max heap using comparator
			@Override
			public int compare(Float h1, Float h2) {
				return h2.compareTo(h1);
			}
	});
	
	/** This method is used to push the element into the MedianHeap class
	  * @param elem  This is the element added to the MedianHeap
	  * @return Nothing
	  */
	public static void push(float elem) {
		maxHeap.add(elem);
		if (elementNumber % 2 == 0) {
			if (minHeap.isEmpty()) {
				elementNumber++; 
				return;
			} else if (maxHeap.peek() > minHeap.peek()) {  // If maxHeap's peek is more than minHeap's peek then just change the peeks. 
				// float maxRoot = maxHeap.poll();
				// float minRoot = minHeap.poll();
				maxHeap.add(minHeap.poll());
				minHeap.add(maxHeap.poll());
			}
		} else {
			minHeap.add(maxHeap.poll());
		}
		elementNumber++;
	}
	
	/** This method is used to count the median number of the running words
	  * @param Nothing  
	  * @return The median number of the current words 
	  */
	public static float getMedianNumber() {
		if (elementNumber % 2 == 0) {
			return (float) ((maxHeap.peek() + minHeap.peek()) / 2.0);
		} else {
			return maxHeap.peek();
		}
	}
}