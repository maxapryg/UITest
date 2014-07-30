package com.livejournal.uitests.pages.service_pages.Unified_scheme.header;

import com.livejournal.uisteps.thucydides.elements.TextField;
import com.livejournal.uisteps.thucydides.elements.UIBlock;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;

/**
 *
 * @author s.savinykh
 */
@Block(
        @FindBy(css = ".i-iconus-search"))
public class SearchMenuItem extends UIBlock {

    @FindBy(css = ".s-search #SearchText")
    public TextField searchLine;
}
