package commands;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

import java.util.ArrayList;
import java.util.List;

public class ListCommand {
    public List<TList> lists = new ArrayList<>();

    @Deprecated
    public void fretch (Message message, Bot bot, Board board){
        this.lists.addAll(board.fetchLists());
        List<String> listsname = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            listsname.add(lists.get(i).getName());
        }
        bot.sendMsg(message,"Done.", listsname);
    }

    public List<TList> init(){
        return this.lists;
    }
}
