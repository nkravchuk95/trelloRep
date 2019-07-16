package junk;

import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.LoginPage;
import org.testng.annotations.Test;

public class LogTest extends BrowserFactory {

    LoginPage logpage = new LoginPage();

    @Test
    public void login() throws InterruptedException{

        logpage.open();
        logpage.login("bopo@planet-travel.club","MavenProject");
        Thread.sleep(1000);
    }
}
