package Yelp.src;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonToCSVUser {
 	@SuppressWarnings("unchecked")
    public static void main(String[] args) throws MalformedURLException, IOException {
 		JSONParser parser = new JSONParser();
 		BufferedReader br = new BufferedReader(new FileReader("/Abdul/yelp_academic_dataset_user.json"));
 		String line = null;
 		
 		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
 				new FileOutputStream("/Abdul/yelp/yelp_academic_dataset_user.csv")));
 	
 		
 		
 		
 		
 		while ((line = br.readLine()) != null) {
 			User u = new User();
        try {
        	
        	JSONObject jsonObject = (JSONObject) parser.parse(line); 
 
        	u.yelping_since = (String) jsonObject.get("yelping_since");
        	u.votes = (JSONObject) jsonObject.get("votes");
        	u.review_count = (long) jsonObject.get("review_count");
        	u.name = (String) jsonObject.get("name");
        	u.user_id = (String) jsonObject.get("user_id");
        	u.friends = (List<String>) jsonObject.get("friends");
        	u.average_stars = (double) jsonObject.get("average_stars");
        	u.type = (String) jsonObject.get("type");
        	u.compliments = (JSONObject) jsonObject.get("compliments");
        	u.elite = (List<Long>) jsonObject.get("elite");
        	
           
            
        	//System.out.println(b.toString());
        	bw.write(u.toCSV());
        	bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
 		}
 		br.close();
 		bw.close();
 		System.out.println("Conversion is complete");
 	}
 	
}