package com.livejournal.uitests.pages.service_pages.profile;

import com.livejournal.uisteps.thucydides.elements.TextField;
import com.livejournal.uisteps.thucydides.elements.UIBlock;
import com.livejournal.uitests.pages.service_pages.ServicePageLogged;
import java.util.ArrayList;
import java.util.List;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 *
 * @author s.savinykh
 */
@DefaultUrl("/profile")
public class ProfilePageLogged extends ServicePageLogged {

    public boolean noteIsDisplayed(String userWithNote, String note) {
        return getDriver().findElement(By.xpath("//span[@data-ljuser='" + userWithNote + "']"
                + "/a[@title='" + note + "']//following::span[contains(text(),'" + note + "')]")).isDisplayed();
    }

    @FindBy(css = ".b-profile-group-body")
    private TextField nameLabel;

    public String getName() {
        return nameLabel.getText();
    }

    public String getProfileBirthday() {
        return startScript("return jQuery('.b-profile-group-row .b-profile-group-body').eq(1).text()").toString();
    }

    public String getBirthdayLabel() {
        return startScript("return jQuery('.b-profile-group-subheader').eq(1).text()").toString();

    }
    
    public ArrayList<String> getSchoolList() {
         return onDisplayed(SchoolsBlock.class).getSchoolList();
    }

    public String getSchoolInfo() {
        String school = startScript("return jQuery('.b-profile-group-body .b-profile-list').eq(1).text()").toString();
        return school.trim();
    }
    

}
