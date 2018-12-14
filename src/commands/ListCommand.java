package commands;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

import java.util.ArrayList;
import java.util.List;

public class ListCommand {
    public List<TList> lists = new ArrayList<>();
    public Board board;
    public Trello trelloApi;

    @Deprecated
    public void fretch (Message message, Bot bot, List<TList> lists){
        this.lists = lists;
        List<String> listsname = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            listsname.add(lists.get(i).getName());
        }
        listsname.add("Add List");
        bot.sendMsg(message,"Done.", listsname);
    }

    public List<TList> init(Board board){
        List<TList> lists = new ArrayList<>();
        lists.addAll(board.fetchLists());
        return lists;
    }

    public Board getBoard() {
        return board;
    }

    public void addList(TList list){
        this.lists.add(list);
    }

    public void setApi(Trello trelloApi){
        this.trelloApi = trelloApi;
    }
}
