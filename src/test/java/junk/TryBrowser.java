package junk;

import com.trello.ui.core.BrowserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class TryBrowser extends BrowserFactory {

    @Test
    @Epic("")
    @Feature("")
    @Story("ef")
    public void openClose(){
        driver().get("https://google.com");
    }

}
