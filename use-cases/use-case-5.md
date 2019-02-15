Goal in Context

As an HR advisor I want to add a new employee's details so that I can ensure the new employee is paid.


Scope

Company


Level

Primary Task


Preconditions

	We know the employee's name/details, Database containing current employee salary data


Success End Condition

	Employee is added to database and the employee is paid


Failed End Condition

	Employee is not added and not paid


Primary Actor

	HR Advisor


Trigger

	A request is sent to finance to set up employee's detail on database


MAIN SUCCESS SCENARIO

	1. HR requests employees details be added to database
	2. Finance submits employees details to database
	3. HR comfirms that details have been added
	4. Employee gets paid


EXTENSIONS

	1. Employee's details are added incorrectly
		(a). Inform finance that details have no been added correctly
	2. Employee is added but not paid
		(a). Inform finance that employee has not been paid
		
SUB-VARIATIONS

	None.


SCHEDULE

	DUE DATE: Release 1.0
	