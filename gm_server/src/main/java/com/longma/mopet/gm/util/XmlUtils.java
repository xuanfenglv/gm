package com.longma.mopet.gm.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;

public class XmlUtils {

    public static Document parseXmlWithSAX(String filePath,String encoding){
        SAXReader xmlReader = new SAXReader();
        try {
            Reader reader=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),encoding));
            return xmlReader.read(reader);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2){
            e2.printStackTrace();
        } catch (FileNotFoundException e3){
            e3.printStackTrace();
        }
        return null;
    }
}