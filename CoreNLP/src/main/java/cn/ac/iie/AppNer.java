package cn.ac.iie;

import java.io.*;
import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * Hello world!
 *
 */
public class AppNer {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		/*props.put("annotators", "tokenize, ssplit, pos, lemma, ner, regexner,coref");
		//props.put("regexner.mapping", "cn/ac/iie/jg-regexner.txt");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String sentence2 = "Kosgi Santosh sent an email to Stanford University. He didn't get a reply";
		Annotation document = new Annotation(sentence2);
		pipeline.annotate(document);
		pipeline.prettyPrint(document, System.out);
	}*/
		//props.put("annotators", "tokenize, ssplit, pos, lemma, ner, regexner");
		props.put("annotators", "tokenize, ssplit, pos, lemma, ner");
		//props.put("regexner.mapping", "cn/ac/iie/jg-regexner.txt");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		//String sentence2 = "Jack attended Mitcham Demonstration School and Unley High School. Subsequently, Gillard studied at the University of Adelaide, but cut short her courses to move to Melbourne, Victoria, in 1982, where she worked with the Australian Union of Students and was the organisation's president from 1983 to 1984. Gillard later graduated from the University of Melbourne, with a Bachelor of Laws degree (1986) and a Bachelor of Arts degree (1989).University of Melbourne is very good";
        BufferedReader br = new BufferedReader(new FileReader("D:/test/yuanwen.txt"));
        String sentence2 = "";
        FileWriter fileWriter = new FileWriter(new File("D:/test/NE.txt"),true);
        while((sentence2=br.readLine())!=null){
            Annotation document = new Annotation(sentence2);
            pipeline.annotate(document);
            //pipeline.prettyPrint(document, System.out);
            try {
                pipeline.jsonPrint(document, fileWriter);
                fileWriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
