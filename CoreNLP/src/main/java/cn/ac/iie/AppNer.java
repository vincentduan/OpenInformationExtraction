package cn.ac.iie;

import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * Hello world!
 *
 */
public class AppNer {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, ner, regexner,coref");
		//props.put("regexner.mapping", "cn/ac/iie/jg-regexner.txt");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String sentence2 = "Kosgi Santosh sent an email to Stanford University. He didn't get a reply";
		Annotation document = new Annotation(sentence2);
		pipeline.annotate(document);
		pipeline.prettyPrint(document, System.out);
	}
}
