package com.Console.ActValue;

@Act(type = {Boolean.class, boolean.class})
public class ActBoolean extends ActValueDefault<Boolean> {

    public ActBoolean(){}


    @Override
    public String getMassage() {
        return "Entered true or false ";
    }

    @Override
    public Boolean isTrueFormat(String string) {

        final String [] BOOLEAN_VARIATIONS = new String[]{
                "TRUE","FALSE","True","False","true","false"
        };

        for (String s : BOOLEAN_VARIATIONS) {
            if(s.equals(string)) {
                return Boolean.valueOf(string);
            }
        }

        return null;
    }
}
