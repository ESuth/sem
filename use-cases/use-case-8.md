GOAL IN CONTEXT 

	As an HR advisor I want to delete an employee's details so that the company is compliant with data retention legislation.
	

SCOPE

	Company
	

PRECONDITIONS

	Database with relevant information about employee's details, System in place to delete employee information.


SUCESS END CONDITIONS

	Relevant employee details are deleted from system, the company is in compliance with the data protection act.
	

FAILURE END CONDITION

	Employee details are no deleted and the company is violating data retention legislation
	

Primary Actor

	HR Advisor
	

TRIGGER

	A request to delete employee information is sent to HR
	

MAIN SUCCESS SCENARIO

	1. HR Advisor requests an employees information
	
	2. Deletes appropriate information
	
	3. Saves changes
	
	4. Employees details are deleted and are no in compliance with data retention legislation
	
	
EXTENSIONS

	1. Employee details are not deleted successfully.
		1(a) Inform relevant bodies that information is still present on database
		
	2. Wrong information has been deleted
		2(a) Inform revelant bodies that information that should not have been deleted has been deleted.
		
		
SUB-VARIATIONS

	None.


SCHEDULE

	DUE DATE: Release 1.0
