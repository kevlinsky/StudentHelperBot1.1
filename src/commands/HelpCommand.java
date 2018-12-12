package commands;

import studenthelperbot.Bot;
import org.telegram.telegrambots.api.objects.Message;

public class HelpCommand extends Command {

    public void execute(Message message, Bot bot){
        bot.standartSendMsg(message, "How can I help you? \n" +
                            "To set up the link write \"/settings\" \n");
    }

}
