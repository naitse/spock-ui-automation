package com.mulesoft.cloudhub.automation.ui.model

import geb.Page
import ch.common.Settings

class LoginPage extends Page{

    //static url = "#/login"
    //static at = { title == "Anypoint Platform Organization Management" }
    /**
     * Page content elements
     */
    static content = {
        heading(wait:20) { $("h2").text() }

        username(wait:20) { $("input#username")}
        password(wait:20) { $("input#password")}

        //loginBtn(to: HomePage) {$('#signinForm [type=submit]')}
        loginBtn(wait:20) {$('#signinForm [type=submit]')}
        messageBox(required:false) { $('div.message')}
    }

    private DEFFAULT_ACCOUNT = Settings.accounts.find{ it.user == 'ion-automation'}
    private DEFAULT_USERNAME = DEFFAULT_ACCOUNT.user
    private DEFAULT_PASSWORD = DEFFAULT_ACCOUNT.pass
    /**
     * Types user and password data and clicks login button
     * @param Map credentials {username, password, environment}
     */
    void login(Map credentials)
    {
        browser.clearCookiesQuietly()

        String user = credentials?.username ?: DEFAULT_USERNAME
        String passwd = credentials?.password ?: DEFAULT_PASSWORD

        if (credentials?.environment != null) {
            user = "$user@${credentials.environment}"
        }

        username << user
        password << passwd

        loginBtn.click()

    }


}
