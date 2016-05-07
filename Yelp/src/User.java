package Yelp.src;

import java.util.List;

import org.json.simple.JSONObject;


public class User {
	public String yelping_since = null;
	public JSONObject votes = null;
	public long review_count = 0;
	public String name = null;
	public String user_id = null;
	public List<String> friends = null;
	public long fans = 0;
	public double average_stars = 0;
	public String type = "user";
	public JSONObject compliments = null;
	public List<Long> elite = null;
	
	public String toString() {
		return "yelping_since: " + yelping_since + "\n" 
				+ "votes: " + votes + "\n"
				+ "review_count: " + review_count + "\n"
				+ "name: " + name + "\n"
				+ "user_id: " + user_id + "\n"
				+ "friends: " + friends + "\n"
				+ "fans: " + fans + "\n"
				+ "average_stars: " + average_stars + "\n"
				+ "type: " + type + "\n"
				+ "compliments: " + compliments + "\n"
				+ "elite: " + elite;
	}
	
	public String toCSV() {
		return yelping_since + "|" 
				+ votes + "|"
				+ review_count + "|"
				+ name + "|"
				+ user_id + "|"
				+ friends + "|"
				+ fans + "|"
				+ average_stars + "|"
				+ type + "|"
				+ compliments + "|"
				+ elite;
	}
	
String removeBars(String s) {
		
		return s.replace("|", "");
	}
}