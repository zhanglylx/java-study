package dms.util;

import dms.bo.LogData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类是一个工具类，负责读写数据
 * 把读写逻辑单独定义在该类中的目的是
 * 为了重用这些逻辑
 */
public class IOUtil {
    /**
     * 从给定的文件中读取第一行字符串，并将其
     * 转换为一个long值返回
     *
     * @param file
     * @return
     */
    public static long readLong(File file) {
        BufferedReader br = null;
        try {
            FileInputStream fis =
                    new FileInputStream(file);
            InputStreamReader isr =
                    new InputStreamReader(fis);
            br =
                    new BufferedReader(isr);
            String line = br.readLine();
            long l = Long.parseLong(line);
            return l;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从给定的RandomAccessFile的当前位置处
     * 连续读取给定字节数，并转换为字符串
     *
     * @param raf
     * @param len
     * @return
     */
    public static String readString(RandomAccessFile raf,
                                    int len) throws IOException {
        byte[] buf = new byte[len];
        raf.read(buf);
        String str =
                new String(buf, "ISO8859-1");
        return str.trim();
    }

    /**
     * 从给定的RandomAccessFile当前位置出
     * 读取一个int值并返回
     *
     * @param raf
     * @return
     * @throws IOException
     */
    public static int readInt(RandomAccessFile raf) throws IOException {
        return raf.readInt();
    }

    /**
     * 从给定的RandomAccessFile当前位置处
     * 读取一个short值并返回
     *
     * @param raf
     * @return
     * @throws IOException
     */
    public static short readShort(RandomAccessFile raf) throws IOException {
        return raf.readShort();
    }

    /**
     * 将给定的集合中的每个元素的
     * toString方法返回的字符串，作为
     * 一行内容写入给定的文件中
     *
     * @param list
     * @param file
     * @throws IOException
     */
    public static void saveList(List list, File file)
            throws IOException {
        PrintWriter pw = null;
        try {
            pw =
                    new PrintWriter(file);
            for (Object o : list) {
                pw.println(o);
            }
        } finally {

        }
        if (pw != null) pw.close();
    }

    /**
     * 将给定的long值作为一行字符串写入
     * 给定的文件
     */
    public static void saveLong(long l, File file)
            throws IOException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            pw.println(l);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 从指定的文件中按行读取每一条日志，并转换
     * 为一个LogData对象，最终将所有日志对象
     * 存入一个List集合中并返回
     *
     * @param file
     * @return
     */
    public static List<LogData> loadLogData(File file)
            throws IOException {
        BufferedReader br = null;
        try {
            FileInputStream fis
                    = new FileInputStream(file);
            InputStreamReader isr
                    = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line;
            List<LogData> logData = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                //解析过程应交给LogData
                /**
                 * 原因在于该字符串的格式是由LogData自身的
                 * toString决定的，所以解析自然也应该交给它
                 */
                logData.add(new LogData(line));

            }
            return logData;
        } finally {
            if (br != null) br.close();
        }
    }
}
