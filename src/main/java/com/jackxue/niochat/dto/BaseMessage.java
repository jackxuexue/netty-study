package com.jackxue.niochat.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseMessage implements Serializable {
    static final long serialVersionUID = 1L;
    protected int type;
    protected Map<String, String> keyV = new HashMap<>();

    public int getType(){
        return type;
    }
    public String getValue(String key){
        return keyV.get(key);
    }
    public void add(String key, String value){
        keyV.put(key, value);
    }
}
