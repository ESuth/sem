Goal in Context

As an HR advisor I want to produce a report on the salary of employees in a department so that I can support financial reporting of the organisation.

Scope

Company.

Level

Primary task.

Preconditions

We know the role. Database contains all current department's employee salary data.

Success End Condition

A report is available for specific departments to HR to provide to finance

Failed End Condition

No report is produced

Primary Actor

HR Advisor

Trigger

A request for finance information from a specific department is sent to HR

MAIN SUCCESS SCENARIO

	1. Finance requests salary information for a given department
	2. HR advisor captures name of the department and role to get salary information for
	3. HR advisor extracts salary information from all employees in the department
	4. HR advisor provides report to finance.
	
EXTENSIONS

	1. Department does not exist
		i. HR advisor informs finance no department exists
	2. Salary information is incorrect
		i. HR advisor informs finance that salary information is incorrect
		
Sub-Variations

None

SCHEDULE

DUE DATE: Release 1.0
