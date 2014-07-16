/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livejournal.uitests.pages.service_pages.Unified_scheme.header;

import com.livejournal.uisteps.thucydides.elements.Button;
import com.livejournal.uisteps.thucydides.elements.Link;
import com.livejournal.uisteps.thucydides.elements.UIBlock;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;

/**
 *
 * @author s.savinykh
 */@Block
        (@FindBy(css=".s-do-item.s-do-item-feedback"))
public class FeedbackMenuItem extends UIBlock 
{
     
    @FindBy(css=".i-popup-close") 
    public Link xButton;
    
    @FindBy(css=".s-welcometo-action")
    public Link sendFeedback;
    
    @FindBy(css=".s-welcometo-switch")
    public Link switchOld;
    
}
