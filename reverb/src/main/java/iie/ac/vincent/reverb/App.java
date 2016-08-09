package iie.ac.vincent.reverb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import edu.washington.cs.knowitall.extractor.ReVerbExtractor;
import edu.washington.cs.knowitall.extractor.conf.ConfidenceFunction;
import edu.washington.cs.knowitall.extractor.conf.ReVerbOpenNlpConfFunction;
import edu.washington.cs.knowitall.nlp.ChunkedSentence;
import edu.washington.cs.knowitall.nlp.OpenNlpSentenceChunker;
import edu.washington.cs.knowitall.nlp.extraction.ChunkedBinaryExtraction;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/test?" + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		String sql = "insert into triple(arg1,rel,arg2) values(?,?,?)";
		PreparedStatement  pstmt  = conn.prepareStatement(sql);
		FileInputStream file_in = new FileInputStream("D://nlp//Hillary.txt");
		//FileInputStream file_in = new FileInputStream("D://nlp//Hillary.txt");
		//FileInputStream file_in = new FileInputStream("D://nlp//input.txt");
		int in_size = file_in.available();
		byte[] InBuff = new byte[in_size];
		file_in.read(InBuff);
		file_in.close();
		OpenNlpSentenceChunker chunker = new OpenNlpSentenceChunker();
		String sentStr = new String(InBuff);
		// sentStr =
		// "Xi has attempted to legitimize the authority of the Communist Party, by introducing far-ranging measures to enforce party discipline and to ensure internal unity, as well as initiating an unprecedented and far-reaching campaign against corruption. Xi's anti-corruption campaign has led to high-profile arrests such as General Xu Caihou and Zhou Yongkang. He has also imposed further restrictions over ideological discourse, advocating the concept of 'internet sovereignty'.";
		ChunkedSentence sent = chunker.chunkSentence(sentStr);

		// Prints out extractions from the sentence.
		ReVerbExtractor reverb = new ReVerbExtractor();
		ConfidenceFunction confFunc = new ReVerbOpenNlpConfFunction();
		int i = 1;
		String temp = "";
		String deleteSql = "truncate table triple";
		pstmt.executeUpdate(deleteSql);
		for (ChunkedBinaryExtraction extr : reverb.extract(sent)) {
			double conf = confFunc.getConf(extr);
			temp += "Sentence" + i + "\n";
			temp += conf + "\t" + extr.getArgument1() + "\t"
					+ extr.getRelation() + "\t" + extr.getArgument2() + "\n" + "\n";
			pstmt.setString(1, extr.getArgument1()+"");
			pstmt.setString(2, extr.getRelation()+"");
			pstmt.setString(3, extr.getArgument2()+"");
			pstmt.execute();
			i++;
		}
		// Output to File
		FileOutputStream file_out = new FileOutputStream(
				"D://nlp//triple_out2.txt");
		byte[] data = temp.getBytes();
		file_out.write(data);
		file_out.close();
	}
}
