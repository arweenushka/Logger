# Logger for Adidas aLive OnlineTime
This script will log time for you. First it will log fridays like a short day and then log mondays-thursdays

Should be run each month

# Additional feature that was added to the project
* Cross browser execution. Tests can be executed using Chrome, Safari, Firefox and Edge. To be able to run tests on each of them,
  browsers should be installed on your machine. By default, Chrome browser will be used.

# How to execute script

Open cmd in the project folder and run the command 

    > mvn clean test -DsuiteXMLFile=Logger.xml

To run tests on other browser:

    > mvn clean test -Dbrowser=safari

 