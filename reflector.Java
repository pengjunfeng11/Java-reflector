package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * cov_preventer
 * berry
 * 2022/1/11
 */
@Getter
@Setter
public class ReturnMsg {
private int code;
private String data;
private String msg;

//根据反射，获取任意对象的全部属性值。换句话说，是一个小型的json工具
//但目前仅限public修饰的对象,private还需要再研究研究
//private属性的区别是需要使用field.getDeclaredFields来获取属性名
//并且在获取属性值之前需要绕过访问权限的检查field.setAccessible(true);
public String forward(@NotNull Object data) throws  IllegalAccessException {
    Field[] fields = data.getClass().getDeclaredFields();
    StringBuffer sb = new StringBuffer();
    sb.append("{");
    for (Field field : fields) {
        sb.append(field.getName());
        sb.append(":");
        field.setAccessible(true);
        sb.append(field.get(data));
        sb.append(",");
    }
    sb.append("}");

    return new String(sb);
}

public String Success(Object data){

    return null;
}

    public static void main(String[] args) throws Exception{
        dog dog = new dog("小奶油",19);
        people model = new people("li","男",18,dog);
        ReturnMsg returnMsg = new ReturnMsg();
        System.out.println(returnMsg.forward(model));
    }
}


@Getter
@Setter
class people{
    private String name;
    private String sex;
    private int id;
    private dog gou;

    public people(String name, String sex, int id , dog gou) {
        this.name = name;
        this.sex = sex;
        this.id = id;
        this.gou = gou;

    }
}

@Getter
@Setter
@ToString
@AllArgsConstructor
class dog{
    private String name;
    private int age;
}
