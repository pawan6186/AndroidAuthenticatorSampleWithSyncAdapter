package wittgroupinc.com.authenticatordemo;

import com.google.gson.Gson;

/**
 * Created by Pawan Gupta on 05-03-2017.
 */

public class JSONParser<T> {
    private final Class<T> type;

    public T parse(String response) {
        Gson gson = new Gson();
        T t = gson.fromJson(response, getMyType());
        return t;
    }


    public JSONParser(Class<T> type) {
        this.type = type;
    }

    public Class<T> getMyType() {
        return this.type;
    }
}
