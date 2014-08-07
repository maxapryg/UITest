
package com.livejournal.uitests.registration.comfortable.links_on_the_finish_form;

import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.create_account_pages.CreateAccountPage;
import com.livejournal.uitests.utility.Date;
import com.livejournal.uitests.utility.RandomName;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 *
 * @author m.prytkova
 */
public class ValidateEmailLinkTest extends WebTest {

    @Given("new user on Finish Form (data: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender)")
    public void new_user_on_Finish_Form(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getCreateAccountButton().click();

    }

    @When("user click Validate Email Link")
    public void user_click_Сhange_Email_Link() {
        on(CreateAccountPage.class).getSuccessfulFinishForm().getValidateEmail().click();

    }

    @Then("user in Validate Email Page")
    public void user_in_Сhange_Email_Page() {
        verify().expectedResult("Validate Email link", getCurrentBrowser().getDriver().getCurrentUrl().contains("/register.bml"))
                .showMessageIfVerificationFailed("You are not in Validate Emai Page! Current URL: " + getCurrentBrowser().getDriver().getCurrentUrl() + " Correct URL contains: /register.bml").finish();
 
    }
    
}
