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
    public void fretch (Message message, Bot bot, List<Board> boards){
        this.boards.addAll(boards);
        List<String> boardsname = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            boardsname.add(boards.get(i).getName());
        }
        boardsname.add("Add Board");
        bot.sendMsg(message,"Done", boardsname);
    }

    public List<Board> init(List<String> boardsId, Trello trelloApi){
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < boardsId.size(); i++) {
            boards.add(trelloApi.getBoard(boardsId.get(i)));
        }
        return boards;
    }

    public void setName (Message message, Bot bot, Board board, String newName){
        board.setName(newName);
    }

    public List<Board> getBoards() {
        return boards;
    }
    public void addBoard(Board board){
        this.boards.add(board);
    }
}
