// Agent customer in project MAS

/* Initial beliefs and rules */

/* Initial goals */

!goto(cinema).

/* Plans */

+!goto(cinema) 
	: not in(taxi)
	<- !get(taxi,cinema).

+!get(taxi,cinema) 
	: not available(taxi)
	<- .send(boss,tell,requires(customer,taxi)).
		//!get(taxi,cinema).
		
	
+!get(taxi,cinema)
	: available(taxi)
	<- .send(taxi,tell,has(customer)).
		//.send(taxi,achieve,take(cinema)).
		
+!arrive
	: at(cinema)
	<- .send(taxi,untell,has(customer)).
		//.send(taxi,achieve,getCustomer).
