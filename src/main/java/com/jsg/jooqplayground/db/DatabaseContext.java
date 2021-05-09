package com.jsg.jooqplayground.db;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

public abstract class DatabaseContext {

    private static DSLContext context = null;
    private static boolean configured = false;

    public static void configure(DataSource dataSource, SQLDialect dialect) {
        context = DSL.using(dataSource, dialect);
        configured = true;
    }

    public static DSLContext get() {
        return context;
    }

}
