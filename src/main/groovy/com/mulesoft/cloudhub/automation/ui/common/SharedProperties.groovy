package com.mulesoft.cloudhub.automation.ui.common

import ch.common.Settings

class SharedProperties {

    static baseUrl = resolveProp(System.getProperty('geb.build.baseUrl'), "https://anypoint-ch-dev.mulesoft.com/cloudhub/")
    static env = (baseUrl.contains('-ch-qa'))?'qa2':(baseUrl.contains('-ch-dev'))?'dev':(baseUrl.contains('-ch-stg'))?'stg':'';

    static DEFFAULT_ACCOUNT = Settings.accounts.find{ it.user == 'ion-automation'}

    static localUrl = (System.getProperty('local') != null)?'http://localhost:9000/':'';

    static private SUITE = resolveProp(System.getProperty('suite'),'sanity')

    static public boolean runForSuite(def suiteArray){

        def ignoreIt = true;

        for ( suite in suiteArray ) {
            if(suite == SUITE){
                ignoreIt = false
            }
        }

        return ignoreIt

    }

    static private MuleVersion = getMuleVersion(resolveProp(System.getProperty('muleVersion'),'3.5.1'))

    static public String getMuleVersion(String desiredMuleVersion) {

        def muleV =  Settings.muleVersions.find {
            it.name.contains(desiredMuleVersion)  && it.ci == 0 && it.enabled == 1
        }

        if (muleV != null){
            return muleV.version
        }

        return ''
    }

    def static private resolveProp(String toResolve, String defaultValue){
        return (toResolve != null) ? toResolve :defaultValue;
    }

}
