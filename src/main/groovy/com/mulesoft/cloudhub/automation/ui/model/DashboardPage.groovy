package com.mulesoft.cloudhub.automation.ui.model

import geb.Page
import org.apache.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebElement

class DashboardPage extends Page {
    private static final Logger LOG = Logger.getLogger(DashboardPage)


    static at = { waitFor(5){$('.dashboard').isDisplayed()}}

    /**
     * Page content elements
     */
    static content = {


    }

    String convertToPath(String app) {
        getDriver().navigate().refresh()

        "#/console/applications/${app}/dashboard"
    }
}