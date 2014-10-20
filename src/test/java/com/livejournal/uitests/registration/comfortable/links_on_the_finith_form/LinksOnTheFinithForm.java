package com.livejournal.uitests.registration.comfortable.links_on_the_finith_form;

import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.create_account_pages.CreateAccountPage;
import com.livejournal.uitests.pages.service_pages.create_account_pages.finish_form.SuccessfulFinishForm;
import com.livejournal.uitests.pages.service_pages.create_account_pages.finish_form.UnsuccessfulFinishForm;
import com.livejournal.uitests.utility.Date;
import com.livejournal.uitests.utility.RandomName;
import com.livejournal.uitests.utility.VerifyText;
import net.thucydides.core.annotations.StepGroup;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 *
 * @author m.prytkova
 */
public class LinksOnTheFinithForm extends WebTest {

    //Scenario: Links on successfuly finish form (1/3)
    //Scenario: Links on unsuccessfuly finish form (1/3)
    @Given("new user on Finish Form (data: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender)")
    public void new_user_on_Finish_Form(String name, String email, String password, String day, String month, String year, String gender) {
        open(CreateAccountPage.class);
        getCurrentBrowser().deleteCookies();
        open(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender)
                .clickOnCreateAccountButton();
    }

    //Scenario: Links on successfuly finish form (2/3)
    @When("user click link $page on successfuly Finish Form")
    public void user_click_link_on_successfuly_Finish_Form(String page) {
        clickOnSuccessfulLink(page);
    }

    //Scenario: Links on unsuccessfuly finish form (2/3)
    @When("user click link $page on unsuccessfuly Finish Form")
    public void user_click_link_on_unsuccessfuly_Finish_Form(String page) {
        clickOnUnuccessfulLink(page);
    }

    //Scenario: Links on successfuly finish form (3/3)
    //Scenario: Links on unsuccessfuly finish form (3/3)
    @Then("user in correct page $page with URL $URL")
    public void user_in_correct_Page_with_URL(String page, String URL) {
        verify().that(getCurrentUrl().contains(URL))
                .ifResultIsExpected(VerifyText.okTextForURL(page, URL))
                .ifElse(VerifyText.errorTextForURL(page, URL, getCurrentUrl()))
                .finish();
    }

    @StepGroup
    public void clickOnSuccessfulLink(String link) {
        switch (SuccessfulFormLinks.valueOf(stringToEnum(link))) {
            case СHANGE_EMAIL:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnChangeEmailLink();
                break;
            case VALIDATE_EMAIL:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnValidateEmailLink();
                break;
            case EDIT_PROFILE:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnEditProfileLink();
                break;
            case FIND_FRIENDS:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnFindFiendsLink();
                break;
            case SELECT_JOURNAL:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnCustomizeJournalLink();
                break;
            case FRIENDS_FEED:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnFrendsFeedLink();
                break;
            case RATINGS:
                onDisplayed(SuccessfulFinishForm.class)
                        .clickOnRatingsLink();
                break;
            case CREATE_POST_PAGE:
                onDisplayed(SuccessfulFinishForm.class)
                        .createFirstPost();
                break;

            default:
                Assert.fail("Unknown link " + link + "!");
        }
    }

    @StepGroup
    public void clickOnUnuccessfulLink(String link) {
        switch (UnsuccessfulFormLinks.valueOf(stringToEnum(link))) {
            case MAIN_PAGE:
                onDisplayed(UnsuccessfulFinishForm.class)
                        .clickOnLjAnonymouslyLink();
                break;
            case UNDERAGE_ACCOUNT:
                onDisplayed(UnsuccessfulFinishForm.class)
                        .clickOnUnderageAccountLink();
                break;

            default:
                Assert.fail("Unknown link " + link + "!");
        }
    }

    private String stringToEnum(String link) {
        return link.replace(" ", "_")
                .toUpperCase();
    }

   
}
