package sample;

import org.frikadelki.ash.telegram.api.base.TgmUpdate;
import org.frikadelki.ash.telegram.api.chat.TgmBotApiChat;
import org.frikadelki.ash.telegram.api.keyboard.KeyboardButton;
import org.frikadelki.ash.telegram.api.keyboard.ReplyKeyboardHide;
import org.frikadelki.ash.telegram.api.keyboard.ReplyKeyboardMarkup;
import org.frikadelki.ash.telegram.api.message.TgmMessage;

import java.io.IOException;

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
                replyMarkup = ReplyKeyboardMarkup
                        .builder()
                        .resizeKeyboard(true)
                        .oneTimeKeyboard(true)
                        .keyboardRow()
                        .button("asdf1")
                        .button("asdf2", KeyboardButton.Type.CONTACT)
                        .keyboardRow()
                        .button("qwer2")
                        .button("qwer2", KeyboardButton.Type.LOCATION)
                        .build();
            }
            else
            {
                replyMarkup = new ReplyKeyboardHide(true);
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
