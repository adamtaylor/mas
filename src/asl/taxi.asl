// Agent taxi in project MAS

/* Initial beliefs and rules */

/* Initial goals */

!getCustomer.

/* Plans */

+!getCustomer
	: not has(customer)
	<- !at(taxi,taxirank);
	   	get(customer);
	   	!at(taxi,cinema);
	   	drop(customer);
	   	!getCustomer.
	   	

	
+!at(taxi,P) : at(taxi,P) <- true.
+!at(taxi,P) : not at(taxi,P)
	<- move_towards(P);
    	!at(taxi,P).