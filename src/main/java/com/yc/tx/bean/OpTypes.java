package com.yc.tx.bean;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-17 14:15
 */
//枚举类型
public enum  OpTypes {
     deposite("deposite",1),withdraw("withdraw" ,2),transfer("transfer",3);
    private String name;
    private int value;


    //构造方法
    private OpTypes (String name,int value){
        this.name=name;
        this.value=value;

    }
    //覆盖方法

    @Override
    public String toString() {
        return "OpTypes{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }
}
