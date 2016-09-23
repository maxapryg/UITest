package com.livejournal.uitests.pages.journal_pages.journal;

import com.livejournal.uitests.pages.journal_pages.entry.EntryPageLogged;
import com.livejournal.uisteps.thucydides.elements.UIBlock;
import com.livejournal.uitests.pages.journal_pages.JournalPageLogged;
import java.util.ArrayList;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.StepGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author s.savinykh
 */
@DefaultUrl("/")
public class MyJournalPageLogged extends JournalPageLogged {

    @FindBy(css = ".flaticon--arrow-bottom-bold")
    private UIBlock cutArrow;

    public EntryPageLogged openPostByText(String text) {
        startScript("jQuery('.entryunit:contains(\"" + text + "\") .entryunit__head .entryunit__title a')[0].click()");
        return onOpened(EntryPageLogged.class);
    }

    @StepGroup
    public String getTextFromLJCut(String text) {
        ArrayList<WebElement> entries = new ArrayList<WebElement>();
        int counter = 0;
        while ((entries.isEmpty()) && (counter < 100)) {
            counter++;
            entries = (ArrayList<WebElement>) getDriver().findElements(By.cssSelector(".entryunit"));
        }
        startScript("jQuery('.entryunit__text:contains(\"" + text + "\") b a').click()");
        return startScript("return jQuery('.entryunit__text:contains(\"" + text + "\") div').text().trim()").toString();

    }

    @StepGroup
    public String getLJCutCustomText(String text) {
        ArrayList<WebElement> entries = new ArrayList<WebElement>();
        int counter = 0;
        while ((entries.isEmpty()) && (counter < 100)) {
            counter++;
            entries = (ArrayList<WebElement>) getDriver().findElements(By.cssSelector(".entryunit"));
        }
        return startScript("return jQuery('.entryunit__text:contains(\"" + text + "\") b a').attr('title')").toString();

    }

}
