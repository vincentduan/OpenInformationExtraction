import java.io.FileInputStream;
//import java.io.FileReader;  
//import java.io.FileNotFoundException;  
//import java.io.IOException;  
//Output to file
//import java.nio.CharBuffer;  
//import java.io.File;  
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

//import java.io.BufferedReader;
//import java.io.FileReader;

//import java.io.InputStreamReader;
//import java.io.InputStream;

/**
 * A demo illustrating how to call the OpenIE system programmatically.
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/test?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		String sql = "insert into triple(arg1,rel,arg2) values(?,?,?)";
		PreparedStatement  pstmt  = conn.prepareStatement(sql);
		
		
		// Create the Stanford CoreNLP pipeline
		Properties props = new Properties();
		props.setProperty("annotators",
				"tokenize,ssplit,pos,lemma,depparse,natlog,openie");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// Annotate an example document.

		// Fixed
		// Annotation doc = new
		// Annotation("Born in Honolulu, Obama is a US Citizen.");
		// Annotation doc = new
		// Annotation("Born in a small town, she took the midnight train.");
		// Annotation doc = new
		// Annotation("Born in Honolulu, Obama is a US Citizen. Born in a small town, she took the midnight train.");

		// String sentence1 =
		// "Born in Honolulu, Obama is a US Citizen. Born in a small town, she took the midnight train.";
		// Annotation doc = new Annotation(sentence1);

		// Read from File one time
		//FileInputStream file_in = new FileInputStream("D://nlp//Hillary2.txt");
		//FileInputStream file_in = new FileInputStream("D://nlp//Hillary.txt");
		FileInputStream file_in = new FileInputStream("D://nlp//input2.txt");
		String sentence2;
		int in_size = file_in.available();
		byte[] InBuff = new byte[in_size];

		file_in.read(InBuff);
		file_in.close();
		sentence2 = new String(InBuff);
		Annotation doc = new Annotation(sentence2);

		/*
		 * // Read from File by line InputStream inputStream = new
		 * FileInputStream
		 * ("C://Users//zhao//Desktop//OpenIE//OpenIE_CoreNLP//input1.txt");
		 * InputStreamReader inputReader = new InputStreamReader(inputStream);
		 * BufferedReader bufferReader = new BufferedReader(inputReader);
		 * 
		 * String line = null; StringBuffer strBuffer = new StringBuffer();
		 * 
		 * while ((line = bufferReader.readLine()) != null) {
		 * strBuffer.append(line); }
		 * 
		 * inputStream.close(); inputReader.close(); bufferReader.close();
		 * 
		 * Annotation doc = new Annotation(strBuffer.toString());
		 */

		pipeline.annotate(doc);

		// Output to File
		FileOutputStream file_out = new FileOutputStream(
				"D://nlp//triple_out.txt");
		String TripleRes;
		byte[] OutBuff = new byte[] {};
		byte[] NewLine = { '\r', '\n' };

		int count = 1;

		// Loop over sentences in the document
		for (CoreMap sentence : doc
				.get(CoreAnnotations.SentencesAnnotation.class)) {
			// Get the OpenIE triples for the sentence
			Collection<RelationTriple> triples = sentence
					.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);

			// Print the triples
			TripleRes = "Sentence" + count + "\r\n";
			OutBuff = TripleRes.getBytes();
			file_out.write(OutBuff, 0, OutBuff.length);

			//System.out.printf("Sentence %d:\n", count);
			for (RelationTriple triple : triples) {

				TripleRes = triple.confidence + "\t"
						+ triple.subjectLemmaGloss() + "\t"
						+ triple.relationLemmaGloss() + "\t"
						+ triple.objectLemmaGloss();

				OutBuff = TripleRes.getBytes();
				file_out.write(OutBuff, 0, OutBuff.length);
				file_out.write(NewLine, 0, NewLine.length);
				pstmt.setString(1, triple.subjectLemmaGloss());
				pstmt.setString(2, triple.relationLemmaGloss());
				pstmt.setString(3, triple.objectLemmaGloss());
				pstmt.execute();
				//System.out.println(triple.confidence + "\t" + triple.subjectLemmaGloss() + "\t" + triple.relationLemmaGloss() + "\t" + triple.objectLemmaGloss());
				// break;
			}
			file_out.write(NewLine, 0, NewLine.length);
			//System.out.println("");
			count++;
		}
		file_out.close();
	}
}
