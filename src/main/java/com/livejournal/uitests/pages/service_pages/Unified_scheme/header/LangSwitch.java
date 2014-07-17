/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.livejournal.uitests.pages.service_pages.Unified_scheme.header;

import com.livejournal.uisteps.thucydides.elements.Link;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;

/**
 *
 * @author s.savinykh
 */@Block
        (@FindBy(css=".s-nav-item-lang-open"))
public class LangSwitch
{
    @FindBy(xpath=".//*[@data-lang='en_LJ'][2]")
    public Link langEn;
    
    @FindBy(xpath=".//*[@data-lang='ru'][2]")
    public Link langRu;
        
    @FindBy(xpath=".//*[@data-lang='uk'][2]")
    public Link langUk;
            
    @FindBy(xpath=".//*[@data-lang='fr'][2]")
    public Link langFr;
                
    @FindBy(xpath=".//*[@data-lang='pt'][2]")
    public Link langPt;
                    
    @FindBy(xpath=".//*[@data-lang='es'][2]")
    public Link langEs;
                        
    @FindBy(xpath=".//*[@data-lang='de'][2]")
    public Link langDe;
    
    @FindBy(xpath=".//*[@data-lang='it'][2]")
    public Link langIt;
    
    @FindBy(xpath=".//*[@data-lang='be'][2]")
    public Link langBe;
    
}
