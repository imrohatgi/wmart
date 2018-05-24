# Ticket Service
## How to Build the Ticket Service Application:

  1. Clone wmart app to your local using following command
      git clone https://github.com/imrohatgi/wmart.git
  2. change directory folder to wmart/walmart
  3. run command on your console "mvn clean install"
    Please wait appx 30 seconds to finish the build as its running test too or you can skip the tests using follwoing command  for quick build - "mvn clean install -DskipTests"

## How to run the Ticket Service Applicaiton:

  1. Run the command "java -cp target/walmart-0.0.1-SNAPSHOT.jar com.org.wmdemo.MainBookTickets".
  
      It will open a Menu screen with 4 option. and few more details: it will look like this below.    
  ```
  Please note at any stage you can get to main menu @email:'cancel' , @HoldId/@Seats:0 
  ---------------------------------------------------------------------
  Total Available Seats now:288
  Main Menu:: 
  1-New booking
  2-Reserve booking
  3-SummaryView
  0-Exit
  ``` 
 - 1 to create a new booking which will hold the seats for you but you not reserved yet.
 - 2 to confirm the existing booking which you might have hold it. 
 - 3 to provide summary view of available seats, holding seats and reserved seats for the session.
 - 0 to exit from the application.

## Assumption:
  - Assuming that only application can alot the seats and user can not choose it; though applicaiton can be enhance in a way that user can choose which seats he wants one he select the summary view; user can see available seats and choose accordingly. but for now its out of scope from this app.
  - Assuming 30 Seconds is the time in which holded reservation will expire. though we can change it in Constants.java file an we could configure it in properties too (out of scope)
