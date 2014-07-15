/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livejournal.uitests.tests.account_creation;

import com.livejournal.uisteps.thucydides.tests.WebTest;
import com.livejournal.uitests.pages.service_pages.create_account_pages.CreateAccountPage;
import com.livejournal.uitests.pages.service_pages.create_account_pages.Popups;
import com.livejournal.uitests.tests.utility.Date;
import com.livejournal.uitests.tests.utility.RandomName;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 *
 * @author m.prytkova
 */
public class IncorrectPasswordTest extends WebTest {

    @When("user enter correct data except for the password: <name>,<email>,<password>,<day>,<monse>,<year>,<gender>")
    public void user_enter_data(@Named("name") String name,
            @Named("email") String email,
            @Named("password") String password,
            @Named("day") String day,
            @Named("monse") String month,
            @Named("year") String year,
            @Named("gender") String gender) throws InterruptedException {

        on(CreateAccountPage.class).createAccountData(new RandomName(name).get(),
                email,
                password,
                Date.parceDayOrGetCurrent(day).toString(),
                Date.parceMonthOrGetCurrent(month).toString(),
                Date.parceYearOrGetCurrent(year).toString(),
                gender);
    }

    @Then("user see message <message> on popup")
    public void user_see_message_on_popup(@Named("message") String message) {
        on(CreateAccountPage.class).createAccountForm.fieldPassword.passwordField.click();
        Assert.assertTrue("Popup is not displyed!", on(Popups.class).isDisplayed());
        String correctMessage = on(CreateAccountPage.class).createAccountForm.popups.popupText.getText();
        Assert.assertTrue("Incorrect text!", correctMessage.contains(message));
    }
    
    @Then ("button Create Account is not active")
    public void button_Create_Account_is_not_active (){
        Assert.assertFalse("Button is enabled!",on(CreateAccountPage.class)
                .createAccountForm.createAccountButton.isEnabled());
    }
}