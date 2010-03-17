// Agent customer2 in project MAS

/* Initial beliefs and rules */

/* Initial goals */

!goto(university).

/* Plans */


+!goto(university) 
	: not in(taxi)
	<- !get(taxi,university).

+!get(taxi,university) 
	: not available(taxi)
	<- .wait(10000);
		.send(boss,tell,requires(customer,taxi,university));
		.send(boss,achieve,newCustomer).
		//.send(boss,untell,requires(customer,taxi,university)).
		//!get(taxi,cinema).
		
	
+!get(taxi,university)
	: available(taxi)
	<- .send(taxi,tell,has(customer)).
		//.send(taxi,achieve,take(cinema)).
		
+!arrive
	: at(university)
	<- .send(taxi,untell,has(customer)).
		//.send(taxi,achieve,getCustomer).
