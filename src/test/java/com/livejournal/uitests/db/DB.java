package com.livejournal.uitests.db;

import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.login_page.LoginPageUnlogged;
import org.jbehave.core.annotations.Given;

public class DB extends WebTest {

    @Given("data from DB")
    public void db() throws InterruptedException {

        System.out.println("!!!!!!!!!!!!!!!!!!!! start test");
        open(LoginPageUnlogged.class)
                .authorizeBy("autotest-paid-1", getDBDate().userData().getUserPassword("autotest-paid-1"));;
        System.out.println("!!!!!!!!!!!!!!!!!!!! finish test");

    }

}
