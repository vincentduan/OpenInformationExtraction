# OLLIE
* Ollie is software that automatically identifies and extracts binary relationships from English sentences. Ollie is designed for information extraction, where the target relations cannot be specified in advance and speed is important.
* Open IE 3.0 used to be known as [Ollie](http://knowitall.github.io/ollie/).

#Example
     sentence : 
     President Obama will meet with Congressional leaders on Friday, and House Republicans summoned lawmakers back for a Sunday session, in a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts scheduled to hit next week.
     
     extraction : 
     (House Republicans; will summoned lawmakers back for; a Sunday session)
	 (House Republicans; will summoned lawmakers back in; a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts)
	 (President Obama; will summoned lawmakers back for; a Sunday session)
	 (President Obama; will summoned lawmakers back in; a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts)
	 (House Republicans; will summoned back; lawmakers)
	 (President Obama; will summoned back; lawmakers)
	 (House Republicans; will meet on; Friday)
	 (House Republicans; will meet with; Congressional leaders)
	 (President Obama; will meet on; Friday)
	 (President Obama; will meet with; Congressional leaders)
	 (lawmakers; will be summoned back for; a Sunday session)
	 (lawmakers; will be summoned back in; a last-ditch effort to avert a fiscal crisis brought on by automatic tax increases and spending cuts)
	 (House Republicans; will meet in; Friday)
	 (President Obama; will meet in; Friday)



