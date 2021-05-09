package com.jsg.jooqplayground;

import com.jsg.jooqplayground.db.ConnectionPool;
import com.jsg.jooqplayground.db.DatabaseContext;
import com.jsg.jooqplayground.ezsql.GenericRepository;
import org.jooq.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/playground?serverTimezone=UTC&rewriteBatchedStatements=true";
        String username = "localdev";
        String password = "password";
        ConnectionPool.configure(url, username, password);
        DatabaseContext.configure(ConnectionPool.getDataSource(), SQLDialect.MARIADB);


//        Example example = new Example("Test", "984573049587vh5p36");
//        EntityConverter.unwrap(example);
        GenericRepository<Example> repo = new GenericRepository<>(DatabaseContext.get(), Example.class);
        List<Example> exampleList = repo.readAll();
        System.out.println(exampleList);
//        boolean result = repo.updateWhere(example, DSL.field("value").eq("Test"));
//        boolean result = repo.delete(example);
//        System.out.println("Successful? " + result);
//        System.out.println(repo.readAll());

//        DSLContext db = DatabaseContext.get();
//
//
//        for (int i = 0; i < 500; i++){
//            List<Word> words = db.select().from(DSL.table("sentiment"))
//                    .where(DSL.field("word").eq("good").and(DSL.field("tag").eq("sentiwords")))
//                    .fetch().into(Word.class);
//        }

    }

}
