package com.company;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
//import org.json.JSONArray;
//
//import javax.xml.crypto.Data;
//import org.json.simple.JSONObject;




public class Main {

        private static Path filePath = Paths.get("C:\\Users\\Casa\\Desktop\\test(1).mif");

    public static void main(String[] args) throws FileNotFoundException {
        System.setProperty("file.encoding", "UTF-8");
        String pathWindows = "C:\\Users\\Casa\\Desktop\\1.mif";
        String pathMacOS = "/Users/administrator/IdeaProjects/MifParser1/test (1).mif";
//        Gson g = new Gson;
//        Data d = g.fromJson(jsonString, Data.class);

        String  resultPath = "C:\\Users\\Casa\\Desktop\\result.json";
        final int LOOKUP = 0;
        final int READ_KEY = 1;
        final int READ_VALUE = 2;
        int state = LOOKUP;
       // Gson g = new Gson();
        try(FileInputStream fin=new FileInputStream(pathWindows))
            {
                System.out.println("Parsing of"+pathWindows);
                System.out.println("\"test1\" : {");
                int i;
                while((i=fin.read()) != -1){
                    switch (state) {
                        case LOOKUP: //Чтение первого символа
                            //isDigit(String.valueOf(i));
                            if (((i > 'A') && (i < 'z')))  {
                                state = READ_KEY;
                                //System.out.print("{\"");
                                System.out.print("   ");
                                System.out.print((char) i);
                            }
                            else if(((isDigit(String.valueOf(i)) == true))){
                                System.out.print((char) i);

                            }
                            break;

                        case READ_KEY:// чтение последующих символов
                           // isDigit(String.valueOf(i));
                            if (((i > 'A') && (i < 'z')) ) {
                                System.out.print((char) i);
                            } else if(((isDigit(String.valueOf(i)) == true))){
                                System.out.print((char) i);
                                System.out.println("{");
                                System.out.print("\"");
                                state = READ_VALUE;
                            }
                            break;

                        case READ_VALUE:// чтение значения ключа до конца строки
                            if (i == '\n') {
                                System.out.println("\"}");
                                state = LOOKUP;
                            } else {
                                System.out.print((char) i);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + state);
                    }
                }
                System.out.println("}");
            }
        catch(IOException ex){

                System.out.println(ex);
                System.out.println(ex.getMessage());
        }
    }
    private static boolean isDigit(String i) throws NumberFormatException {
        try {
            Double.parseDouble(i);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


