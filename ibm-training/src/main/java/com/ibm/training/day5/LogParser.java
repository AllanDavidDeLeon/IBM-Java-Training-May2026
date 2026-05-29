package com.ibm.training.day5;

import java.util.Set;

@SuppressWarnings("serial")
class MalformedLogEntryException extends Exception {
    public MalformedLogEntryException(String message) {
        super(message);
    }
}

public class LogParser {

    private static final Set<String> levels = Set.of("INFO", "WARN", "ERROR");

    public static void validate(String log) throws MalformedLogEntryException {
        if (log == null || log.isEmpty()) {
            throw new MalformedLogEntryException("Log entry is empty or null");
        }

        if (!log.startsWith("[")) {
            throw new MalformedLogEntryException("Log entry must start with '['");
        }

        int closingBracketIndex = log.indexOf(']');
        if (closingBracketIndex == -1) {
            throw new MalformedLogEntryException("Log entry must contain a closing ']'");
        }

        if (closingBracketIndex + 1 >= log.length() || log.charAt(closingBracketIndex + 1) != ' ') {
            throw new MalformedLogEntryException("Expected space after closing ']'");
        }

        String rest = log.substring(closingBracketIndex + 2);

        int colonIndex = rest.indexOf(':');
        if (colonIndex == -1) {
            throw new MalformedLogEntryException("Log entry must contain ':' after the log level");
        }

        String level = rest.substring(0, colonIndex);

        if (!levels.contains(level)) {
            throw new MalformedLogEntryException(
                "Invalid log level: " + level + ". Expected INFO, WARN, or ERROR."
            );
        }

    }
}