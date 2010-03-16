// Agent taxi in project MAS

/* Initial beliefs and rules */
// gets the price for a journey,
// a random value between 100 and 110.
price(_Service,X) :- .random(R) & X = (10*R)+5.

plays(initiator,boss). 

// initially the taxi doesn't have a customer
~has(customer).

/* Initial goals */

//!getCustomer.

/* Plans */
+plays(initiator,In)
   :  .my_name(Me)
   <- .send(In,tell,introduction(participant,Me)).

@c1 +cfp(CNPId,Task)[source(A)]
   :  plays(initiator,A) & price(Task,Offer) 
   <- +proposal(CNPId,Task,Offer); // remember my proposal
   		.print("CNPId '",CNPId,"' task ",Task," offer ",Offer,"!");
      .send(A,tell,propose(CNPId,Offer)).

@r1 +accept_proposal(CNPId)
   :  proposal(CNPId,Task,Offer)
   <- .print("My proposal '",Offer,"' won CNP ",CNPId,
             " for ",Task,"!");
      //!getCustomer;
      !at(taxi,taxirank);
      - ~has(customer);
      +has(customer);
      .send(customer,tell,available(taxi));
      !take(cinema).
      //!getCustomer.

+!getCustomer
	: not has(customer) | ~has(customer)
	<- !at(taxi,taxirank).
		//.send(boss,tell,available(taxi)).

+!take(P)
	: has(customer)
	<- !at(taxi,P).
		//.send(customer,tell,at(cinema))
		//.send(customer,achieve,arrive);
		//+ ~has(customer).
		//!getCustomer.
	
+!at(taxi,P) : at(taxi,P) <- true.
+!at(taxi,P) : not at(taxi,P)
	<- move_towards(P);
    	!at(taxi,P).