package Yelp.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opencsv.CSVReader;

public class AverageStars {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	// CSV file header
	private static final String FILE_HEADER = "id,elite,avg_stars,yelping_since,friends_count"; // file header for
															// retrieving top 20
															// business
	public static void main(String args[]) throws FileNotFoundException{
		CSVReader reader = new CSVReader(new FileReader("/Users/shardendu/dataset/top20csv.csv"));
		
		String FileName="/Users/shardendu/dataset/yelp_academic_dataset_user.json";
		Scanner scn=new Scanner(new File(FileName),"UTF-8");
	   // CSVWriter.writeCsvFile(scn);
	    
	    HashMap<String, Set<String>> friends = new HashMap<String, Set<String>>();
	   
	    String csvFileName = "/Users/shardendu/dataset/avg_friends_count.csv";
		FileWriter fileWriter = null;
		double av_stars= 0;
		try {
			fileWriter = new FileWriter(csvFileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			String user_id = null;
			int friendscount = 0;
			int rowcount = 0;
			while (scn.hasNext()) {
				rowcount++;
				JSONObject obj = (JSONObject) new JSONParser().parse(scn.nextLine());

				String userid = String.valueOf(obj.get("user_id"));
				
				//JSONArray ja = (JSONArray)obj.getJSONArray("friends");
				
				JSONArray jarr =(JSONArray)obj.get("friends");
				Set<String> frns = new HashSet<String>();
					
				
				for(int i=0;i<jarr.size();i++){
					frns.add(String.valueOf(jarr.get(i)));
				}
				friends.put(userid, frns);
				
				String line = "";
				reader.readNext();
				String[] nextline;
				
				for(String key : friends.keySet()){
					
					Set<String> list = friends.get(key);
					for(String fr :list){
						while ((nextline = reader.readNext()) != null) {
							//map.put(nextline[business_id_index], nextline[count_index]);
							if(fr == nextline[0]){
								av_stars += Double.parseDouble(nextline[2]);
							}
						}
					}
					av_stars= av_stars/(list.size());
					
				}
				
				
				
				
				JSONArray elite =(JSONArray)obj.get("elite");
				
				String avg_stars = String.valueOf(obj.get("average_stars"));
				String yelping_since= String.valueOf(obj.get("yelping_since"));
					// write to csv file
					fileWriter.append(userid);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(elite.size()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(av_stars));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(yelping_since);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(jarr.size()));
					fileWriter.append(NEW_LINE_SEPARATOR);

					av_stars=0;
				
				// if(rowcount>10)
				// break;
				System.out.println(rowcount);
			}
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}

	}
	
	
}
