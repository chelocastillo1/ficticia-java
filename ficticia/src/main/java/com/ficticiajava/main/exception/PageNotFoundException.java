package com.ficticiajava.main.exception;

public class PageNotFoundException extends RuntimeException {

    private final int page;

    public PageNotFoundException(String message, int page) {
        super(String.format(message, page));

        this.page = page;
    }

    public int getPage() {
        return page;
    }
}
