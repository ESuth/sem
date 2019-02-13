USE CASE:

As an department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.

Scope

Company

Level

Primary

Preconditions

We know the role. Database contains all current department's employee salary data.


Success End Condition

The department manager is able to produce a salary report of all employees in his given department


Failed End Condition

No report is produced

Primary Actor

Department Manager

Trigger

A request for finance information from the department manager is sent to HR

MAIN SUCCESS SCENARIO

	1. Finance requests salary information for a given department
	2. HR advisor captures name of the department to get salary information for
	3. HR advisor extracts salary information from all employees in the department
	4. HR advisor provides report to department manager.

EXTENSIONS

	1. HR pulls wrong department's salary information
		(a). Inform HR that salary information is incorrect


SUB-VARIATIONS

None

SCHEDULE

DUE DATE: Release 1.0
