package day03;

import net.sf.json.JSONObject;

import java.io.*;
import java.util.Random;

public class tttt {
    public static void main(String[] args) throws IOException {
        File file = new File("book_chapterID.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String string;
        JSONObject jsonObject = new JSONObject();
        String[] strings;
        int index=0;
        int random=6;
        JSONObject books = new JSONObject();
        PrintWriter printWriter =new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File("shelfUpdate.txt")),"UTF-8"),true);
        while ((string=bufferedReader.readLine())!=null){
            strings = string.split(",");
            jsonObject.accumulate("lastChapterId",strings[0]);
            jsonObject.accumulate("bookId",strings[1]);
            books.accumulate("books",jsonObject);
            jsonObject.clear();
            if(index==random){
                System.out.println(books);
                printWriter.println(books);
                books.clear();
                random = new Random().nextInt(100)+6;
                index=0;
            }
            index++;
        }
        printWriter.close();
        bufferedReader.close();


    }

}
