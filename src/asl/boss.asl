// Agent boss in project MAS

/* Initial beliefs and rules */

/* Initial goals */

!queryTaxi.

/* Plans */

+!queryTaxi2 
	: true 
	<- .send(taxi,askOne,has(customer),false);
		+available(taxi);
		.send(customer,tell,available(taxi)).
		
+!queryTaxi
	: available(taxi)
	<- .send(customer,tell,available(taxi)).
	
+!queryTaxi
	: not available(taxi)
	<- !queryTaxi.
	
