package commands;

import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

import java.util.ArrayList;
import java.util.List;

public class CardCommand {
    public List<Card> cards = new ArrayList<>();

    @Deprecated
    public void fretch (Message message, Bot bot, TList list){
        cards.addAll(list.getCards());
        List<String> cardsname = new ArrayList<>();
        String text = cards.toString() + ".";
        for (int i = 0; i < cards.size(); i++) {
            cardsname.add(cards.get(i).getName());
            text = cards.get(i).getName();
        }
        bot.sendMsg(message,text, cardsname);
    }

    public List<Card> init(){
        return this.cards;
    }
}
