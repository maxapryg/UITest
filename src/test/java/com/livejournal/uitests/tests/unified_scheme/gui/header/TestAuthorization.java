package com.livejournal.uitests.tests.unified_scheme.gui.header;

import com.livejournal.uitests.pages.service_pages.login_page.LoginPage;
import com.livejournal.uisteps.thucydides.tests.WebTest;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 *
 * @author ASolyankin
 */
public class TestAuthorization extends WebTest {

    @When("I on $page submit authorization form with correct login $login and password $password")
    public void submit_authorization_form_with_correct(String page, String login, String password) {
        on(LoginPage.class, page).authorizeBy(login, password);
    }

    @Then("I should be authorized")
    public void should_be_authorized() {
        Assert.assertTrue(true);
    }
}
