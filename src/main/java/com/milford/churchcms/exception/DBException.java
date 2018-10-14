package com.milford.churchcms.exception;

public class DBException extends RuntimeException{
    public DBException(String appName, String dbProblem){
        super(appName + " " + dbProblem);
    }
    
    public DBException(String appName, String dbProblem, int id){
        super(appName + " " + dbProblem + "for table ID: " + id);
    }
}
