package studenthelperbot;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import commands.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public Command[] commands = new Command[]{new HelpCommand(), new SettingsCommand()};
    public String[] commandsName = new String[]{"/help", "/settings"};

    public static final String APIKEY = "dfa6598342b2baa03488828d8ec33b4e";
    public static final String APITOKEN = "53a09ff578ed30b8a6699d1630dd49824fb65174aac726aa3175188a60ca11b5";

    public Trello trelloApi = new TrelloImpl(APIKEY,APITOKEN,new ApacheHttpClient());
    public Member user = trelloApi.getMemberInformation("user26006782");

    public List<String> boardId = this.user.getIdBoards();
    public List<Board> boards = new ArrayList<>();
    public List<TList> lists = new ArrayList<>();
    public List<Card> cards = new ArrayList<>();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void setButtons(SendMessage sendMessage, List<String> buttonsname){

        //Инициализация клавиатуры
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true); //Доступ клавиатуры разным пользователям
        replyKeyboardMarkup.setResizeKeyboard(true); //Автоматическая подгонка кнопок
        replyKeyboardMarkup.setOneTimeKeyboard(false); //Клавиатура будет скрываться когда бот отвечает на сообщение
        //Создание строк кнопок
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        //Добавление кнопок в строку
        for (int i = 0; i < buttonsname.size(); i++) {
            keyboardFirstRow.add(buttonsname.get(i));
        }
        //Добавление строк в список строк
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    @Deprecated
    public void sendMsg (Message message, String text,  List<String> buttonsname){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage, buttonsname);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void standartSendMsg (Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()){

            //Cовпадение с командой /start
            if (message.getText().equals("/start")){
                StartCommand start = new StartCommand();
                start.fretch(message, this, this.boardId, this.trelloApi);
                this.boards = start.init();
            }
            //Совпадения со стандартными командами
            for (int i = 0; i < commands.length; i++) {
                if (commandsName[i].equals(message.getText())) {
                    commands[i].execute(message, this);
                }
            }
            //Совпадения с именами досок
            for (int j = 0; j < boards.size(); j++) {
                if(boards.get(j).getName().equals(message.getText())){
                    ListCommand lists = new ListCommand();
                    lists.fretch(message, this, this.boards.get(j));
                    this.lists = lists.init();
                }
            }
            //Совпадения с именами списков
            for (int j = 0; j < lists.size(); j++) {
                if(lists.get(j).getName().equals(message.getText())){
                    CardCommand cards = new CardCommand();
                    cards.fretch(message, this, lists.get(j));
                    this.cards = cards.init();
                }
            }

            //Совпадения с именами карточек
            for (int j = 0; j < cards.size(); j++) {
                if(cards.get(j).getName().equals(message.getText())){
                    System.out.println("Well Done!");
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "kpfustudenthelper_bot";
    }

    @Override
    public String getBotToken() {
        return "684988930:AAERYB0FK2Vzd5l_hJd0edjo4KHhR_Z6UYI";
    }
}
