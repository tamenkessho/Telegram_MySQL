import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
public abstract class Answers extends TelegramLongPollingBot {

    public static SendMessage send(Update update, String receivedText) {
        SendMessage message = new SendMessage();
        message.setText(receivedText);
        message.setChatId(update.getMessage().getChatId());
        return message;
    }
}
