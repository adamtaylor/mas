// Agent taxi in project MAS

/* Initial beliefs and rules */

/* Initial goals */

!getCustomer.

/* Plans */

+!getCustomer
	: not has(customer) | ~has(customer)
	<- !at(taxi,taxirank);
		.send(boss,tell,available(taxi)).
	
+!take(P)
	: has(customer)
	<- !at(taxi,P);
		.send(customer,tell,at(cinema));
		.send(customer,achieve,arrive);
		+ ~has(customer);
		!getCustomer.
	
+!at(taxi,P) : at(taxi,P) <- true.
+!at(taxi,P) : not at(taxi,P)
	<- move_towards(P);
    	!at(taxi,P).