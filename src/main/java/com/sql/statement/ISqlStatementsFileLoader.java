package com.sql.statement;

public interface ISqlStatementsFileLoader {

    String sql(String name);

    void setStatementResourceLocation(String statementResourceLocation);

    String getStatementResourceLocation();

}
