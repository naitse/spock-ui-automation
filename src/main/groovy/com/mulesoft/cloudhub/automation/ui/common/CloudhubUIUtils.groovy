package com.mulesoft.cloudhub.automation.ui.common

import org.openqa.selenium.remote.RemoteWebDriver

/**
 * Created by naitse on 10/6/14.
 */
class CloudhubUIUtils {

    static void stopuipoller(RemoteWebDriver driver){
        driver.executeScript("angular.element(document.body).injector().get('poller').stopAll()")
        sleep(3000)

    }

    static void reloadpage(RemoteWebDriver driver){
        driver.executeScript("window.location.reload()")
        sleep(3000)
    }


}
