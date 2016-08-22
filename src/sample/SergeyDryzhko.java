package sample;

import org.frikadelki.ash.telegram.api.base.TgmUpdate;
import org.frikadelki.ash.telegram.api.chat.TgmBotApiChat;
import org.frikadelki.ash.telegram.api.inlinemode.InlineQuery;
import org.frikadelki.ash.telegram.api.inlinemode.InlineQueryResultCachedSticker;

import java.io.IOException;
import java.util.*;

/**
 * Created by d.morozov on 20/08/16.
 */
class SergeyDryzhko extends BotUpdatesRunLoop
{
    private long count = 0;

    private Hashtable<String, String> stickers;

    SergeyDryzhko()
    {
        stickers = new Hashtable<String, String>();

        stickers.put("ВО!", "BQADAgADcQIAApkvSwoK3OoiQepJ8AI");
        stickers.put("ДОБРЫЙ ДЕНЬ!", "BQADAgADnwIAApkvSwqpp2YVRNxRMwI");
        stickers.put("КАЖЕТСЯ У МЕНЯ ПРОБЛЕМЫ", "BQADAgADbQIAApkvSwqSCUCfSYEoJgI");
        stickers.put("Немного болит голова. Может быть потому что я ...", "BQADAgADZgIAApkvSwrKHxf38uDS7gI");
        stickers.put("Я В ЭТО МАЛО ВЕРЮ", "BQADAgADcwIAApkvSwq5H4S7pJIhPQI");
        stickers.put("Возможно ли это? -ДА!", "BQADAgADXAIAApkvSwrNBKR2aTSeCQI");
        stickers.put("ДА, Я ПОПАЛ В ПРОСАК", "BQADAgADRgIAApkvSwr5_N9l8PkIdQI");
        stickers.put("с какой целью зачем", "BQADAgADTgIAApkvSwozyRKJbrFMWgI");
        stickers.put("честно сказать не хочется комментировать эту ситуацию", "BQADAgADLwMAApkvSwpz2R-r4d5kpgI");
        stickers.put("так получилось", "BQADAgADVgIAApkvSwrKdrn26Qe9kAI");
        stickers.put("парадокс", "BQADAgADYgIAApkvSwoPqvImrjuRmgI");
        stickers.put("это очередной миф", "BQADAgADYAIAApkvSwoqG0eE_9ullgI");
        stickers.put("звучит не плохо", "BQADAgADVAIAApkvSwomUbrRJIvm9wI");
        stickers.put("есть факты говорящие об обратном", "BQADAgADTAIAApkvSwp-MNtNBr3LvAI");
        stickers.put("вот в чем секрет вот в чём секрет", "BQADAgADfQIAApkvSwpH-1oI2InZQAI");
        stickers.put("но если все это правда но если всё это правда", "BQADAgADZAIAApkvSwplQa7_nZHORAI");
        stickers.put("неужели все так серьезно неужели всё так серьёзно", "BQADAgADWAIAApkvSwoiwu0d000S0QI");
        stickers.put("от этого зависит наше будущее", "BQADAgADewIAApkvSwpFI568CeVkvQI");
        stickers.put("назовите пожалуиста сумму назовите пожалуйста сумму", "BQADAgADaAIAApkvSwp8-DqDL4Pa6QI");
        stickers.put("это много или мало", "BQADAgADeQIAApkvSwo8Bw8I2WvQIQI");
        stickers.put("ну что", "BQADAgADagIAApkvSwoSgtNKhHLMtwI");
        stickers.put("я не могу ответить", "BQADAgADNwMAApkvSwoyLdjqVGahOwI");
        stickers.put("и я не могу", "BQADAgADbwIAApkvSwrAuUXpBTblIQI");
        stickers.put("но такого я конечно не ожидал", "BQADAgADdwIAApkvSwpiQZeRUk8VygI");
        stickers.put("напрасно", "BQADAgADfwIAApkvSwqzAzZCSI2erAI");
        stickers.put("а если ты ошибешься а если ты ошибёшься", "BQADAgADhQIAApkvSwp7ImURPaQVBAI");
        stickers.put("мысли материализовались", "BQADAgADggIAApkvSwr2lE71rjyp2QI");
        stickers.put("и никакой романтики", "BQADAgADjgIAApkvSwrTzhGe1d1AHwI");
        stickers.put("любопытно", "BQADAgADlQIAApkvSwob4i6SZiDpTwI");
        stickers.put("договоренности срываются в последний момент договорённости срываются в последний момент", "BQADAgAD9AIAApkvSwrRnyxQ5OqYTgI");
        stickers.put("то что мы делаем считается большим грехом", "BQADAgADnQIAApkvSwqC2e42atOO0AI");
        stickers.put("я не хочу есть этот банан", "BQADAgADogIAApkvSwpW6UhIeros2gI");
        stickers.put("не приведетли это к полному краху", "BQADAgAD7AIAApkvSwqHK_hi6zXEcAI");
        stickers.put("как бы не так", "BQADAgAD9gIAApkvSwqPq1DfX9RlxAI");
        stickers.put("сложно представить последствия этого", "BQADAgAD4gIAApkvSwoHGux6WvLJGgI");
        stickers.put("это отнюдь не салют в мою честь", "BQADAgADBAMAApkvSwovtpkxmZ7STgI");
        stickers.put("так ли это на самом деле", "BQADAgADAgMAApkvSwpTVVU8YXwP-QI");
        stickers.put("верить или нет", "BQADAgADBgMAApkvSwqZKv-gLWSDeQI");
        stickers.put("ну вряд ли уважаемый человек будет лгать", "BQADAgADCAMAApkvSwpey0haPl7snQI");
        stickers.put("я разумеется тоже", "BQADAgADEgMAApkvSwrflVgXFIxDvwI");
        stickers.put("какой-то набор слов", "BQADAgAD-AIAApkvSwqFEPD7pyVxiwI");
        stickers.put("и это не просто громкие слова", "BQADAgADFAMAApkvSwplfqIFSb-7wgI");
        stickers.put("я бы назвал это запахои опасности", "BQADAgADFgMAApkvSwpK0aibWsItdQI");
        stickers.put("а жить становится все менее комфортно а жить становится всё менее комфортно", "BQADAgADNQMAApkvSwrvVdW5hMEM3gI");
        stickers.put("мое ощущение полная беспомощность моё ощущение полная беспомощность", "BQADAgADKwMAApkvSwqeNcSuHFjj1wI");
        stickers.put("однако", "BQADAgAD4AIAApkvSwoPhvXRz-1G5AI");
        stickers.put("было неприятно", "BQADAgADDgMAApkvSwpac_3cMpteXAI");
        stickers.put("отрицать бессмысленно", "BQADAgADVAMAApkvSwokziLC0reCGwI");
        stickers.put("я испытыл мощный эмоциональный подъем я испытыл мощный эмоциональный подъём", "BQADAgADMQMAApkvSwof1eG8nipgbwI");
        stickers.put("не все так просто", "BQADAgADOQMAApkvSwoZc64uI3UZqQI");
        stickers.put("это мало похоже на комедию", "BQADAgADmQMAApkvSwrcoMDpc_r_GwI");
        stickers.put("я называю это коллективным помешательством", "BQADAgADlwMAApkvSwpIoG7cjl4pbgI");
        stickers.put("довольно точное выражение", "BQADAgADjQMAApkvSwpXdJSDLi1mbAI");
        stickers.put("я решил не останавливаться на достигнутом", "BQADAgADdQIAApkvSwrwO_Mx9b9zNAI");
        stickers.put("в этом действительно нет ничего плохого", "BQADAgADnAMAApkvSwpz0KhioUFhiAI");
        stickers.put("настало время развеять сомнения", "BQADAgADrAMAApkvSwrr2ENtInO1iQI");
        stickers.put("недеюсь мы увидим нечто уникальное", "BQADAgADrgMAApkvSwpF029OyAyeTAI");
        stickers.put("это конечно спорный вопрос", "BQADAgADsgMAApkvSwqQhEC9DqWhjgI");
        stickers.put("была для этого веская и страшная причина", "BQADAgADqgMAApkvSwrn1HTxI15_6gI");
        stickers.put("у нас нет другого выхода", "BQADAgADlAMAApkvSwpskThh4YrtVgI");
        stickers.put("поразительно", "BQADAgADiwMAApkvSwoN0hWGX0RkqQI");
        stickers.put("так это или нет", "BQADAgADsAMAApkvSwo1ASHT18uSWgI");
        stickers.put("если бы", "BQADAgAD_gIAApkvSwrqdrdz7MVPeQI");

        stickers.put("сильное заявление", "BQADAgADTQADyJsDAAGPT_EglSEzTwI");
        stickers.put("проверять я его конечно не буду", "BQADAgADTwADyJsDAAHFvWRl0KiKqQI");
        stickers.put("но как", "BQADAgADVQADyJsDAAHHKTtygFhveQI");
        stickers.put("вы неправы всего добного", "BQADAgADVwADyJsDAAETOPhiMC9QLgI");
        stickers.put("вы понимаете о чем говорят все эти люди я нет", "BQADAgADWwADyJsDAAGRDxBhSsxpzQI");
        stickers.put("данная информация у нас в стране никому не нужна", "BQADAgADXQADyJsDAAEtoHskNrp27wI");
        stickers.put("это конечно неправда", "BQADAgADXwADyJsDAAFXqHkAAaB4CCMC");
        stickers.put("мое ожидание уже длится болше часа", "BQADAgADYQADyJsDAAHdefLwYuFZ1gI");
        stickers.put("наверстать упущенное практически невозможно", "BQADAgADYwADyJsDAAGXMW_lJ4WMLgI");
        stickers.put("я вышел в интернет с таким вопросом", "BQADAgADZQADyJsDAAGdpeir3gJD_AI");
        stickers.put("", "BQADAgADZwADyJsDAAH7XcfjlRL4BQI");
        stickers.put("но где доказательства", "BQADAgADaQADyJsDAAGYedp6DBThWwI");

    }

    @Override
    void updates(TgmUpdate[] updates) throws IOException
    {
        InlineQuery inlineQuery = updates[0].getInlineQuery();

        if (inlineQuery != null)
        {
            ArrayList<String> filteredStickers = new ArrayList<String>();
            Enumeration<String> keys = stickers.keys();
            while (keys.hasMoreElements())
            {
                String key = keys.nextElement();

                if (key.length() == 0 || key.toLowerCase().contains(inlineQuery.getQuery().toLowerCase()))
                {
                    filteredStickers.add(stickers.get(key));
                }
            }

            ArrayList<InlineQueryResultCachedSticker> stickers = new ArrayList<InlineQueryResultCachedSticker>();

            for (String stickerFileId : filteredStickers)
            {
                InlineQueryResultCachedSticker sticker = InlineQueryResultCachedSticker
                        .builder()
                        .type("sticker")
                        .id(Integer.toString(filteredStickers.indexOf(stickerFileId)))
                        .stickerFileId(stickerFileId)
                        .build();

                stickers.add(sticker);
            }

            TgmBotApiChat.AnswerInlineQueryParams params = TgmBotApiChat.AnswerInlineQueryParams
                    .builder()
                    .inlineQueryId(inlineQuery.getId())
                    .results(stickers)
                    .cacheTime(0L)
                    .build();
            bot.getApi().getChatApi().answerInlineQuery(params);
        }
    }
}
