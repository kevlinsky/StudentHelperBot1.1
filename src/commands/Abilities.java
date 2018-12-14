package commands;

import com.julienvey.trello.domain.Card;
import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

import java.util.ArrayList;
import java.util.List;

public class Abilities {
    List<String> abilities = new ArrayList<>();

    @Deprecated
    public void fretch (Message message, Bot bot){
        bot.sendMsg(message,"Done", this.abilities);
    }
}
