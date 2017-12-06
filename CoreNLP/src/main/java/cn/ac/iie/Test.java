package cn.ac.iie;

import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;


public class Test {
	public static void main(String[] args) {
		String props = "StanfordCoreNLP-chinese.properties";
		/*Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");*/
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation;
		// if data from file
		// annotation = new Annotation(IOUtils.slurpFileNoExceptions(file));
		//annotation = new Annotation("中国科学院信息工程研究所是2011年批准成立的中国科学院直属科研机构。研究所按照“软硬兼修，矛盾兼容，开合有法，张弛有度”的办所方针，秉承“打造一流平台，集聚一流人才，支撑国家需求，引领学科发展，努力成为国家在信息工程领域的战略科技力量”的组织目标，面向国家战略需求，在信息安全科技领域，开展基础理论与前沿技术研究，开发应用性技术与系统，为国家信息化进程提供核心关键技术支撑与系统解决方案。 ");
		annotation = new Annotation("金正恩的父亲是金正日");
		pipeline.annotate(annotation);
		pipeline.prettyPrint(annotation, System.out);
	}
}
