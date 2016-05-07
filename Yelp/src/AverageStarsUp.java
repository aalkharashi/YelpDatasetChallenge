package Yelp.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;

public class AverageStarsUp {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	// CSV file header
	private static final String FILE_HEADER = "id,elite,avg_stars,yelping_since,friends_count"; // file header for
															// retrieving top 20
															// business
	public static void main(String args[]) throws ParseException, IOException{
		
		String FileName="/Users/shardendu/dataset/yelp_academic_dataset_user.json";
		Scanner scn=new Scanner(new File(FileName),"UTF-8");
	    
	    HashMap<String, Set<String>> friends = new HashMap<String, Set<String>>();
	    HashMap<String, Double> stars = new HashMap<String,Double>();
	    HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
		double av_stars= 0;
		try {
			
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
				
				stars.put(userid, Double.parseDouble(String.valueOf(obj.get("average_stars"))));
				ArrayList<String> user_data = new ArrayList<String>();
				JSONArray elite =(JSONArray)obj.get("elite");
				user_data.add(String.valueOf(elite.size()));
				user_data.add(String.valueOf(obj.get("yelping_since")));
				user_data.add(String.valueOf(frns.size()));
				data.put(userid, user_data);
				System.out.println(rowcount);
			}
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} 
		calcluateAvgStars(stars,friends,data);
	}
	
	public static void calcluateAvgStars(HashMap<String, Double> stars, HashMap<String, Set<String>> friends, HashMap<String, ArrayList<String>> data) throws ParseException, IOException{
		
		String csvFileName = "/Users/shardendu/dataset/avg_friends_count.csv";
		FileWriter fileWriter = null;
		HashMap<String, Double> result = new HashMap<String, Double>();
		double avg = 0;
		for(String key : friends.keySet()){
			Set<String> frnds= friends.get(key);
			if(frnds.isEmpty()){
				result.put(key, 0.0);
			}else{
				double avg_star=0;
				for(String fr : frnds){
					if(stars.get(fr)==0 || stars.get(fr)==null){
						avg_star=0;
					}
					avg_star+= stars.get(fr);
				}
				
				avg= avg_star/frnds.size();
				result.put(key, avg);
			}
			
			
		}
		String FileName="/Users/shardendu/dataset/yelp_academic_dataset_user.json";
		Scanner scn=new Scanner(new File(FileName),"UTF-8");
		try {
		fileWriter = new FileWriter(csvFileName);

		// Write the CSV file header
		fileWriter.append(FILE_HEADER.toString());

		// Add a new line separator after the header
		fileWriter.append(NEW_LINE_SEPARATOR);
		int rowcount=0;
		while (scn.hasNext()) {
			rowcount++;
			
			
			JSONObject obj = (JSONObject) new JSONParser().parse(scn.nextLine());

			String userid = String.valueOf(obj.get("user_id"));
			ArrayList<String> user_data= data.get(userid);
				// write to csv file
				
					fileWriter.append(userid);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(user_data.get(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(result.get(userid)));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(user_data.get(1));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(user_data.get(2));
					fileWriter.append(NEW_LINE_SEPARATOR);

				
				
				
			
			// if(rowcount>10)
			// break;
			System.out.println(rowcount);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

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
