import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import com.opencsv.CSVReader;

/*
 * This file is used to calculate tf-idf score for the nouns extracted from review and tips text to make another 
 * dataset for task 2 that contains tf-idf scores instead of nouns
 * 
 * @author: Vimalendu Shekhar
 */
public class Tfidf_task2 {

	static String pathToIndex="/Volumes/Hoth/projects/index/review-tips-raw";
	private static final String FILE_HEADER ="User_id,Business_id,Cool,Useful,Funny,Likes,Elite,Yelping_since,Friends_count,Frnd_avg_stars,Takes_Reservations,Price_Range,Hours,Accepts_Credit_Card,Wi_Fi,Delivery,City,Latitude,Longitude,Casual,Classy,Romantic,Good_For_Groups,Good_for_Kids,Happy_Hour,Touristy,Trendy,Lot,Park_street,Park_validated,Valet,Check_0,Check_1,Check_2,Check_3,Check_4,Check_5,Check_6,ReviewTipsLength,Adj1,Adj2,Adj3,Adj4,Adj5,Adv1,Adv2,Adv3,Adv4,Adv5,user_share_business_ratings,per_user_ratings,ratio_of_reviews,Bussiness_Stars,User_Stars";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	public static void main(String args[]) throws IOException, ParseException{
		CSVReader csvreader = new CSVReader(new FileReader("/Users/shardendu/dataset/tfidfScore/out_class_changed.csv"));
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(pathToIndex)));
		
		IndexSearcher searcher = new IndexSearcher(reader);
		searcher.setSimilarity(new DefaultSimilarity()); 
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = null;
		QueryParser parser2 = null;
		String line = "";
		csvreader.readNext();
		String[] nextline;
		String fileName = "/Users/shardendu/dataset/output_out_00.csv";
		FileWriter fileWriter = null;
		fileWriter = new FileWriter(fileName);
		fileWriter.append(FILE_HEADER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		
		try{
			while ((nextline = csvreader.readNext()) != null) {
				
				fileWriter.append(String.valueOf(nextline[0]));
				fileWriter.append(COMMA_DELIMITER);
				for(int i=1;i< 39;i++){
					fileWriter.append(String.valueOf(nextline[i]));
					fileWriter.append(COMMA_DELIMITER);
				}
				for(int j=39; j < 49 ;j++){
					double tfidfscore=0.0;
					tfidfscore=calculateTFIDFScore(reader, nextline[j], new DefaultSimilarity());
					fileWriter.append(String.valueOf(tfidfscore));
					fileWriter.append(COMMA_DELIMITER);
					
				}
				for(int r=49; r < 53 ;r++){
					fileWriter.append(String.valueOf(nextline[r]));
					fileWriter.append(COMMA_DELIMITER);
					
				}
				fileWriter.append(String.valueOf(nextline[53]));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
		}catch (Exception e) {
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
		 
	public static double calculateTFIDFScore(IndexReader reader, String term, TFIDFSimilarity sim) throws IOException{
		TFIDFSimilarity similarity= sim;
		Fields fields = MultiFields.getFields(reader);
		int docnum = reader.numDocs();
		double tfidf=0;
		for(String fld: fields){
			int fre = reader.docFreq(new Term(fld,term));
			double tf= similarity.tf(fre);
	        double idf = similarity.idf(fre, docnum);
	        tfidf= tf*idf;
	        System.out.println("" + fld + ":" + term + " tf-idf=" + tf*idf);
		}
        return tfidf;
	}
	

}
e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
					e.printStackTrace();
				}
			}
		}
		
	
		 
	public static double calculateTFIDFScore(IndexReader reader, String term, TFIDFSimilarity sim) throws IOException{
		TFIDFSimilarity similarity= sim;
		Fields fields = MultiFields.getFields(reader);
		int docnum = reader.numDocs();
		double tfidf=0;
		for(String fld: fields){
			int fre = reader.docFreq(new Term(fld,term));
			double tf= similarity.tf(fre);
	        double idf = similarity.idf(fre, docnum);
	        tfidf= tf*idf;
	        System.out.println("" + fld + ":" + term + " tf-idf=" + tf*idf);
		}
        return tfidf;
	}
	
	
}
