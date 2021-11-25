## Tools used

* Java https://www.java.com/en/
* Selenium https://www.selenium.dev/
* Cucumber https://cucumber.io/
* Maven https://maven.apache.org/
* Git https://git-scm.com/
* IDEA IntelliJ https://www.jetbrains.com/idea/
* JUnit https://junit.org/junit4/
* Jenkins https://www.jenkins.io/
* Percy https://percy.io/
* Applitools https://applitools.com/
* Galen http://galenframework.com/
* Karate https://github.com/intuit/karate
* ReportPortal.io: https://reportportal.io/


<br/><br/>

## Prerequisites

* Install and setup JDK java 8,
* Install and setup Apache Maven
* Install Git
* Install IDEA IntelliJ
* In IDEA IntelliJ 'Cucumber for java' plugin
* Google Chrome
* Jenkins ready for use (if you want to run tests via CI)
* Install ReportPortal.io (if you want to send results there)

<br/><br/>

## Installation

1.) Create a folder on your disk and clone GIT repository:
```
git clone https://github.com/maroskutschy/Jasecu.git
```
always use 'master' branch

2.) In IDEA IntelliJ click on File > Open and choose the root folder of the
project

<br/><br/>


## Usage

There are 4 ways how to run the tests:

<br/><br/>


**1.) IDEA IntelliJ - Run the tests as feature file via IDEA IntelliJ Cucumber for Java plugin**

a) Go to Run > Edit Configurations > Templates > Cucumber Java > Use classpath of module > choose module from which you want to run the tests (for example choose: general)

b) In file:
 ```
 general/src/test/java/TestRunners/TestDefaultValues.java
  
 ```
insert correct values into following variables
   ```
   public static final String DEFAULT_LINK = "LINK_TO_YOUR_APPLICATION";
   // for Facebook: 
   public static final String DEFAULT_LINK = "http://www.facebook.com";
   public static final String DEFAULT_USER = "YOUR_USER";
   public static final String DEFAULT_PASSWORD = "CORRECT_PASSWORD";
  // last variable is for running Applitools Tests 
   public static final String APPLITOOLS_API_KEY = "YOUR_APPLITOOLS_API_KEY";
   ```
also you can change DEFAULT_OPERATING_SYSTEM to: 'Windows' or 'MacOS'
and DEFAULT_BROWSER to 'Chrome' or 'Firefox'

c) open any feature file, for example:

```
general/src/test/resources/Feature_Files/Facebook/Login_Sample/Facebook_Login_Sample.feature
```
and right click on row with:
   ```
   Scenario: Successful Login to Facebook with default credentials
   ```
and click on run

**Where to see the result of Cucumber tests:**

you can see it in the bottom left part of IntelliJ: in Run Tab

<br/><br/>


**2.) IDEA IntelliJ - Run the tests as JUnit Test:**

b) In file:
 ```
 general/src/test/java/TestRunners/TestDefaultValues.java
  
 ```
insert correct values into following variables
   ```
   public static final String DEFAULT_LINK = "LINK_TO_YOUR_APPLICATION";
   // for Facebook: 
   public static final String DEFAULT_LINK = "http://www.facebook.com";
   public static final String DEFAULT_USER = "YOUR_USER";
   public static final String DEFAULT_PASSWORD = "CORRECT_PASSWORD";
  // last variable is for running Applitools Tests 
   public static final String APPLITOOLS_API_KEY = "YOUR_APPLITOOLS_API_KEY";
   ```
also you can change DEFAULT_OPERATING_SYSTEM to: 'Windows' or 'MacOS'
and DEFAULT_BROWSER to 'Chrome' or 'Firefox'

b) In IDEA IntelliJ find TestRunner java file, for example:
 ``` 
  general/src/test/java/TestRunners/TestRunnerFacebookLoginSampleReportPortalJunit.java
 ```

right click on it and choose option 'Run ...'


**Where to see the result of Cucumber tests:**


  ``` 
  In the console output of the 'Run' tab
``` 
If you run tests from different module, change the 'Facebook' module to different one

<br/><br/>

**3.) IDEA IntelliJ - Run the tests as maven build (this is also the way how it is running from Jenkins):**

a) In IDEA IntelliJ on the right side in Maven Tab, click on 'jasecu(root)' > Lifecycle > install and then right click on 'install' and choose 'modify run configuration'
and write following in command line editbox:

  ```
 clean install -DskipTests
  ``` 
update name of the configuration and save it and run it

b) In IDEA IntelliJ on the right side in Maven Tab, click on 'jasecu(root)' > Lifecycle > install and then right click on 'install' and choose 'modify run configuration'
and write following in command line editbox:

  ```
 clean install -pl :general -DskipTests
  ``` 
update name of the configuration and save it and run it

c) In IDEA IntelliJ on the right side in Maven Tab, click on 'jasecu(root)' > Lifecycle > install and then right click on 'install' and choose 'modify run configuration'
and write following in command line editbox:

  ```
 clean test -pl :general -PJUnit -Dtest=TestRunnerFacebookLoginSampleReportPortalJunit -DfailIfNoTests=false -DoperatingSystem=Windows -Dbrowser=Chrome -Dlink=http://www.facebook.com -Duser=YOUR_USER -Dpassword=YOUR_PASSWORD -f ./general
  ``` 
update name of the configuration and save it

You can change name of java runner file, OS, browser and also link


**Where to see the result of Cucumber tests:**


 ``` 
  In the console output of the 'Run' tab
``` 
If you run tests from different module, change the 'Facebook' module to different one


<br/><br/>

**4.) Jenkins**
* Install 'Cucumber reports' Jenkins plugin
* Create new Jenkins job (freestyle project)
* Add git repository and credentials
* In 'Build' section > add 3 build steps same as in point 3:

 1st: clean install -DskipTests

 2nd: clean install -pl :general -DskipTests
 
3rd: for example:

 clean test -pl :general -PJUnit -Dtest=TestRunnerFacebookLoginSampleReportPortalJunit -DfailIfNoTests=false -DoperatingSystem=Windows -Dbrowser=Chrome -Dlink=http://www.facebook.com -Duser=YOUR_USER -Dpassword=YOUR_PASSWORD -f ./general

* Add post build step:
  Cucumber reports
  click on advanced > File Include Pattern = **/*.json


**Where to see the result of Cucumber tests:**

after running the tests, following file is generated:

  ```
  general\target\cucumber.json
  ```

And Jenkins 'cucumber reports' plugin is parsing it and is generating awesome Cucumber report available on
Jenkins for each build

## Notes related to other Tools

**1.) Applitools**

You need to create free account at: https://applitools.com/
and then use your Applitools API key:

a) When running via IntelliJ Cucumber for java plugin:
In file:

  ```
    general/src/test/java/TestRunners/TestDefaultValues.java
  ```

insert correct values into following variables
```
public static final String APPLITOOLS_API_KEY = "YOUR_APPLITOOLS_API_KEY";
```

b) when running via Maven command:

   ```
   clean test -PJUnit -pl :general -Dtest=TestRunnerFacebookApplitoolsReportPortalJunit -DfailIfNoTests=false -DoperatingSystem=Windows -Dbrowser=Chrome -Dlink=http://www.facebook.com -Duser=YOUR_USER -Dpassword=YOUR_PASSWORD -DapplitoolsApiKey=YOUR_APPLITOOLS_API_KEY -f ./general
   ``` 

**2.) Percy**

a) When running via IntelliJ

     ONE TIME SETUP:
     Create fre Percy Account at:  https://percy.io/
     Install NodeJS
     in cmd/terminal > switch to root of naf framework project
     perform command: npm init
     perform command: npm install --save-dev @percy/agent

     BEFORE EACH RUN:
     in cmd/terminal > switch to root of jasecu framework project
     mac/linux: perform command: export PERCY_TOKEN=YOUR_PERCY_TOKEN
     windows: perform command: set PERCY_TOKEN=YOUR_PERCY_TOKEN
     perform command: npx percy exec -- mvn install -pl :Facebook -PTestNG -DTestSuite=FacebookPercyParameters.xml -DoperatingSystem=Windows -Dbrowser=Chrome -Dlink=http://www.facebook.com -Duser=YOUR_USER -Dpassword=YOUR_PASSWORD -f ./Facebook

b) When running via Jenkins
make sure all 'One Time Setup' steps from a) are done in jobs worspace and
use same command as in 'BEFORE EACH RUN'

**3.) Galen**

Galen reports can be found at:
   ```
  galen\target\Galen\
 ```
to see them correctly with CSS on Jenkins, you need to do following:
1.	Manage Jenkins->
2.	Manage Nodes->
3.	Click settings(gear icon)->
4.	click Script console on left and type in the following command:
      System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")
      and Press Run. If you see the output as 'Result:' below "Result" header then the protection disabled.
      Re-Run your build and you can see that the new HTML files archived will have the CSS enabled.

**4.) Karate**

Karate reports can be found at:
   ```
  karate\target\surefire-reports\karate-summary.html
 ```
to see them correctly with CSS on Jenkins, you need to do following:
1.	Manage Jenkins->
2.	Manage Nodes->
3.	Click settings(gear icon)->
4.	click Script console on left and type in the following command:
      System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")
      and Press Run. If you see the output as 'Result:' below "Result" header then the protection disabled.
      Re-Run your build and you can see that the new HTML files archived will have the CSS enabled.

to run Karate tests:

  ```
 clean test -pl :karate -PJUnit -Dtest=TestRunnerKarateReportPortal -DfailIfNoTests=false -f ./karate
  ```
In the file:

 ```
 karate/src/test/java/TestRunners/TestRunnerKarateReportPortal.java
```
is specified path for feature files


**5.) Reportportal.io**

Install Reportportal.io application according to guide on https://reportportal.io/docs/Installation-steps

after installation and login got to your user profile, click on java tab 
and copy:

rp.endpoint = YOUR REPORT PORTAL'S LINK
rp.uuid = YOUR REPORT PORTAL'S UUID
rp.launch = YOUR REPORT PORTAL'S LAUNCH
rp.project = YOUR REPORT PORTAL'S PROJECT

into following files:

karate/src/test/java/reportportal.properties

general/src/test/java/reportportal.properties

general/src/test/resources/reportportal.properties

Only results from 'galen' module do not support sending results to Reportportal.io.
Results from 'general' and 'karate' modules support it.
If you do not use Reportportal.io, no problem, no results will be sent,
you will just see messages in the logs informing that it can't send results, but 
there will be no failures related to it.





# Video Tutorials

**SEE video tutorial on official jasecu site:**

 [http://jasecu.mablog.eu/blog/](http://jasecu.mablog.eu/blog/)


# Social networks

**Instagram:**

[https://www.instagram.com/jasecu_by_maros/](https://www.instagram.com/jasecu_by_maros/)

**Facebook:**

[https://www.facebook.com/profile.php?id=100071928812026](https://www.facebook.com/profile.php?id=100071928812026)

**YouTube:**

[https://www.youtube.com/channel/UCaUnf-N_A6AomWGq76qVV5g](https://www.youtube.com/channel/UCaUnf-N_A6AomWGq76qVV5g)

<br/><br/>



