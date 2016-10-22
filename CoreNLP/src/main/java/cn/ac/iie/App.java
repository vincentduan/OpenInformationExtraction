package cn.ac.iie;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Create the Stanford CoreNLP pipeline
		Properties props = new Properties();
		props.setProperty("annotators",
				"tokenize,ssplit,pos,lemma,depparse,natlog,openie");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String sentence2 = "President Obama will meet with Congressional leaders on Friday, and House Republicans summoned lawmakers back for a Sunday session, in a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts scheduled to hit next week.";
		Annotation document = new Annotation(sentence2);
		pipeline.annotate(document);
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			// Get the OpenIE triples for the sentence
			Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
			for (RelationTriple triple : triples) {
				 System.out.println("(" + triple.subjectLemmaGloss() + ";" + triple.relationLemmaGloss() + ";" + triple.objectLemmaGloss() + ")");
			}
		}
	}
}
