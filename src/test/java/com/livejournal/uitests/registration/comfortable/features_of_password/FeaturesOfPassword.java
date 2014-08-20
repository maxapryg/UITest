package com.livejournal.uitests.registration.comfortable.features_of_password;

import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.create_account_pages.CreateAccountPage;
import com.livejournal.uitests.pages.service_pages.create_account_pages.PopupsBlock;
import com.livejournal.uitests.utility.VerifyText;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 *
 * @author m.prytkova
 */
public class FeaturesOfPassword extends WebTest {

    @Given("unlogged user on Registration Form")
    public void unlogged_user_on_Registration_Form() {
        on(CreateAccountPage.class);
    }

    @When("user enter password $password")
    public void user_enter_password(String password) {
        on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordField().enter(password);
    }

    @When("user clicks Mapping Button")
    public void user_clicks_Mapping_Button() {
        on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordMappingLinkHide().click();
    }

    @Then("the password is hidden")
    public void the_password_is_hidden() {
        verify().that(on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordMappingLinkHide().isDisplayed())
                .ifResultIsExpected("Correct icon display password")
                .ifElse("Incorrect icon display password!")
                .and()
                .that(!on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordMappingLinkShow().isDisplayed())
                .ifResultIsExpected("Correct icon display password")
                .ifElse("Incorrect icon display password!")
                .finish();
    }

    @Then("the password is displayed")
    public void the_password_is_displayed() {
        verify().that(!on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordMappingLinkHide().isDisplayed())
                .ifResultIsExpected("Correct icon display password")
                .ifElse("Incorrect icon display password!")
                .and()
                .that(on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordMappingLinkShow().isDisplayed())
                .ifResultIsExpected("Correct icon display password")
                .ifElse("Incorrect icon display password!")
                .finish();

    }

    @Then("user see Password Bubble which contains text $text and URL $URL")
    public void user_see_Password_Bubble_which_contains_text_and_URL(String text, String URL) {
        on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordField().click();
        verify().that(on(PopupsBlock.class).isDisplayed())
                .ifResultIsExpected("Popup is displyed")
                .ifElse("Popup is not displyed!")
                .and()
                .that(on(PopupsBlock.class).getPopupText().getText().contains(text))
                .ifResultIsExpected(VerifyText.okTextForMessage(text))
                .ifElse(VerifyText.errorTextForMessage(text, on(PopupsBlock.class).getPopupText().getText()))
                .finish();

        on(PopupsBlock.class).getLearnMoreLink().click();
        verify().that(getCurrentUrl().contains(URL))
                .ifResultIsExpected("Learn More")
                .ifElse(VerifyText.errorTextForURL("Learn More", URL, getCurrentUrl()))
                .finish();

    }
}