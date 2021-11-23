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

2021-11-23:
Galen maven this works:
clean install -pl :general -DskipTests -f pom.xml
clean test -PJUnit -am -pl :galen -Dtest=TestRunnerFacebookGalenJunit -Dmaven.test.failure.ignore=true -DoperatingSystem=Windows -Dbrowser=Chrome -Dlink=http://www.facebook.com -Duser=jasecuframework@gmail.com -Dpassword=XXX -f ./galen


TODO 2:
- Galen files copied to Galen module = running ok from ff, as Junit,
  but problem to run as maven build = why ?
  once fixed, then: + test on Jenkins
(do it in branch: modules_karate_3)
- test if other FF works ok from general + karate
- remove files from general (copied to galen)  
- remove profiles

TODO: module for karate (with + without reportportal = old and new way)
= now in special branch - karate_reportportal (revreted for master)
= get rid of testng 1st dependency

= and create separate module for Galen


