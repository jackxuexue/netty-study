package com.jackxue.niochat;

import java.io.Serializable;

public class MessageEvent implements Serializable {
    static final long serialVersionUID = 1L;

    private int type;
    private Object  source;

    public MessageEvent(int type, Object source) {
        init(type, source);
    }

    private void  init(int type, Object source) {
        this.type = type;
        this.source = source;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
