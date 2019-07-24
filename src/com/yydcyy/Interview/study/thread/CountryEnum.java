package com.yydcyy.Interview.study.thread;



/**
 * Created by YYDCYY on 2019-07-24.
 */
public enum CountryEnum {
    ONE(1, "清华大学"), TWO(2, "武汉大学"), THREE(3,"复旦大学"),FOUR(4,"上海交通大学"),FIVE(5,"北京大学"),SIX(6,"大连大学") ;

    //@Getter
    private Integer retCode; //Lombok 插件下载, 无用
    private String resMessage;
    public Integer getRetCode() {
        return retCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    CountryEnum(int retCode, String resMessage) {
        this.retCode = retCode;
        this.resMessage = resMessage;
    }

    //干干净净, 遍历返回枚举类型
    public static CountryEnum forEach_CountryEnum( int index){
        CountryEnum[] myArray = CountryEnum.values(); // 遍历
        for (CountryEnum element: myArray){
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
