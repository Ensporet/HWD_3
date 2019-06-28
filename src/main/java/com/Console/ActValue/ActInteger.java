package com.Console.ActValue;

@Act(type = {int.class,Integer.class})
public class ActInteger extends ActValueDefault<Integer> {

    public ActInteger() {
    }

    @Override
    public String getMassage() {
        return "Entered Integer number from -32768 to 32767";
    }

    @Override
    public Integer isTrueFormat(String string) {
        if(string == null || string.isEmpty() ||
                !string.matches(
                        "((\\+?0*"                             +
                                "(([1]\\d\\d\\d\\d\\d\\d\\d\\d\\d)|"  +
                                "([2][0]\\d\\d\\d\\d\\d\\d\\d\\d)|"   +
                                "([2][1][0-3]\\d\\d\\d\\d\\d\\d\\d)|" +
                                "([2][1][4][0-6]\\d\\d\\d\\d\\d\\d)|" +
                                "([2][1][4][7][0-3]\\d\\d\\d\\d\\d)|" +
                                "([2][1][4][7][4][0-7]\\d\\d\\d\\d)|" +
                                "([2][1][4][7][4][8][0-2]\\d\\d\\d)|" +
                                "([2][1][4][7][4][8][3][0-5]\\d\\d)|" +
                                "([2][1][4][7][4][8][3][6][0-3]\\d)|" +
                                "([2][1][4][7][4][8][3][6][4][0-7])|" +
                                "(\\d\\d?\\d?\\d?\\d?\\d?\\d?\\d?\\d?)))|("+

                                "(-)0*"                               +
                                "(([1]\\d\\d\\d\\d\\d\\d\\d\\d\\d)|"  +
                                "([2][0]\\d\\d\\d\\d\\d\\d\\d\\d)|"   +
                                "([2][1][0-3]\\d\\d\\d\\d\\d\\d\\d)|" +
                                "([2][1][4][0-6]\\d\\d\\d\\d\\d\\d)|" +
                                "([2][1][4][7][0-3]\\d\\d\\d\\d\\d)|" +
                                "([2][1][4][7][4][0-7]\\d\\d\\d\\d)|" +
                                "([2][1][4][7][4][8][0-2]\\d\\d\\d)|" +
                                "([2][1][4][7][4][8][3][0-5]\\d\\d)|" +
                                "([2][1][4][7][4][8][3][6][0-3]\\d)|" +
                                "([2][1][4][7][4][8][3][6][4][0-8])|" +
                                "(\\d\\d?\\d?\\d?\\d?\\d?\\d?\\d?\\d?))))"
                )
        ){

            return null;

        }


        return Integer.valueOf(string);
    }


}
