package com.mtons.mblog.utils;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by raden on 2019/6/23.
 */
public class TxtConvertSrt {
    public static void write() {
        File file = null;
        FileWriter fw = null;
        file = new File("F:\\JMeterRes\\Data\\test123.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            for (int i = 1; i <= 3000; i++) {
                fw.write("abcdefgabcdefg" + i + ",");//向文件中写内容
                fw.write("sssssssssssssss" + i + ",\r\n");
                fw.flush();
            }
            System.out.println("写数据成功！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static String reader() {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("d:\\1.txt")));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = result.toString();
        // 读取的文件转换成srt格式

        return s;
    }

    public static void main(String[] args) {
        String reader = reader();

        String msg = reader;
        List<String> list = getMsg(msg);
        Map<String,String> map = new HashMap<>();
        int j = 0;
        for(String s : list){
            StringBuffer sb = new StringBuffer();
            sb.append(j++ +"\r\n");
            String substring = s.substring(1, s.length() - 1);
            String[] split = substring.split(",");
            for(int i = 0  ; i < split.length; i++) {
                String time = split[i];
                String[] split1 = time.split(":");
                StringBuffer sb1 = new StringBuffer();
                if(split1.length < 3) {
                    // 需要在最开始添加00:
                    sb1.append("00:");
                }
                for(String time1 : split1) {
                    if(new BigDecimal(time1).compareTo(new BigDecimal("10")) ==-1) {
                        time1 = "0" + time1+":";
                    }
                    sb1.append(time1);
                }
                if(i != split.length -1) {
                    sb.append(sb1.toString().substring(0,sb1.toString().length() - 1)).append(" --> ");
                } else {
                    sb.append(sb1.toString().substring(0,sb1.toString().length() - 1)).append("\r\n");
                }

            }
            map.put(s,sb.toString());
        }
        // 遍历map替换参数
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            reader = reader.replace(next.getKey(),next.getValue());
        }
        System.out.println(reader);
    }

    public static List<String> getMsg(String msg) {

        List<String> list = new ArrayList<String>();
//        Pattern p = Pattern.compile("(\\()([0-9a-zA-Z\\.\\/\\=])*(\\))");
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group(0));
        }
        return list;
    }

}
