package com.example.moviesappmvvm.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class MoviesPayLoad {
    @SerializedName("api_key")
    @Expose
    String apiKey;
    @SerializedName("page")
    @Expose
    int page;

    public MoviesPayLoad() {
    }

    public MoviesPayLoad(String apiKey, int page) {
        this.apiKey = apiKey;
        this.page = page;
    }
    public Map<String, Object> toMap(){
        return new HashMap<String, Object>() {{
            put("api_key",apiKey);
            put("page",page);
        }
        };
    }
}