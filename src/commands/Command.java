package commands;

import org.telegram.telegrambots.api.objects.Message;
import studenthelperbot.Bot;

public abstract class Command {
    public void execute(Message message, Bot bot){}
}
