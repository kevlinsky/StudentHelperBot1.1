package commands;


import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

import java.util.ArrayList;
import java.util.List;

public class StartCommand extends Command{
    public List<Board> boards = new ArrayList<>();

    @Deprecated
    public void fretch (Message message, Bot bot, List<String> boardsId, Trello trelloApi){
        for (int i = 0; i < boardsId.size(); i++) {
            boards.add(trelloApi.getBoard(boardsId.get(i)));
        }
        List<String> boardsname = new ArrayList<>();
        String text = "Nothing";
        for (int i = 0; i < boards.size(); i++) {
            boardsname.add(boards.get(i).getName());
            text = boards.get(i).getName();
        }
        bot.sendMsg(message,text, boardsname);
    }

    public List<Board> init(){
        return this.boards;
    }
}
