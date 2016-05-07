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

}