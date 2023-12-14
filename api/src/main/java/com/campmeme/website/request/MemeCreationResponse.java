package com.campmeme.website.request;

public class MemeCreationResponse extends OperationSuccess {

    private long memeId;

    public MemeCreationResponse(long memeId) {
        this.memeId = memeId;
    }

    public long getMemeId() {
        return memeId;
    }

    public void setMemeId(long memeId) {
        this.memeId = memeId;
    }
}
