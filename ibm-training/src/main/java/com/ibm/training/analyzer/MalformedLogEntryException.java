package com.ibm.training.analyzer;

@SuppressWarnings("serial")
public class MalformedLogEntryException extends Exception {
    public MalformedLogEntryException(String msg) {
        super(msg);
    }
}