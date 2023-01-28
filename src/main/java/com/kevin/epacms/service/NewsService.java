package com.kevin.epacms.service;

import com.kevin.epacms.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.util.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface NewsService extends IService<News> {
    /**
     * 分页查询新闻
     *
     * @param currentPage   当前页面
     * @param pageSize      页面大小
     * @param conditions    查询条件
     * @return {@link Result}
     */
    Result getNewsByPage(Integer currentPage, Integer pageSize, List<ConditionVo> conditions);

    /**
     * 批量删除
     *
     * @param idsStr ids
     * @return {@link Result}
     */
    Result deleteBatch(String idsStr);

    /**
     * 添加单条新闻
     *
     * @param news 新闻
     * @return {@link Result}
     */
    Result addNews(News news);

    /**
     * 批量添加新闻
     *
     * @param newsList 新闻列表
     * @return {@link Result}
     */
    Result addBatchNews(List<News> newsList);

    /**
     * 爬取新闻
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param url         url
     * @param cssQuery    css查询
     * @return {@link Result}
     */
    Result crawlNews(Integer currentPage, Integer pageSize, String url, String cssQuery);

    /**
     * 通过id删除新闻
     *
     * @param id id
     * @return {@link Result}
     */
    Result deleteNewsById(Long id);
}
