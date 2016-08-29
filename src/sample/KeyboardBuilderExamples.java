package sample;

import org.frikadelki.ash.telegram.api.keyboard.TgmKeyboardButton;
import org.frikadelki.ash.telegram.api.keyboard.TgmReplyKeyboardMarkup;
import org.frikadelki.ash.telegram.api.keyboard.builders.TgmKeyboardBuilder;
import org.frikadelki.ash.telegram.api.keyboard.builders.TgmKeyboardRowBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.morozov on 29/08/16.
 */
public class KeyboardBuilderExamples {

	{
		//конструируем отельно row когда-то
		List<TgmKeyboardButton> keyboardRow1 = TgmKeyboardRowBuilder
			.make()
			.button("qqq1")
			.button("qqq2", TgmKeyboardButton.Type.CONTACT)
			.build();

		//можно без билдера накидать
		ArrayList<TgmKeyboardButton> keyboardRow2 = new ArrayList<>();
		keyboardRow2.add(new TgmKeyboardButton("www1"));
		keyboardRow2.add(new TgmKeyboardButton("www1", TgmKeyboardButton.Type.LOCATION));

		TgmReplyKeyboardMarkup
			.builder()
			.keyboard()
			.row(keyboardRow1)//уже когда-то сбилданые row можно добавлять
			.row(keyboardRow2)
			.row()//можно стартануть row и накидать на лету
			.button("aaa1")
			.button("aaa2", TgmKeyboardButton.Type.CONTACT)
			.endRow()
			.endKeyboard()
			.build();
	}

	{
		//конструируем отельно клаву когда-то
		List<List<TgmKeyboardButton>> keyboard = TgmKeyboardBuilder
			.make()
			.row()
			.button("test1")
			.button("test2", TgmKeyboardButton.Type.LOCATION)
			.button(new TgmKeyboardButton("test3"))
			.button(new TgmKeyboardButton("test4", TgmKeyboardButton.Type.CONTACT))
			.endRow()
			.row()
			.button("test5")
			.endRow()
			.build();

		TgmReplyKeyboardMarkup
			.builder()
			.resizeKeyboard(true)
			.oneTimeKeyboard(true)
			.selective(false)
			.keyboard(keyboard)//присваиваем когда-то как-то сконструированую клаву
			.build();
	}

	{
		//конструируем весь markup сразу
		TgmReplyKeyboardMarkup
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
}
