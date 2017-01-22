package org.andriders.talk.sasara.error.io;

public class AppDatabaseException extends AppIOException {
    public AppDatabaseException() {
    }

    public AppDatabaseException(String message) {
        super(message);
    }

    public AppDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppDatabaseException(Throwable cause) {
        super(cause);
    }
}
