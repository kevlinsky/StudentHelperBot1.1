package commands;

import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

public class NameCommand extends Command {
    public void execute (Message message, Bot bot){
        bot.standartSendMsg(message, "Type in name");
    }
}
