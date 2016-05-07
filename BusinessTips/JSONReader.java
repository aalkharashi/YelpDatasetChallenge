package BusinessTips;

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
 }