package com.codesquad.team14.exception;

public class CategoryNotBestException extends RuntimeException {

    private final ErrorCode errorCode;

    public CategoryNotBestException() {
        super(ErrorCode.CATEGORY_NOT_BEST.getMessage());
        this.errorCode = ErrorCode.CATEGORY_NOT_BEST;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
