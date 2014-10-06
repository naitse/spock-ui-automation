import com.mulesoft.cloudhub.automation.ui.common.SharedProperties
import com.mulesoft.cloudhub.automation.ui.model.HomePage
import com.mulesoft.cloudhub.automation.ui.model.LoginPage
import geb.spock.GebReportingSpec
import spock.lang.IgnoreIf

class AnypointLoginSpec extends GebReportingSpec {

    @IgnoreIf({ SharedProperties.runForSuite(["cs"]) })
    def "Unable to login with unvalid cedentials"() {
        when: "User goes to Anypoint Login Page"
        to LoginPage

        and: "logs in using invalid credentials"
        login([username:"somefakeuser", password:"somefakepassword"]);
        Thread.sleep(2000)

        then: "an valid error message is displayed"
        messageBox.text().trim() == "Your credentials are not valid or your session is expired."

    }

    @IgnoreIf({ SharedProperties.runForSuite(["cs"]) })
    def "Can get to Cloudhub Home Page"() {
        when: "User goes to Anypoint Login Page"
        to LoginPage

        and: "logs in using valid credentials"
        login();

        then: "Cloudhub home page is displayed"
        at HomePage

    }
}

//        interact {
//            moveToElement(manualsMenu.toggle)
//        }