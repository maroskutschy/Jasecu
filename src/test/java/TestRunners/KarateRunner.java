package TestRunners;


import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

@RunWith(Karate.class)
@KarateOptions (features = "src/test/resources/Feature_Files/API")
class KarateRunner {

}