// Agent boss in project MAS

/* Initial beliefs and rules */

all_proposals_received(CNPId) 
  :- .count(introduction(participant,_),NP) & // number of participants
     .count(propose(CNPId,_), NO) &           // number of proposes received
     .count(refuse(CNPId), NR) &              // number of refusals received
     NP = NO + NR.

id(0).

/* Initial goals */

// loop through looking for customers
//!checkForCustomers.
//!startCNP(1,takeCustomer).
!start.

/* Plans */

/* Contract Net Protocol Stuff */

// start the CNP
+!startCNP(Id,Task) 
   <- .print("Waiting participants... ",Task);
      .wait(2000);  // wait participants introduction
      +cnp_state(Id,propose);   // remember the state of the CNP
      .findall(Name,introduction(participant,Name),LP);
      .print(Id,"Sending CFP to ",LP);
      .send(LP,tell,cfp(Id,Task));
      // the deadline of the CNP is now + 4 seconds, so
      // the event +!contract(Id) is generated at that time
      .at("now +4 seconds", { +!contract(Id) }).


// receive proposal 
// if all proposal have been received, don't wait for the deadline
@r1 +propose(CNPId,_Offer)
   :  cnp_state(CNPId,propose) & all_proposals_received(CNPId)
   <- !contract(CNPId).

// receive refusals   
@r2 +refuse(CNPId) 
   :  cnp_state(CNPId,propose) & all_proposals_received(CNPId)
   <- !contract(CNPId).
   
// this plan needs to be atomic so as not to accept
// proposals or refusals while contracting
@lc1[atomic]
+!contract(CNPId)
   :  cnp_state(CNPId,propose)
   <- -+cnp_state(CNPId,contract);
      .findall(offer(O,A),propose(CNPId,O)[source(A)],L);
      .print("Offers are ",L);
      L \== []; // constraint the plan execution to at least one offer
      .min(L,offer(WOf,WAg)); // sort offers, the first is the best
      .print("Winner is ",WAg," with ",WOf);
      !announce_result(CNPId,L,WAg);
      -+cnp_state(CNPId,finished).

// nothing todo, the current phase is not 'propose'
@lc2 +!contract(_). 

-!contract(CNPId)
   <- .print("CNP ",CNPId," has failed!").

+!announce_result(_,[],_).
// announce to the winner
+!announce_result(CNPId,[offer(_,WAg)|T],WAg) 
   <- .send(WAg,tell,accept_proposal(CNPId));
      !announce_result(CNPId,T,WAg).
// announce to others
+!announce_result(CNPId,[offer(_,LAg)|T],WAg) 
   <- .send(LAg,tell,reject_proposal(CNPId));
      !announce_result(CNPId,T,WAg).

/* Customer Communication Stuff */

+!start
	: requires(C,T,L)
	<- ?requires(C,T,L);
		!startTaxiRide(L).
		
//+!start
//	: requires2(C,T,L)
//	<- ?requires2(C,T,L);
//		!startTaxiRide(L).

+!newCustomer
	<- !start.

+!startTaxiRide(L)
	: true
	<- ?id(I);
		//-id(I);
		+id(I+1);
		!startCNP(I+1,L).

//+!checkForCustomers
//	: ?requires(customer,taxi,location)
//	<- //!checkForCinemaCustomers.
//		//!checkForUniversityCustomers.
//		!checkForCustomer1;
//		!checkForCustomer2.

//+!checkForCustomer1
//	: not requires(customer1,taxi,cinema)
//	<- !checkForCustomers.
	
//+!checkForCustomer2
//	: not requires(customer2,taxi,university)
//	<- !checkForCustomers.
	
//+!checkForCustomer1
//	: requires(customer1,taxi,cinema)
//	<- !startTaxiRide(cinema).
	
//+!checkForCustomer2
//	: requires(customer2,taxi,university)
//	<- !startTaxiRide(university).
	
//+!checkForCinemaCustomers
//	: not requires(customer,taxi,cinema)
//	<- !checkForCustomers.
	
//+!checkForCinemaCustomers
//	: requires(customer,taxi,cinema)
//	<- !startTaxiRide;
//		!checkForCustomers.
	
//+!checkForUniversityCustomers
//	: not requires(customer,taxi,university)
//	<- !checkForCustomers.
	
//+!checkForUniversityCustomers
//	: requires(customer,taxi,university)
//	<- !startTaxiRide.

//+!queryTaxi2 
//	: true 
//	<- .send(taxi,askOne,has(customer),false);
//		+available(taxi);
//		.send(customer,tell,available(taxi)).
		
//+!queryTaxi
//	: available(taxi)
//	<- .send(customer,tell,available(taxi)).
	
//+!queryTaxi
//	: not available(taxi)
//	<- !queryTaxi.
	
