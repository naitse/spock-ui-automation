package com.mulesoft.cloudhub.automation.ui.model


import geb.Page
import org.apache.log4j.Logger
import org.openqa.selenium.internal.Locatable


class HomePage extends Page {
    private static final Logger LOG = Logger.getLogger(HomePage)

    static url = "cloudhub/#/console/home"
    static at = { waitFor(15){$("button.ch-add-button").isDisplayed()} }

    /**
     * Page content elements
     */
    static content = {

        addApplicationButton {
            waitFor{ $("button.ch-add-button").isDisplayed() }
            $("button.ch-add-button")
        }

        addApplicationModal(required: false) {$(".modal-content")}

        applicationDomainInput(required: false) {
            $("input#domain")
        }

        domainValidationPopup(required: false) { $('.add-application-popover') }

        domainValidationMessages(required: false) { domainValidationPopup.find('.add-application-error') }

        cancelBtn(required: false) {
            $("button").find{
                it.text() == 'Cancel'
            }
        }

        addAppBtn(required:false) { $("#addApplication") }



        environment { $("span.environment") }

        envListTable {$("table[data-au='envList']") }

        btnEnvSwitch {$("button[data-au='btnEnvSwitch']") }
    }

    void onLoad(Page previousPage) {
        waitPageLoad();
    }

    void waitPageLoad(){
        Thread.sleep(5000)
    }

    boolean domainValidationMessageIs(int index){
        domainValidationMessages[index].find('.fa-times').size() == 1
    }

    /**
     * Selects an application on dashboard view
     * @param appName the application domain name
     */
    void clickApplicationRow(String appName) {

        $('.ch-application-domain').find {
            it.text() == appName
        }.click()

    }

    void clickApplicationSettingsLink(String appName) {

        $($('.ch-application-domain').find {
            it.text() == appName
        }).parent('.application-name').find('.ch-application-settings').click()

    }

    void clickApplicationLogsLink(String appName) {

        $($('.ch-application-domain').find {
            it.text() == appName
        }).parent('.application-name').find('.ch-application-logs').click()

    }

    def applicationRow(String appName){

        return $('.ch-application-domain').find {
            it.text() == appName
        }

    }

    void switchEnvironment(String env){
        waitFor{ envListTable.isDisplayed() }

        def envToSwitch = envListTable.find('td').find{
            it.text() == env
        }

        envToSwitch.click()

        btnEnvSwitch.click()
    }

}
