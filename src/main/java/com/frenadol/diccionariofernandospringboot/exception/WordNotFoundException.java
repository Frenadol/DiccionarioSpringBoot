package com.frenadol.diccionariofernandospringboot.exception;

public class WordNotFoundException extends RuntimeException {

    private String exceptionDetail;
    private Object fieldValue;

    public WordNotFoundException(String exceptionDetail, Object fieldValue) {
        super(exceptionDetail + " -" + fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }

    public WordNotFoundException(String exceptionDetail) {
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}