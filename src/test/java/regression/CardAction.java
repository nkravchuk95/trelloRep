package regression;

import com.google.gson.Gson;
import com.trello.api.TrelloRestClient;
import com.trello.api.models.Card;
import com.trello.api.models.Checklist;
import com.trello.api.models.Label;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.CardPage;
import com.trello.ui.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.*;
import static org.hamcrest.Matchers.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.with;

public class CardAction extends BrowserFactory {



    public TrelloRestClient client = new TrelloRestClient();

    public LoginPage loginPage = new LoginPage();
    public BoardsPage boardsPage = new BoardsPage();
    public CardPage cardPage = new CardPage();

    Card card = new Card("Test_Card_"+new Date().getTime());

    @BeforeTest
    public void prepareData() throws IOException {
        card = client.cardsService.createCardNew("5d09c4cd65685688a7c43973", card).execute().body();
    }

    @AfterTest
    public void clearData() throws IOException {
       client.cardsService.deleteCard(card.id).execute();
    }

    @Test
    public void login(){
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        Assert.assertTrue(!driver().findElements(By.cssSelector(".mod-add")).isEmpty(), "Board page not opened");

    }

    @Test(dependsOnMethods={"login"})
    public void openCard(){
        cardPage.open(card.url);
    }


    @Test(dependsOnMethods={"login", "openCard"})
    public void copyCard()throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.copy(card.url, "New","lis");

        with().pollInterval(10, MILLISECONDS).then().await().atMost(30, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().idBoard, equalTo("5d092d72a46052887d259450"));
        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardUp.idBoard,"5d092d72a46052887d259450");

    }

    @Test(dependsOnMethods={"login", "openCard"})
    public void moveCard()throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.move(card.url,"lis");

        with().pollInterval(10, MILLISECONDS).then().await().atMost(30, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().idList, equalTo("5d0bbaa6b24718181696873e"));
        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardUp.idList,"5d0bbaa6b24718181696873e");

    }

    @Test()
    public void addChecklistItem()throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.addChecklist(card.url,"checklistNameTest","checklistItemNameTest");

        int size = card.idChecklists.size();
        List<String> idChecklists = card.idChecklists;
        await().atMost(10, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().idChecklists.size(), equalTo(size+1));

        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardUp.idChecklists.size(),size+1);
        List<String> idChecklistsUp = cardUp.idChecklists;

        idChecklistsUp.removeAll(idChecklists);
        String idChecklist = idChecklistsUp.get(0);

        int sizecheckItems = card.idChecklists.size();
        Checklist checklist = client.checkListService.getCheckList(idChecklist).execute().body();

        String namecheckItem = checklist.checkItems.get(0).name;

        Assert.assertEquals(namecheckItem, "checklistItemNameTest");




    }

    @Test()
    public void addLabel()throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        int size = client.cardsService.getCard(card.id).execute().body().labels.size();

        Label label = client.labelsService.getLabel("5d0b11b9e6983765e415e1fc").execute().body();
        cardPage.addLabel(card.url,label.id);

        with ().pollInterval(10, MILLISECONDS).then().await().atMost(10, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().labels.size(), equalTo(size+1));

        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardUp.labels.size(),size+1);
    }

    @Test()
    public void addMember()throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.addMember(card.url,"5cf5307243cdc88c39024d09");


        await().atMost(10, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().idMembers.contains("5cf5307243cdc88c39024d09"), equalTo(true));

        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertTrue(cardUp.idMembers.contains("5cf5307243cdc88c39024d09"));

    }

    @Test(dependsOnMethods={"login", "openCard"})
    public void addDate() throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.addDateTime(card.url,"25");

        with().pollInterval(10, MILLISECONDS).then().await().atMost(20, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().due, equalTo("2019-07-25T09:00:00.000Z"));
        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardUp.due,"2019-07-25T09:00:00.000Z");
        System.out.println(card.due);
    }

    @Test(dependsOnMethods={"login", "openCard"})
    public void addDesc() throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.addDescr(card.url,"new new");

        with().pollInterval(10, MILLISECONDS).then().await().atMost(10, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().desc, equalTo("new new"));
        Card cardForLabel = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardForLabel.desc,"new new");

    }


    @Test(dependsOnMethods={"login", "openCard"})
    public void addComment(){
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        cardPage.addComment(card.url,"new new com");
    }


}
