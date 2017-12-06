package cn.ac.iie;

import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class TrainingTest {
	public static void main(String[] args) {
		String props = "StanfordCoreNLP-chinese.properties";
		/*Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");*/
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation;
		// if data from file
		// annotation = new Annotation(IOUtils.slurpFileNoExceptions(file));
		annotation = new Annotation("我们搭建这个中国科学院信息工程研究所系统是出于研究目的所以整个系统还是实验性的没有展示界面");
		pipeline.annotate(annotation);
		pipeline.prettyPrint(annotation, System.out);
	}
}
