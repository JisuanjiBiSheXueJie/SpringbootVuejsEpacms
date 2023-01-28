package com.kevin.epacms.controller;

import cn.hutool.core.bean.BeanUtil;
import com.kevin.epacms.entity.News;
import com.kevin.epacms.entity.dto.NewsDto;
import com.kevin.epacms.entity.dto.NewsListDto;
import com.kevin.epacms.entity.dto.SearchDto;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.service.NewsService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Api(tags = "新闻管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @ApiOperation("分页查询新闻")
    @PostMapping("/page")
    public Result getNewsByPage(@RequestBody SearchDto searchConditions,
                                @RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize){
        List<ConditionVo> conditions = searchConditions.getConditions();
        return newsService.getNewsByPage(currentPage, pageSize, conditions);
    }

    @ApiOperation("爬取新闻")
    @GetMapping("/crawl")
    public Result crawlNews(@RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                            @RequestParam("url") String url,
                            @RequestParam(value = "cssQuery", required = false) String cssQuery){
        return newsService.crawlNews(currentPage, pageSize, url, cssQuery);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestParam("idsStr") String idsStr){
        return newsService.deleteBatch(idsStr);
    }

    @ApiOperation("删除新闻")
    @DeleteMapping
    public Result deleteNewsById(@RequestParam("id") Long id){
        return newsService.deleteNewsById(id);
    }

    @ApiOperation("添加单条新闻")
    @PostMapping
    public Result addNews(@RequestBody News news){
        return newsService.addNews(news);
    }

    @ApiOperation("批量添加新闻")
    @PostMapping("/batch")
    public Result addBatchNews(@RequestBody NewsListDto newsList){
        return newsService.addBatchNews(BeanUtil.copyToList(newsList.getNewsList(), News.class));
    }
}
