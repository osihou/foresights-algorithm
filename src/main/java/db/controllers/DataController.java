package db.controllers;


import java.sql.PreparedStatement;

public abstract class DataController {


    public DataController(){

    }

    public DataController(String path){

    }

    public abstract PreparedStatement getPreparedStatement(String statement);

    public abstract void closeConnection();

}
