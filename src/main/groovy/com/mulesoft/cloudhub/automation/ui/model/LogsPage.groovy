package com.mulesoft.cloudhub.automation.ui.model

import geb.Page
import org.apache.log4j.Logger

class LogsPage extends Page {
    private static final Logger LOG = Logger.getLogger(LogsPage)


    static at = { waitFor(5){$('.logs').isDisplayed()}}

    /**
     * Page content elements
     */
    static content = {


    }

    String convertToPath(String app) {
        getDriver().navigate().refresh()

        "cloudhub/#/console/applications/${app}/logs/live"
    }
}