package com.mulesoft.cloudhub.automation.ui.common

import ch.common.Settings

class SharedProperties {

    /**** PUBLIC ****/

    static BASEURL = resolveProp(System.getProperty('geb.build.baseUrl'), "https://anypoint-ch-dev.mulesoft.com/")

    static ENV = resolveProp(System.getProperty('geb.env'), "dev")

    static DEFFAULT_ACCOUNT = Settings.accounts.find{ it.user == 'ion-automation'}

    static public boolean notIn(String... suiteArray){
        return !suiteArray.contains(SUITE);
    }

    static public String getMuleVersion(String desiredMuleVersion) {

        def muleV =  Settings.muleVersions.find {
            it.name.contains(desiredMuleVersion)  && it.ci == 0 && it.enabled == 1
        }

        if (muleV != null){
            return muleV.version
        }

        return ''
    }

    /********/

    /**** PRIVATE ****/

    static private SUITE = resolveProp(System.getProperty('suite'),'sanity')
    static private MuleVersion = getMuleVersion(resolveProp(System.getProperty('muleVersion'),'3.5.1'))

    def static private resolveProp(String toResolve, String defaultValue){
        return (toResolve != null) ? toResolve :defaultValue;
    }

    /********/

}
