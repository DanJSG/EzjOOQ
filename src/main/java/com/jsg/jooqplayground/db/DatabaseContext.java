package com.jsg.jooqplayground.db;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

public final class DatabaseContext {

    private static DSLContext context = null;
    private static boolean configured = false;

    private DatabaseContext() {}

    public static void configure(DataSource dataSource, SQLDialect dialect) {
        context = DSL.using(dataSource, dialect);
        configured = true;
    }

    public static DSLContext get() {
        if(!configured) throw new IllegalStateException("Database context has not been configured. Please configure");
        return context;
    }

}
