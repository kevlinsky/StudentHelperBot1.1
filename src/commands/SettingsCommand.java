package commands;

import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

public class SettingsCommand extends Command {

    public void execute(Message message, Bot bot){
        bot.standartSendMsg(message, "What do you want to set up?");
    }
}
