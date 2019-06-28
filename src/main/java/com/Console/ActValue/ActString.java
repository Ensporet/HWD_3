package com.Console.ActValue;

@Act(type = {String.class})
public class ActString extends ActValueDefault<String> {


    public ActString() {
    }

    @Override
    public String getMassage() {
        return "Entered anu text";
    }

    @Override
    public String isTrueFormat(String string) {
        return string;
    }
}
