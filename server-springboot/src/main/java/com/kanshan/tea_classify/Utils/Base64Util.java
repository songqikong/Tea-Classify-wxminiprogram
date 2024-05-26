package com.kanshan.tea_classify.Utils;
import javax.xml.bind.DatatypeConverter;
import java.io.*;


public class Base64Util {
    public static String strToImg(String base64Str, String photoUrl){
        String base64Image = base64Str.split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File file = new File(photoUrl);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return photoUrl;
    }
}