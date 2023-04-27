package com.company;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.simple.JSONObject;




public class Main {


        private static Path filePath = Paths.get("C:\\Users\\Casa\\Desktop\\test(1).mif");

    public static void main(String[] args) throws FileNotFoundException {
        System.setProperty("file.encoding", "UTF-8");
        String path = "C:\\Users\\Casa\\Desktop\\1.mif";

        String з = "C:\\Users\\Casa\\Desktop\\result.json";
        final int LOOKUP = 0;
        final int READ_KEY = 1;
        final int READ_VALUE = 2;
        int state = LOOKUP;
       // Gson g = new Gson();
        try(FileInputStream fin=new FileInputStream(path))
            {
                System.out.println("DEBUG 1");
                int i;
                while((i=fin.read()) != -1){
                    switch (state) {
                        case LOOKUP:
                            //isDigit(String.valueOf(i));
                            if (((i > 'A') && (i < 'z')) || ((isDigit(String.valueOf(i)) == true)))  {
                                state = READ_KEY;
                                System.out.print("{\"");
                                System.out.print((char) i);
                            }
                            break;

                        case READ_KEY:
                           // isDigit(String.valueOf(i));
                            if (((i > 'A') && (i < 'z')) || ((isDigit(String.valueOf(i)) == true))) {
                                System.out.print((char) i);
                            } else {
                                System.out.print("\", \"");
                                state = READ_VALUE;
                            }
                            break;

                        case READ_VALUE:
                            if (i == '\n') {
                                System.out.println("\"}");
                                state = LOOKUP;
                            } else {
                                System.out.print((char) i);
                            }
                            break;
                    }
                }
                System.out.println();
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

