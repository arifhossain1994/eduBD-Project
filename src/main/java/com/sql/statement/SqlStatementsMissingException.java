package com.sql.statement;

public class SqlStatementsMissingException extends RuntimeException {

    private static final long serialVersionUID = 5655491702104925774L;

    public SqlStatementsMissingException(String string) {
        super(string);
    }

}
