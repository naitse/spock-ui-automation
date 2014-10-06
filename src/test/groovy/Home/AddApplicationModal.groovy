package Home

import ch.CloudhubClient
import ch.rest.model.ApplicationInfo
import com.mulesoft.cloudhub.automation.ui.model.HomePage
import com.mulesoft.cloudhub.automation.ui.model.LoginPage
import com.mulesoft.cloudhub.automation.ui.model.SettingsPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import spock.lang.IgnoreIf
import spock.lang.Stepwise

import static com.mulesoft.cloudhub.automation.ui.common.SharedProperties.*

/**
 * Created by naitse on 10/4/14.
 */
@Stepwise
class AddApplicationModal extends GebReportingSpec{

    static private ApplicationInfo validApplication = (new ApplicationInfo("ui-automation-add-application-modal-test"))
    static private CloudhubClient client = new CloudhubClient([username: DEFFAULT_ACCOUNT.user])

    def setupSpec() {
        client.applications.delete(validApplication)
        to LoginPage
        login()
    }

    def cleanupSpec() {
        client.applications.delete(validApplication)
    }

    @IgnoreIf({ notIn("sanity", "regression") })
    def "User can create application"() {
        when: "User clicks Add Application Button"
        at HomePage
        addApplicationButton.click()

        and: "modal opens"
        addApplicationModal.isDisplayed()

        then: "User can fill application name"
        applicationDomainInput << validApplication.domain
        applicationDomainInput.value() == validApplication.domain

        when: "User click Add Application button"
        waitFor(5){
            addAppBtn.isEnabled()
        }
        addAppBtn.click()

        then: "user lands on settings page of created application"
        at SettingsPage
    }

    @IgnoreIf({ notIn("sanity", "regression") })
    def "User can not create application using existing application name"(){
        to HomePage

        when: "User clicks Add Application Button"
        at HomePage
        addApplicationButton.click()

        and: "modal opens"
        addApplicationModal.isDisplayed()

        and: "User fills existing application name"
        applicationDomainInput << validApplication.domain
        applicationDomainInput.value() == validApplication.domain

        then: "Add Application button is disabled"
        Thread.sleep(2000)
        !addAppBtn.isEnabled()

        and: "a popover shows corresponding error"
        domainValidationMessageIs(3)

    }

    @IgnoreIf({ notIn("sanity", "regression") })
    def "User can close Add Application Modal by using ESC key"(){
        to HomePage

        when: "User clicks Add Application Button"
        at HomePage
        addApplicationButton.click()

        and: "modal opens"
        addApplicationModal.isDisplayed()

        then: "User hits ESC key"
        applicationDomainInput << Keys.ESCAPE

        expect: "Modal is closed"
        !addApplicationModal.isDisplayed()

    }

}
