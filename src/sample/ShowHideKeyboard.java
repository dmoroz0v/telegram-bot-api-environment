package sample;

import org.frikadelki.ash.telegram.api.base.TgmUpdate;
import org.frikadelki.ash.telegram.api.chat.TgmBotApiChat;
import org.frikadelki.ash.telegram.api.keyboard.TgmKeyboardButton;
import org.frikadelki.ash.telegram.api.keyboard.TgmReplyKeyboardHide;
import org.frikadelki.ash.telegram.api.keyboard.TgmReplyKeyboardMarkup;
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
				replyMarkup = TgmReplyKeyboardMarkup
						.builder()
						.resizeKeyboard(true)
						.oneTimeKeyboard(true)
						.selective(false)
						.keyboard()
						.row()
						.button("asdf1")
						.button("asdf2", TgmKeyboardButton.Type.CONTACT)
						.endRow()
						.row()
						.button("qwer2")
						.button("qwer2", TgmKeyboardButton.Type.LOCATION)
						.endRow()
						.endKeyboard()
						.build();
			}
			else
			{
				replyMarkup = new TgmReplyKeyboardHide(true);
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
