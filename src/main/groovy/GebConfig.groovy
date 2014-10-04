

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebDriver

//baseUrl = 'https://anypoint-ch-dev.mulesoft.com/cloudhub/'
cacheDriverPerThread = true
quitCacheDriverOnShutdown = true
autoClearCookies = true

driver = {
    def dr = new FirefoxDriver()
    dr.manage().window().maximize()
    return dr
}

waiting {
    timeout = 20

    presets {
        slow {
            timeout = 360
            retryInterval = 15
        }
        fast {
            timeout = 15
            retryInterval = 2
        }
        quick {
            timeout = 5
        }
    }
}
reportOnTestFailureOnly = true
reportsDir = "src/test/reports"

String ChromeLocalRemoteDriverURL = "http://localhost:9515"
String remoteURL = "http://muleion:27696dec-0aa5-429c-ae5e-d9c11022fc5a@ondemand.saucelabs.com:80/wd/hub"

environments {
    'remote-ie' {
        driver = {
            DesiredCapabilities capabillities =	DesiredCapabilities.internetExplorer()
            capabillities.setCapability("platform", Platform.XP)
            capabillities.setCapability("selenium-version", "2.23.0")
            capabillities.setCapability("name", "ui-automation")
            capabillities.setCapability("version", "8");
            capabillities.setCapability("name", "ch-ui-automation")
            capabillities.setCapability("command-timeout", 300)
            capabillities.setCapability("idle-timeout", 300)
            capabillities.setCapability("screen-resolution", "1280x1024")
            capabillities.setCapability("record-screenshots", false)
            capabillities.setCapability("build", getBuildNumber())
            new RemoteWebDriver(new URL(remoteURL), capabillities)
        }
    }

    'remote-ie-9' {
        driver = {
            DesiredCapabilities capabillities =	DesiredCapabilities.internetExplorer()
            capabillities.setCapability("platform", Platform.VISTA)
            capabillities.setCapability("selenium-version", "2.23.0")
            capabillities.setCapability("name", "ui-automation")
            capabillities.setCapability("version", "9");
            capabillities.setCapability("name", "ch-ui-automation")
            capabillities.setCapability("command-timeout", 300)
            capabillities.setCapability("idle-timeout", 300)
            capabillities.setCapability("screen-resolution", "1280x1024")
            capabillities.setCapability("record-screenshots", false)
            capabillities.setCapability("build", getBuildNumber())
            new RemoteWebDriver(new URL(remoteURL), capabillities)
        }
    }

    'local-chrome'{
        driver = {
            DesiredCapabilities capabillities = DesiredCapabilities.chrome()
            capabillities.setCapability("build", getBuildNumber())
            new RemoteWebDriver(new URL(ChromeLocalRemoteDriverURL), capabillities)
        }
    }

    'remote-chrome'{
        driver = {
            DesiredCapabilities capabillities = DesiredCapabilities.chrome()
            capabillities.setCapability("platform", Platform.XP)
            capabillities.setCapability("selenium-version", "2.41.0")
            capabillities.setCapability("name", "ch-ui-automation")
            capabillities.setCapability("command-timeout", 300)
            capabillities.setCapability("idle-timeout", 300)
            capabillities.setCapability("screen-resolution", "1280x1024")
            capabillities.setCapability("record-screenshots", false)
            capabillities.setCapability("build", getBuildNumber())

            def drvr = new RemoteWebDriver(new URL(remoteURL), capabillities)
            drvr.setFileDetector(new LocalFileDetector())
            drvr
        }
    }

    'remote-ff'{
        driver = {
            DesiredCapabilities capabillities = DesiredCapabilities.firefox()
            capabillities.setCapability("version", "11")
            capabillities.setCapability("platform", Platform.XP)
            capabillities.setCapability("selenium-version", "2.23.0")
            capabillities.setCapability("name", "ch-ui-automation")
            capabillities.setCapability("command-timeout", 300)
            capabillities.setCapability("idle-timeout", 300)
            capabillities.setCapability("screen-resolution", "1280x1024")
            capabillities.setCapability("record-screenshots", false)
            capabillities.setCapability("build", getBuildNumber())

            def drvr = new RemoteWebDriver(new URL(remoteURL), capabillities)
            drvr.setFileDetector(new LocalFileDetector())
            drvr
        }
    }
}
def getBuildNumber(){
    //def buildNumber = (System.properties.getProperty('geb.build.baseUrl') + '_version_/').toURL().getText()
    // buildNumber.substring(buildNumber.indexOf('<h1') + 13 , buildNumber.indexOf('</h1'))
    "dd"
}
