GOAL IN CONTEXT

	As an HR advisor I want to view and employee's details so that the employee's promotion request can be supported.

SCOPE

	Company.


LEVEL

	Primary Task


PRECONDITIONS

	We know the roles, Employee is on database and has a position that allows promotion


SUCCESS END CONDITION

	HR Advisor has access to employee details and employee is promoted


FAILED END CONDITION

	HR Advisor does not gain access and the promotion is never processed


PRIMARY ACTOR

	HR Advisor


TRIGGER

	A request for employee information is requested by HR


MAIN SUCESS SCENARIO

	1. HR Advisor requests access to employee details

	2. HR Advisor gains access to employee details

	3. HR Advisor extracts relevant employee information 

	4. HR Advisor ensures employee's promotion is successful


EXTENSIONS

	1. Employee's details are not on system
		1(a). Inform Finance that employee's details are not on the system
	2. HR Advisor can not gain access to employee details
		1(a). Make request to revelevant bodies to allow HR to access said information
	
SUB-VARIATIONS 

	None.



SCHEDULE DUE DATE: 

	Release 1.0
