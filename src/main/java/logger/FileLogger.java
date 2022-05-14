package logger;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileLogger{

    public void create(String name){
        try{
            File myFile = new File("C:\\Users\\Rykezu\\Desktop\\" + name + ".txt");
            if(myFile.createNewFile()){
                System.out.println("File created: " + myFile.getName());
            }
            else {
                System.out.println("File already exists");
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void write(String file, long value){
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\Rykezu\\Desktop\\" + file + ".txt");
            myWriter.write(value + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String file, String value){
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\Rykezu\\Desktop\\" + file + ".txt");
            myWriter.write(value);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String file, Object ... values){
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\Rykezu\\Desktop\\" + file + ".txt");
            for(int i = 0; i < values.length; i++){
                myWriter.write(String.valueOf(values[i]) + " ");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}