package com.kevin.epacms.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author kevin
 * @date 2022/12/3 14:13
 */
@Data
public class NewsListDto {
    private List<NewsDto> newsList;
}
