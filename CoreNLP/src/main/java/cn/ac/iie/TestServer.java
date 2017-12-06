package cn.ac.iie;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 2017/11/4
 * Time: 15:32
 */
public class TestServer {
    public static void main(String[] args) {
        /*Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");*/
        String props = "StanfordCoreNLP-chinese.properties";
        StanfordCoreNLPClient pipeline = new StanfordCoreNLPClient(props, "http://localhost", 9000, 2);
// read some text in the text variable
        String text ="金正恩的父亲是金正日"; // Add your text here!
// create an empty Annotation just with the given text
        Annotation document = new Annotation(text);
// run all Annotators on this text
        pipeline.annotate(document);
    }
}
