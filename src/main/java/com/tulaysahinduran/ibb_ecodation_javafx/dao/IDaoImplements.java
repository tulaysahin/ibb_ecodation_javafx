package com.tulaysahinduran.ibb_ecodation_javafx.dao;

import com.tulaysahinduran.ibb_ecodation_javafx.database.SingletonPropertiesDBConnection;

import java.sql.Connection;

public interface IDaoImplements  <T> extends ICrud<T>, IGenericsMethod<T> {

    // Gövdeli Method
    default Connection iDaoImplementsDatabaseConnection(){
        // Singleton DB
      //  return SingletonDBConnection.getInstance().getConnection();

        // Sşingleton Config
        return SingletonPropertiesDBConnection.getInstance().getConnection();
    }
}
