package sample;

import org.frikadelki.ash.telegram.api.base.TgmUpdate;
import org.frikadelki.ash.telegram.api.chat.TgmBotApiChat;
import org.frikadelki.ash.telegram.api.keyboard.KeyboardButton;
import org.frikadelki.ash.telegram.api.keyboard.ReplyKeyboardHide;
import org.frikadelki.ash.telegram.api.keyboard.ReplyKeyboardMarkup;
import org.frikadelki.ash.telegram.api.message.TgmMessage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by d.morozov on 20/08/16.
 */
class ShowHideKeyboard extends BotUpdatesRunLoop
{
    private long count = 0;

    @Override
    void updates(TgmUpdate[] updates) throws IOException
    {
        TgmMessage message = updates[0].getNewMessage();

        if (message != null)
        {
            ++count;

            long chatId = message.getChat().getId();

            Object replyMarkup;

            if (count % 2 == 1)
            {
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                ArrayList<ArrayList<KeyboardButton>> keyboard = new ArrayList<ArrayList<KeyboardButton>>();
                ArrayList<KeyboardButton> keyboardRow = new ArrayList<KeyboardButton>();
                KeyboardButton keyboardButton = new KeyboardButton();
                keyboardButton.setText("action1");
                keyboardButton.setRequestEntity(KeyboardButton.RequestEntity.LOCATION);
                keyboardRow.add(keyboardButton);
                keyboard.add(keyboardRow);

                replyKeyboardMarkup.setKeyboard(keyboard);

                replyMarkup = replyKeyboardMarkup;
            } else
            {
                ReplyKeyboardHide replyKeyboardHide = new ReplyKeyboardHide();
                replyKeyboardHide.setHideKeyboard(true);

                replyMarkup = replyKeyboardHide;
            }

            bot.getApi().getChatApi().sendMessage(TgmBotApiChat.SendMessageParams
                    .builder()
                    .chatId(chatId)
                    .text("1")
                    .replyMarkup(replyMarkup)
                    .build());
        }
    }
}
