package Home

import ch.CloudhubClient
import ch.rest.model.ApplicationInfo
import com.mulesoft.cloudhub.automation.ui.model.DashboardPage
import com.mulesoft.cloudhub.automation.ui.model.LogsPage
import spock.lang.Stepwise

import static com.mulesoft.cloudhub.automation.ui.common.CloudhubUIUtils.*
import com.mulesoft.cloudhub.automation.ui.model.HomePage
import com.mulesoft.cloudhub.automation.ui.model.LoginPage
import com.mulesoft.cloudhub.automation.ui.model.SettingsPage
import geb.spock.GebReportingSpec
import static com.mulesoft.cloudhub.automation.ui.common.SharedProperties.*
import spock.lang.IgnoreIf


class Home extends GebReportingSpec{

    static private ApplicationInfo validApplication = (new ApplicationInfo("ui-automation-home-page-test"))
    static private CloudhubClient client = new CloudhubClient([username: DEFFAULT_ACCOUNT.user])

    def setupSpec() {
        client.applications.delete(validApplication)
        client.applications.create(validApplication)
        to LoginPage
        login()
    }

    def cleanupSpec() {
        client.applications.delete(validApplication)
    }

    def cleanup(){
        to HomePage
    }

    @IgnoreIf({ notIn("sanity", "regression") })
    def "Application Dashboard displayed when user clicks application row"() {

        when: "User clicks Application row"
            at HomePage
            stopuipoller(browser.driver);
            clickApplicationRow(validApplication.domain)

        then: "he lands on settings page"
            at DashboardPage

    }

    @IgnoreIf({ notIn("sanity", "regression") })
    def "Application Settings displayed when user clicks settings link at application row"() {

        when: "User clicks Settings link at application row"
            at HomePage
            stopuipoller(browser.driver);
            interact {
                moveToElement(applicationRow(validApplication.domain))
            }
            clickApplicationSettingsLink(validApplication.domain)

        then: "he lands on settings page"
            at SettingsPage

    }

    @IgnoreIf({ notIn("sanity", "regression") })
    def "Application Logs displayed when user clicks Logs link at application row"() {

        when: "User clicks Settings link at application row"
            at HomePage
            stopuipoller(browser.driver);
            interact {
                moveToElement(applicationRow(validApplication.domain))
            }
            clickApplicationLogsLink(validApplication.domain)

        then: "he lands on Logs page"
            at LogsPage

    }

}
