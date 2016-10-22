# CoreNLP
* Stanford CoreNLP provides a set of natural language analysis tools. It can give the base forms of words, their parts of speech, whether they are names of companies, people, etc., normalize dates, times, and numeric quantities, and mark up the structure of sentences in terms of phrases and word dependencies, indicate which noun phrases refer to the same entities, indicate sentiment, extract open-class relations between mentions, etc.
[More Info](http://stanfordnlp.github.io/CoreNLP/index.html)
#JDK1.8

#Example
     sentence : 
     President Obama will meet with Congressional leaders on Friday, and House Republicans summoned lawmakers back for a Sunday session, in a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts scheduled to hit next week.
     
     extraction : 
     (House Republicans;summon back;lawmaker)
     (President Obama;will meet in;effort)
     (President Obama;will meet in;last-ditch effort)
     (House Republicans;summon;lawmaker)
     (President Obama;will meet with;leader)
     (President Obama;will meet with;congressional leader)
     (President Obama;will meet on;Friday)



