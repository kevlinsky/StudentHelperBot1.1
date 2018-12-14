package commands;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

import java.util.ArrayList;
import java.util.List;

public class CardCommand {
    public List<Card> cards = new ArrayList<>();
    public TList list;

    @Deprecated
    public void fretch (Message message, Bot bot, List<Card> cards){
        this.cards = cards;
        List<String> cardsname = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            cardsname.add(cards.get(i).getName());
        }
        cardsname.add("Add Card");
        bot.sendMsg(message,"Done", cardsname);
    }

    public List<Card> init(TList list){
        List<Card> cards = new ArrayList<>();
        cards.addAll(list.getCards());
        return cards;
    }

    public TList getList() {
        return list;
    }
}
