package com.livejournal.uitests.registration.useful.register_an_account_with_incorrect_data;

import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.create_account_pages.CreateAccountPage;
import com.livejournal.uitests.pages.service_pages.create_account_pages.PopupsBlock;
import com.livejournal.uitests.utility.Date;
import com.livejournal.uitests.utility.NumberOfSymbols;
import com.livejournal.uitests.utility.RandomName;
import com.livejournal.uitests.utility.VerifyText;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 *
 * @author m.prytkova
 */
public class RegisterAnAccountWithIncorrectData extends WebTest {

    @Given("unlogged user on Registration Form")
    public void unlogged_user_on_Registration_Form() {
        on(CreateAccountPage.class);
    }

    @When("user enter correct data leave one age field empty: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_data_leave_one_age_field_empty(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                day,
                month,
                year,
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getBirthDateForm().getYearDropDownMenu().getWrappedElement().click();

    }

    @When("user enter correct data leave email field empty: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_data_leave_email_field_empty(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getEmailField().type("");
    }

    @When("user enter correct data leave name field empty: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_data_leave_name_field_empty(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(name,
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getUserNameField().type("");

    }

    @When("user enter correct data leave password field empty: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_data_leave_password_field_empty(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordField().type("");

    }

    @When("user enter correct data except for the age: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_correct_data_except_for_the_age(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                day,
                month,
                year,
                gender);
        verify().that(on(CreateAccountPage.class).getCreateAccountForm().getCreateAccountButton().isEnabled())
                .ifResultIsExpected("Create account Button")
                .ifElse("Button is disabled!")
                .finish();
        on(CreateAccountPage.class).getCreateAccountForm().getCreateAccountButton().click();
    }

    @When("user enter correct data except for the email: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_correct_data_except_for_the_email(String name, String email, String password, String day, String month, String year, String gender) {
        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                NumberOfSymbols.get(email, 30),
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getEmailField().type("");

    }

    @When("user enter correct data except for the name: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_correct_data_except_for_the_name(String name, String email, String password, String day, String month, String year, String gender) {

        on(CreateAccountPage.class).createAccountData(NumberOfSymbols.get(new RandomName(name).get(), 30),
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getUserNameField().type("");

    }

    @When("user enter correct data except for the password: name $name, email $email, password $password, day $day, month $month, year $year, gender $gender")
    public void user_enter_correct_data_except_for_the_password(String name, String email, String password, String day, String month, String year, String gender) {

        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                NumberOfSymbols.get(password, 30),
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
        on(CreateAccountPage.class).getCreateAccountForm().getPasswordBlock().getPasswordField().type("");

    }

    @Then("in Popup user see message $message and button Create Account is not active")
    public void in_Popup_user_see_message_and_button_Create_Account_is_not_active(String message) {
        verify().that(on(PopupsBlock.class).getPopupBlock().isDisplayed())
                .ifResultIsExpected("Popup is displyed")
                .ifElse("Popup is not displyed!")
                .and()
                .that(on(PopupsBlock.class).getPopupText().getText().contains(message))
                .ifResultIsExpected(VerifyText.okTextForMessage(message))
                .ifElse(VerifyText.errorTextForMessage(message, on(PopupsBlock.class).getPopupText().getText()))
                .and()
                .that(!on(CreateAccountPage.class).getCreateAccountForm().getCreateAccountButton().isEnabled())
                .ifResultIsExpected("Create account Button is disabled")
                .ifElse("Button is enabled!")
                .finish();

    }

    @Then("user go to Finish Registration Form and see message $message")
    public void user_go_to_Finish_Registration_Form_and_see_message(String message) {
        verify().that(on(CreateAccountPage.class).getUnsuccessfulFinishForm().getFinishText().getText().contains(message))
                .ifResultIsExpected(VerifyText.okTextForMessage(message))
                .ifElse(VerifyText.errorTextForMessage(message, on(CreateAccountPage.class).getUnsuccessfulFinishForm().getFinishText().getText()))
                .finish();

    }

}
