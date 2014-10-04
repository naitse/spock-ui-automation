package com.mulesoft.cloudhub.automation.ui.model


import geb.Page
import org.apache.log4j.Logger
import org.openqa.selenium.internal.Locatable


class HomePage extends Page {
    private static final Logger LOG = Logger.getLogger(HomePage)

    static url = "#/console/home"
    static at = { title == "Anypoint Platform Organization Management" }

    /**
     * Page content elements
     */
    static content = {
        add_application_button {
            waitFor{ $("button.ch-add-button").isDisplayed() }
            $("button.ch-add-button")
        }

        popupDomainInput(required: false) {
            $("input#domain")
        }

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
        waitFor(5){
            add_application_button.isDisplayed()
        }
    }

    /**
     * Selects an application on dashboard view
     * @param appName the application domain name
     */
    void goToApplication(String appName) {

        getBrowser().go("#/console/applications/${appName}/dashboard")

    }

    /**
     * make create app button viewable on viewport
     *
     */
    void makeButtonViewable(){

        getDriver().manage().window().maximize();
        ((Locatable)createAppBtn.firstElement()).getLocationOnScreenOnceScrolledIntoView();

    }

    void createAppBtn_Click_js(){
        getDriver().executeScript("\$('#modal-submit.btn.btn-primary').click()")
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
