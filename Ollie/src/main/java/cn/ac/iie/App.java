package cn.ac.iie;

import java.io.File;
import java.net.MalformedURLException;


import edu.knowitall.ollie.Ollie;
import edu.knowitall.ollie.OllieExtraction;
import edu.knowitall.ollie.OllieExtractionInstance;
import edu.knowitall.tool.parse.MaltParser;
import edu.knowitall.tool.parse.graph.DependencyGraph;

/**
 * Hello world!
 *
 */
public class App 
{
	// the extractor itself
    private Ollie ollie;

    // the parser--a step required before the extractor
    private MaltParser maltParser;

    // the path of the malt parser model file
    private static final String MALT_PARSER_FILENAME = "C://engmalt.linear-1.7.mco";

    public App() throws MalformedURLException {
    	 // initialize MaltParser
        maltParser = new MaltParser(new File(MALT_PARSER_FILENAME));

        // initialize Ollie
        ollie = new Ollie();
    }

    /**
     * Gets Ollie extractions from a single sentence.
     * @param sentence
     * @return the set of ollie extractions
     */
    public Iterable<OllieExtractionInstance> extract(String sentence) {
        // parse the sentence
        DependencyGraph graph = maltParser.dependencyGraph(sentence);

        // run Ollie over the sentence and convert to a Java collection
        Iterable<OllieExtractionInstance> extrs = scala.collection.JavaConversions.asJavaIterable(ollie.extract(graph));
        return extrs;
    }

    public static void main(String args[]) throws MalformedURLException {
        //System.out.println(OllieTest.class.getResource("/logback.xml"));
        // initialize
    	App ollieWrapper = new App();

        // extract from a single sentence.
        String sentence = "President Obama will meet with Congressional leaders on Friday, and House Republicans summoned lawmakers back for a Sunday session, in a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts scheduled to hit next week.";
        Iterable<OllieExtractionInstance> extrs = ollieWrapper.extract(sentence);

        // print the extractions.
        for (OllieExtractionInstance inst : extrs) {
            OllieExtraction extr = inst.extr();
            System.out.println(extr);
            //System.out.println(extr.arg1().text()+"\t"+extr.rel().text()+"\t"+extr.arg2().text());
        }
    }
}
