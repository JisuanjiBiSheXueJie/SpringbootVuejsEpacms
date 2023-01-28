package com.kevin.epacms.entity.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author kevin
 * @date 2022/11/24 21:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LeaveMap {

    public static List<LeaveMap> init(){
        List<LeaveMap> recordMaps = new ArrayList<>();
        for (long i = 11L; i >= 0; i--) {
            LocalDate localDate = LocalDate.now().minusMonths(i);
            String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            recordMaps.add(new LeaveMap(date, 0L));
        }
        return recordMaps;
    }

    @ApiModelProperty("每月的打卡数")
    private Long total;

    @ApiModelProperty("日期")
    private String date;

    public LeaveMap(String date){
        new LeaveMap(date, 0L);
    }

    public LeaveMap(String date, Long total){
        this.date = date;
        this.total = total;
    }
}
