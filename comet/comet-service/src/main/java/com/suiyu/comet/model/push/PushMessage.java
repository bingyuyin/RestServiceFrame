package com.suiyu.comet.model.push;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public class PushMessage {
    private PushMessageType type;
    private Object body;
    private String targetId;
    private String sourceId;

    public PushMessage(String targetId, String sourceId, Object body, PushMessageType type){
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.body = body;
        this.type = type;
    }

    public PushMessageType getType() {
        return type;
    }

    public void setType(PushMessageType type) {
        this.type = type;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString(){
        return "";
    }
}
