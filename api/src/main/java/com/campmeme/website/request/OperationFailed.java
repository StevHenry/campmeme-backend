package com.campmeme.website.request;

public class OperationFailed {

    private final boolean success = false;

    private final String reason;

    public OperationFailed(String reason) {
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }
}
