package com.technoecorp.gorilladealer.interfaces;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by achint on 3/27/17.
 */

public interface OkHttpCustomResponse {
    void onResponse(Call call, final Response response) throws IOException;

    void onFailure(Call call, final IOException e);
}

