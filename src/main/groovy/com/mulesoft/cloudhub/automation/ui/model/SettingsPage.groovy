package com.mulesoft.cloudhub.automation.ui.model

import geb.Page
import org.apache.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebElement

class SettingsPage extends Page {
    private static final Logger LOG = Logger.getLogger(SettingsPage)

    static at = { title == "CloudHub" }

    /**
     * Page content elements
     */
    static content = {

        applicationSettings { $('.application-settings')}

        applyBtn { $("button[data-au='apply']") }

        muleVersion { $("select[name='muleVersion']") }

        propertiesGroup { $("div[data-au='properties']") }
        propertiesExpander { waitFor{
                    $("div[data-au='properties']").find("a span").isDisplayed()
                }
            $("div[data-au='properties']").find("a span")
        }

        advancedGroup { $("div[data-au='advanced']") }
        advancedExpander { waitFor{
            $("div[data-au='advanced']").find("a span").isDisplayed()
        }
            $("div[data-au='advanced']").find("a span")
        }

        muleWorkersBtn { $("div.muleWorkers button") }

        muleWorkersList { $("div.muleWorkers ul li") }

        regionsBtn { $("div.region button") }
        regionsList { $("div.region ul li") }

        fileUploadProgress { $(".file-upload-progress .progress-bar") }

        deployBtn { $("button[data-au='deployBtn']") }

        deleteBtn { $("button#deleteApplicationModalBtn") }

        newPropKey { $("#ch-new-property-key") }
        newPropValue { $("#ch-new-property-value") }

        btnPropList(required: false) { $("button[data-au='btnPropList']") }
        btnPropText(required: false) { $("button[data-au='btnPropText']") }

        btnGetFromSbx{ $(".get-from-sandbox") }

        btnApplySandbox {  $("button[data-au='btnApplySandbox']") }
    }

    void onLoad(Page previousPage) {
        waitPageLoad();
    }

    void waitPageLoad(){
        waitFor(5){
            applicationSettings.isDisplayed()
        }
    }

    public void selectMuleVersion(){
        def list = getMuleVersionList()
        String currentVersion = $("div.mule-version button").text()

        def ver = list.find{
            it.text() != currentVersion
        }

        if(ver != null ){
            ver.click()
        }else{
            throw new Exception("Version not found")
        }
    }

    public void selectMuleVersion(String version){
        def list = getMuleVersionList()

        def ver = list.find{
            it.text() == version
        }

        if(ver != null ){
            ver.click()
        }else{
            throw new Exception("Version ${version} not found")
        }
    }

    public def getMuleVersionList(){
        waitFor{$("div.mule-version button").isDisplayed()}
        $("div.mule-version button").click()

        $("div.mule-version li a")
    }

    public void uploadFile(String file){
        String filePath = getClass().getResource(file).getPath()

        //getDriver().executeScript("\$('#fileChooser').show()")
        WebElement element = getDriver().findElement(By.id("fileChooser"));
        LocalFileDetector detector = new LocalFileDetector();
        File f = detector.getLocalFile(filePath);
        ((RemoteWebElement)element).setFileDetector(detector);
        $('#fileChooser') <<  f.getAbsolutePath()
    }

    def getPropRemoveBtns(){
        $(".muleicon.muleicon-remove-row").findAll {
            it.isDisplayed()
        }
    }

    void selectSandbox(String option){

        waitFor{ $("form#settingsSandboxForm button.dropdown-toggle").isDisplayed() }

        $("form#settingsSandboxForm button.dropdown-toggle").click()

        def sbox = $("form#settingsSandboxForm ul.dropdown-menu li").find{
            it.text() == option
        }

        if(!sbox){
            throw new Exception("Sandbox ${option} not found")
        }

        sbox.click()

        Thread.sleep(500)
    }

    void selectApplicationOnSandbox(String appName){
        waitFor{ $("form#settingsSandboxForm table").isDisplayed() }

        def appToPromote = $("form#settingsSandboxForm table td.domain").find{
            it.text() == appName
        }

        if(!appToPromote){
            throw new Exception("Application ${appName} not found")
        }

        appToPromote.click()
    }

    String convertToPath(String app) {
        getDriver().navigate().refresh()

        "#/console/applications/${app}/settings"
    }
}