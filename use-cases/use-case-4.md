Use Case:
---------
Program that allows users to generate population reports based on geographical location
 
 
Context of Use:
---------------
User enters a geographical location, gets a generated report with information such as, code, name, continent, region, ect
 
 
Level
-----
Summary
 
 
Preconditions
-------------
Working SQL database with relevant informaiton
 
 
Success End Condition
---------------------
User has reports, correct data has been retrieved and no errors have occurred
 
 
Failed End Protection
---------------------
User is informed that the program has not functioned correctly and allows them to try another selection, or to exit
 
 
Primary Actor
-------------
User
 
 
Main Success Scenario
---------------------
    1. Requester, user initiates a report
    2. Retrieval, data is retrieved from SQL Database
    3. Display, data is displayed for the user in a report
    4. Selection, user is asked to either make another selection, or exit the program
    5. Exit, user exits the program without error
   
   
Extensions
----------
1(a). User submitted data is incorrect
    1(a)1. User is asked to reinsert data, is told that data previously entered is incorrect, remind them of appropriate syntax
    
    1(a)2. Ask the user if they would like to go back to a previous section of the program
    
    1(a)3. Allow the user to exit the software at any time
    
    1(a)4. User enters text that is not properly processed by the program, inform the user of appropriate syntax
   
2(a). Program does not function correctly
    2(a)1. User encounters a bug that causes an error/crash, careful evaluation of code, if unconclusive ask group for advice.
    
    2(a)2. User enters text that is not properly processed by the program, inform the user of appropriate syntax
    
    2(a)3. Data retrieved from the database is incorrect, search database to check if data searched is incorrect
   
3(a). Program is difficult to use
    3(a)1. Program is cluttered, and provides unecessary information
    
    3(a)2. Program has unecessary menus/confusing menus
    
    3(a)3. Program is hard to navigate (unclear instructions/directions for the user to follow)
   
 
Channel To Primary Actor
-------------------------
Software, written in Java using IntelliJ.
   
   
Open Issues
-----------
    How will we let the user navigate through our program
    How will we display the information
    How do we handle errors within the program
