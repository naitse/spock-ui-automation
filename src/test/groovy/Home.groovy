import ch.CloudhubClient
import ch.rest.model.ApplicationInfo
import com.mulesoft.cloudhub.automation.ui.model.HomePage
import com.mulesoft.cloudhub.automation.ui.model.LoginPage
import com.mulesoft.cloudhub.automation.ui.model.SettingsPage
import geb.spock.GebReportingSpec
import com.mulesoft.cloudhub.automation.ui.common.SharedProperties
import spock.lang.Stepwise

/**
 * Created by naitse on 10/4/14.
 */
@Stepwise
class Home extends GebReportingSpec{

    static private ApplicationInfo validApplication = (new ApplicationInfo("ui-automation-add-application-modal-test"))
    static private CloudhubClient client = new CloudhubClient([username: SharedProperties.DEFFAULT_ACCOUNT.user])

    def setupSpec() {
        client.applications.delete(validApplication)
        to LoginPage
        login()
    }

    def cleanupSpec() {
        client.applications.delete(validApplication)
    }

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

    def "User can not create application using existing an application name"(){
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
        Thread.sleep(5000)
        !addAppBtn.isEnabled()

        and: "a popover shows corresponding error"
        domainValidationMessageIs(3)

    }

}
