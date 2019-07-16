package junk;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Card;
import org.testng.annotations.Test;

import java.io.IOException;

public class TryTrelloApi {

    TrelloRestClient client = new TrelloRestClient();

    @Test
    public void callIT() throws IOException {

        Card card = new Card();
        card.name = "uh";

        Card newCard = client.cardsService.createCardNew("5d09c4cd65685688a7c43973", card).execute().body();


        client.cardsService.deleteCard(newCard.id).execute();

    }
}
