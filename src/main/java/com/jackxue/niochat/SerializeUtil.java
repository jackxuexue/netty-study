package com.jackxue.niochat;

import java.io.*;

public class SerializeUtil {

    /**
     * 序列化对象
     * @param object
     * @return
     */
    public static byte[] serializeObj(Object object){
        if (object == null)
        {
            return null;
        }

        ByteArrayOutputStream byteOutStream = null;

        ObjectOutputStream outputStream = null;

        try
        {
            byteOutStream = new ByteArrayOutputStream();

            outputStream = new ObjectOutputStream(byteOutStream);

            outputStream.writeObject(object);

            byte[] bytes = byteOutStream.toByteArray();

            return bytes;
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return null;
        }
        finally
        {
            if (null != outputStream)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            if (null != byteOutStream)
            {
                try
                {
                    byteOutStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * 反序列化对象
     * @param bytes
     * @return
     */
    public static Object unSerializeObj(byte[] bytes){
        ByteArrayInputStream byteInputStream = null;

        ObjectInputStream inputStream = null;

        try
        {
            byteInputStream = new ByteArrayInputStream(bytes);

            inputStream = new ObjectInputStream(byteInputStream);

            return inputStream.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != byteInputStream)
            {
                try
                {
                    byteInputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            if (null != inputStream)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
