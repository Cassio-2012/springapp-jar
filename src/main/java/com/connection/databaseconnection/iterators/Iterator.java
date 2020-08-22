package com.connection.databaseconnection.iterators;

import com.connection.databaseconnection.adapters.PostModel;

import java.util.List;

public interface Iterator {


    boolean hasNext();
    PostModel nextList();


}
