package sample;

import org.frikadelki.ash.telegram.api.base.TgmUpdate;
import org.frikadelki.ash.telegram.api.misc.TgmBotApiMisc;
import org.frikadelki.ash.telegram.bot.TgmBot;
import org.frikadelki.ash.toolset.result.AshResult;

import java.io.IOException;

/**
 * Created by d.morozov on 20/08/16.
 */
abstract class BotUpdatesRunLoop
{
    TgmBot bot;

    private long nextOffset(TgmUpdate[] updates)
    {
        long maxUpdateId = 0;

        for (TgmUpdate update : updates)
        {
            maxUpdateId = Math.max(update.getUpdateId(), maxUpdateId);
        }

        return maxUpdateId + 1;
    }

    void run()
    {
        Thread one = new Thread()
        {
            public void run()
            {
                bot = new TgmBot(Config.TOKEN, new TgmQueryIOImpl());
                long currentOffset = 0;
                while (true)
                {
                    TgmBotApiMisc.UpdatesParams updateParams = new TgmBotApiMisc.UpdatesParams(currentOffset, Config.LIMIT, Config.TIMEOUT);
                    AshResult<TgmUpdate[]> result = bot.getApi().getMiscApi().getUpdates(updateParams);
                    if (result.getData().length > 0)
                    {
                        currentOffset = nextOffset(result.getData());
                    }

                    try
                    {
                        if (result.getData().length > 0)
                        {
                            updates(result.getData());
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        one.start();
    }

    abstract void updates(TgmUpdate[] updates) throws IOException;
}
