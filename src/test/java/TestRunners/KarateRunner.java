package TestRunners;


import com.intuit.karate.junit5.Karate;

class KarateRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run().feature("src/test/resources/Feature_Files/API");
    }

}