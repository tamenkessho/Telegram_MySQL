import DataBase.DataBase;
import DataBase.DataBaseSettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Bot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        String text = update.getMessage().getText();
        if(text.equals("/start")){
            try {
                execute(Answers.send(update,"I'm Bot, which collecting phone numbers and store them in MySQL DataBase"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                DataBaseSettings dbs = new DataBaseSettings();
                Connection connection = DataBase.connect(dbs);
                Statement statement = null;
                if (connection != null) {
                    statement = connection.createStatement();
                }
                String name = update.getMessage().getFrom().getFirstName();
                String secondName = update.getMessage().getFrom().getLastName();
                assert statement != null;
                statement.executeUpdate("INSERT " + dbs.tableName + "(" + dbs.uc_1 + ", " + dbs.uc_2 + ", " + dbs.uc_3 + ") VALUES ('" + name + "', '" + secondName + "', '" + text + "');");
                execute(Answers.send(update,"I successfully created a new row in the table, thank you for participating!"));

            } catch (ClassNotFoundException | SQLException | TelegramApiException e) {
                e.printStackTrace();
            }       //exceptions
        }
    }

    @Override
    public String getBotUsername() {
        return "@PhoneCollectorBot";
    }

    @Override
    public String getBotToken() {
        return "1882814884:AAF_7X31SJJoTbtkG97dXD-iyDL1jpRYqaw";
    }
}