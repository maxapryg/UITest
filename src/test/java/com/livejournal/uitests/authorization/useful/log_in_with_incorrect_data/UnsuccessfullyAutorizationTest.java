package com.livejournal.uitests.authorization.useful.log_in_with_incorrect_data;

import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.login_page.LoginPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 *
 * @author m.prytkova
 */
public class UnsuccessfullyAutorizationTest extends WebTest {

    @Given("unlogged user on Login Form")
    public void unlogged_user_on_Login_Form() {
        on(LoginPage.class);

    }

    @When("user enter incorrect data: name $name, password $password and clicks LogIn")
    public void user_login(String name, String password) {
        on(LoginPage.class).getLoginForm().authorizeBy(name, password);
    }

    @Then("user is not logged and see message $message")
    public void user_is_not_logged(String message) {
        verify().expectedResult("Error text", on(LoginPage.class).getErrorText().getText().contains(message))
                .showMessageIfVerificationFailed("Incorrect error text! Current text: " + on(LoginPage.class).getErrorText().getText() + " Cjrrecr text conyains: " + message).finish();

    }
}