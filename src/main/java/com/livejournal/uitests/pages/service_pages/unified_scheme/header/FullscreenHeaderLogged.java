package com.livejournal.uitests.pages.service_pages.unified_scheme.header;

import com.livejournal.uisteps.thucydides.elements.Link;
import com.livejournal.uitests.pages.service_pages.inbox_pages.InboxMainPage;
import com.livejournal.uitests.pages.service_pages.lj_magazine_page.LJMagazinePageLogged;
import com.livejournal.uitests.pages.service_pages.main_pages.MainPageLogged;
import com.livejournal.uitests.pages.service_pages.update.UpdateBmlPageLogged;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;

/**
 *
 * @author s.savinykh
 */
@Block(
        @FindBy(css = HeaderLocator.CSS))
public class FullscreenHeaderLogged extends FullscreenHeader {

    @FindBy(css = ".s-nav-item-friends")
    public Link friendsFeedMenuItem;

    public MyJournalMenuItem myJournalMenuItem;

    @FindBy(css = ".s-do-item .s-do-item-post")
    public Link postNewEntry;

    @FindBy(css = ".s-do-item .s-do-item-message")
    public Link messagesMenuItem;

    public MainPageLogged clickOnLogo() {
        logo.click();
        return on(MainPageLogged.class);
    }

    public LJMagazinePageLogged clickOnLjMagazineMenuItem() {
        ljMagazineMenuItem.click();
        return on(LJMagazinePageLogged.class);
    }

    public FriendsFeedMenu moveMouseOverFriendsFeedMenuItem() {
        friendsFeedMenuItem.moveMouseOver();
        return on(FriendsFeedMenu.class);
    }

    public ShopMenuLogged moveMouseOverShopMenuItem() {
        shopMenuItem.moveMouseOver();
        return on(ShopMenuLogged.class);
    }

    public UpdateBmlPageLogged clickOnPostNewEntry() {
        postNewEntry.click();
        return on(UpdateBmlPageLogged.class);
    }

    public InboxMainPage clickOnLjMessagesMenuItem() {
        messagesMenuItem.click();
        return on(InboxMainPage.class);
    }
}
