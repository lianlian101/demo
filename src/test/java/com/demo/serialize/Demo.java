package com.demo.serialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.fusesource.hawtbuf.ByteArrayInputStream;

public class Demo {

    /**
     * @param obj 需要序列化的对象
     * @return byte[] 序列化后数据
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        byte[] byteArray = baos.toByteArray();
        return byteArray;
    }
    
    /**
     * @param b 序列化的数据
     * @return object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("resource")
    public static Object unserialize(byte[] b) throws IOException, ClassNotFoundException{
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        return obj;
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User(1,"张三","123");
        // 序列化
        byte[] bs = serialize(user);
        System.out.println(bs);
        // 反序列化
        Object u = unserialize(bs);
        System.out.println(u);
    }
    
}
