// Agent boss in project MAS

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start 
	: true 
	<- .send(taxi,askOne,has(customer),available(taxi)).
