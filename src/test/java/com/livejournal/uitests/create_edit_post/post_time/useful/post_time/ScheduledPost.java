package com.livejournal.uitests.create_edit_post.post_time.useful.post_time;

import com.livejournal.uisteps.thucydides.ThucydidesUtils;
import com.livejournal.uisteps.thucydides.WebTest;
import com.livejournal.uitests.pages.service_pages.login_page.LoginPageUnlogged;
import com.livejournal.uitests.pages.service_pages.tools.SheduledEntriesPage;
import com.livejournal.uitests.pages.service_pages.update.FinishPostForm;
import com.livejournal.uitests.pages.service_pages.update.UpdateBmlPageLogged;
import com.livejournal.uitests.utility.RandomText;
import com.livejournal.uitests.utility.date.PostTime;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 *
 * @author m.prytkova
 */
public class ScheduledPost extends WebTest {

    //Scenario: Create scheduled post (1/3)
    @Given("logged user $name on Create Post page")
    public void logged_user_on_Create_Post_page(String name) throws InterruptedException {
        open(LoginPageUnlogged.class)
                .authorizeBy(name, getDBDate().userData().getUserPassword(name))
                .defoultLanguage(name)
                .defoultStyle(name);
        open(SheduledEntriesPage.class)
                .deleteAllSheduledEntries();
        open(UpdateBmlPageLogged.class);
    }

    //Scenario: Create scheduled post (2/3)
    @When("user create new post and change parameter $parameter by value $value")
    public void user_create_new_post_and_change_date(String parameter, String value) {
        ThucydidesUtils.putToSession("post_date", PostTime.getCorrectDate(parameter, value));
        String[] date = ThucydidesUtils
                .getFromSession("post_date")
                .toString()
                .split(";");
        String post_text = RandomText.getRandomText(30);
        onOpened(UpdateBmlPageLogged.class)
                .closeDraft()
                .createPost("New scheduled post", "html", post_text)
                .setDateAndTime(date[0], date[1])
                .postEntry();
        ThucydidesUtils.putToSession("post_text", post_text);
    }

    //Scenario: Create scheduled post (2/3)
    @Then("the post is scheduled")
    public void the_post_is_scheduled() {
        String post_text = onDisplayed(FinishPostForm.class)
                .clickToScheduledLink()
                .getPostByText(ThucydidesUtils.getFromSession("post_text").toString().trim());
        verify().that(post_text.contains(PostTime.convertPostTime(ThucydidesUtils.getFromSession("post_date").toString(), "24")))
                .ifResultIsExpected("Post is scheduled, whis correct date: " + PostTime.convertPostTime(ThucydidesUtils.getFromSession("post_date").toString(), "24"))
                .ifElse("There is no post " + post_text + " in scheduled, with correct date")
                .finish();
    }

}
