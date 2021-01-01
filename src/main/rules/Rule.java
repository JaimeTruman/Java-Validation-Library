package main.rules;


import main.results.Result;

public interface Rule {
    String getMessageOnFailed();
    Result check(Object object);
}
