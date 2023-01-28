package com.kevin.epacms.entity.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * @author kevin
 * @date 2022/11/24 21:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RecordMap {

    public static List<RecordMap> init(){
        List<RecordMap> recordMaps = new ArrayList<>();
        for (long i = 11L; i >= 0; i--) {
            LocalDate localDate = LocalDate.now().minusMonths(i);
            String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            recordMaps.add(new RecordMap(date, 0L));
        }
        return recordMaps;
    }

    @ApiModelProperty("每月的打卡数")
    private Long total;

    @ApiModelProperty("日期")
    private String date;

    public RecordMap(String date){
        new RecordMap(date, 0L);
    }

    public RecordMap(String date, Long total){
        this.date = date;
        this.total = total;
    }
}
