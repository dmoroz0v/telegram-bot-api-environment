package sample;

import org.frikadelki.ash.telegram.runtime.TgmQueryIO;
import org.frikadelki.ash.toolset.result.AshResult;

import java.io.IOException;
import java.net.URL;

/**
 * Created by d.morozov on 10/08/16.
 */
public class TgmQueryIOImpl implements TgmQueryIO
{
    @Override
    public AshResult<String> query(URL url, String jsonParams) throws IOException {
        return null;
    }
}
