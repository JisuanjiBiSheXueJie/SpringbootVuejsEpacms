package com.kevin.epacms;

import cn.hutool.core.date.DateUtil;
import com.kevin.epacms.entity.dto.RecordMap;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @author: kevin
 * @since: 15:11 2022/5/6
 */
public class BaseTest {

    @Test
    public void test() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("e:/file.txt"));
        String content = readFileLine(bufferedReader, reader -> {
            try {
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < 4; i++) {
                    sb.append(reader.readLine()+"\n");
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        });
        System.out.println(content);
    }

    private String readFileLine(BufferedReader reader,Function<BufferedReader, String> function){
        return function.apply(reader);
    }

    @Test
    public void test2(){
        String dateValue = "2022-05-07";
        String date = "2022-05-07 14:00:14";
        Date parse1 = DateUtil.parse(date, "yyyy-MM-dd");
        Date parse2 = DateUtil.parse(dateValue, "yyyy-MM-dd");
        System.out.println(parse1.equals(parse2));
    }

    @Test
    public void test3(){
    }
}
