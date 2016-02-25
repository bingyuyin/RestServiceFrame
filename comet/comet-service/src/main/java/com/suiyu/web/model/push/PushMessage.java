package com.suiyu.web.model.push;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public class PushMessage {
    private PushMessageType type;
    private Object object;
    private String targetId;
    private String sourceId;

    public PushMessageType getType() {
        return type;
    }

    public void setType(PushMessageType type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
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

    @Override
    public String toString(){
        return "";
    }
}
