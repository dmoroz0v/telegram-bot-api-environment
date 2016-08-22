package sample;

import org.frikadelki.ash.telegram.TgmConstants;
import org.frikadelki.ash.telegram.runtime.TgmQueryIO;
import org.frikadelki.ash.toolset.network.AshNetworkErrors;
import org.frikadelki.ash.toolset.network.HttpCodes;
import org.frikadelki.ash.toolset.result.AshResult;
import org.frikadelki.ash.toolset.result.error.AshError;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Created by d.morozov on 10/08/16.
 */
public class TgmQueryIOImpl implements TgmQueryIO
{
    @Override
    public AshResult<String> query(URL url, String jsonParams) throws IOException
    {
        System.out.println("send query");
        HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
        boolean shouldPost = (jsonParams != null);
        httpUrlConnection.setRequestMethod((shouldPost ? "POST" : "GET"));

        httpUrlConnection.addRequestProperty("Content-Type", TgmConstants.BODY_JSON_CONTENT_TYPE);

        if (shouldPost)
        {
            System.out.println(jsonParams);
            httpUrlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(httpUrlConnection.getOutputStream());
            wr.writeBytes(jsonParams);
            wr.flush();
            wr.close();
        }

        final int httpCode = httpUrlConnection.getResponseCode();

        BufferedReader in;
        if (200 <= httpCode && httpCode <= 299)
        {
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
        }
        else
        {
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        if (HttpCodes.isSuccessSubtype(httpCode) || HttpCodes.isBadRequestSubtype(httpCode)) {
            System.out.println("success query: " + response);
            final String responseBody = new String(response);
            System.out.println("response: " + responseBody);
            return new AshResult<>(responseBody);
        } else {
            System.out.println("error query: " + response);
            final AshError error = AshNetworkErrors.http(httpCode, "").build();
            return new AshResult<>(error);
        }
    }
}
