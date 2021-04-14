package com.jackxue.niochat;

public class MessageEvent {
    private int id;
    private int type;
    private Object  source;


    public MessageEvent(int id, Object source) {
        init(id, 0, source);
    }

    private void  init(int id, int type, Object source) {
        this.id = id;
        this.type = type;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
