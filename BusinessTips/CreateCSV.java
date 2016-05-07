package BusinessTips;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import constants.ConfigConstant;
import constants.ConfigConstant.TAGGER_TAGS;
/*
 * This code created CSV after extracting information from yelp dataset files.
 * @author:Vimalendu Shekhar
 */
public class CSVWriter {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	// CSV file header
	private static final String FILE_HEADER = "id,count"; // file header for
															// retrieving top 20
															// business
	


	public static void writeCsvFile(Scanner scn) {

		String csvFileName = "/Users/shardendu/dataset/sample_tip.csv";
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(csvFileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			String currentbusiness_id = null;
			int currentbusinesscount = 0;
			int rowcount = 0;
			while (scn.hasNext()) {
				rowcount++;
				JSONObject obj = (JSONObject) new JSONParser().parse(scn.nextLine());

				String business_id = String.valueOf(obj.get("business_id"));
				if (currentbusiness_id == null) {
					// null current item
					currentbusiness_id = business_id;
					currentbusinesscount++;
				}

				if (currentbusiness_id.equalsIgnoreCase(business_id))
					currentbusinesscount++;
				else {
					// write to csv file
					fileWriter.append(currentbusiness_id);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(currentbusinesscount));
					fileWriter.append(NEW_LINE_SEPARATOR);

					currentbusiness_id = business_id;
					currentbusinesscount = 1;
				}
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

}package BusinessTips;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

/*
 * This code created CSV after extracting information from yelp dataset files.
 * @author:Vimalendu Shekhar
 */
//import com.opencsv.CSVReader;

public class JSONReader {

	public static void main(String args[]) throws IOException, ParseException
	{
		String FileName="/Users/shardendu/dataset/yelp_academic_dataset_tip.json";
		Scanner scn=new Scanner(new File(FileName),"UTF-8");
	    CSVWriter.writeCsvFile(scn);
	   
	}
 }package BusinessTips;

/*
 * This code created CSV after extracting information from yelp dataset files.
 * @author:Vimalendu Shekhar
 */
public class ReviewData {

	public String business_id;
	public String userid;
	public String reviewText;
	public String tipsText;
	public String stars;
	public String likes;
	public String funny;
	public String useful;
	public String cool;
	public int textsize;

	public String getBusiness_id() {
		return business_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getFunny() {
		return funny;
	}

	public void setFunny(String funny) {
		this.funny = funny;
	}

	public String getUseful() {
		return useful;
	}

	public void setUseful(String useful) {
		this.useful = useful;
	}

	public String getCool() {
		return cool;
	}

	public void setCool(String cool) {
		this.cool = cool;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getTipsText() {
		return tipsText;
	}

	public void setTipsText(String tipsText) {
		this.tipsText = tipsText;
	}
}
ut.println("Reviews count:" + reviewData.size());
			System.out.println("Tips count:" + tipsData.size());
			
			
			String fileName = "/Users/shardendu/dataset/reviewandtips.csv";

			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER);
			
			
			fileWriter.append(NEW_LINE_SEPARATOR);
			for (ReviewData reviewDataitem : reviewData) {
				fileWriter.append(reviewDataitem.userid);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(reviewDataitem.business_id);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(reviewDataitem.stars);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(reviewDataitem.likes);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(reviewDataitem.funny);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(reviewDataitem.useful);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(reviewDataitem.cool);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(reviewDataitem.textsize));
				
				tags = POSTaggerUtil.getAllFilteredTaggedWordPair(tagger, reviewDataitem.reviewText, FILTERED_TAGS);
				for(Object o : tags.keySet()){
					if(Arrays.asList(adj).contains(String.valueOf(o))){
						Set<String> nouns = tags.get(o);
						for(String noun : nouns){
							if(adjcount < 5){
								fileWriter.append(COMMA_DELIMITER);
								fileWriter.append(POSTaggerUtil.lemmatize(noun,String.valueOf(o), true));
								adjcount++;
							}
						}
					}else if (Arrays.asList(adv).contains(String.valueOf(o))){
						Set<String> nouns = tags.get(o);
						for(String noun : nouns){
							if(advcount < 5){
								fileWriter.append(COMMA_DELIMITER);
								fileWriter.append(POSTaggerUtil.lemmatize(noun,String.valueOf(o), true));
								advcount++;
							}	
						}
					}
				}
				if((advcount+adjcount < 10)){
					int count = advcount+adjcount;
					while(count < 10){
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append("?");
						count++;
					}
				}
				//fileWriter.append();
				fileWriter.append(NEW_LINE_SEPARATOR);
				adjcount=0;
				advcount=0;	
			}		
			//writing from tips objects
			for (ReviewData currenttipData : tipsData) {
				fileWriter.append(currenttipData.userid);
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(currenttipData.business_id);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(currenttipData.stars);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(currenttipData.likes);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(currenttipData.funny);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(currenttipData.useful);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(currenttipData.cool);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(currenttipData.textsize));
				tags = POSTaggerUtil.getAllFilteredTaggedWordPair(tagger, currenttipData.tipsText, FILTERED_TAGS);
				for(Object o : tags.keySet()){
					if(Arrays.asList(adj).contains(String.valueOf(o))){
						Set<String> advs = tags.get(o);
						for(String advb : advs){
							if(adjcount < 5){
								fileWriter.append(COMMA_DELIMITER);
								fileWriter.append(POSTaggerUtil.lemmatize(advb,String.valueOf(o), true));
								adjcount++;
							}	
						}
					}else if (Arrays.asList(adv).contains(String.valueOf(o))){
						Set<String> advs = tags.get(o);
						for(String advb : advs){
							if(advcount < 5){
								fileWriter.append(COMMA_DELIMITER);
								fileWriter.append(POSTaggerUtil.lemmatize(advb,String.valueOf(o), true));
								advcount++;
							}
						}
					}
				}
				if((advcount+adjcount < 10)){
					int count = advcount+adjcount;
					while(count < 10){
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append("?");
						count++;
					}
				}
				fileWriter.append(NEW_LINE_SEPARATOR);
				adjcount=0;
				advcount=0;
			}		

			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
