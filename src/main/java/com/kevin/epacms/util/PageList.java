package com.kevin.epacms.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页结果集
 *
 * @author kevin
 * @since 2022-11-11
 */
@Data
@ApiModel("封装分页结果集")
@NoArgsConstructor
public class PageList implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("每页数量")
    private Long currentPage;

    @ApiModelProperty("当前页")
    private Long pageSize;

    @ApiModelProperty("数据总条数")
    private Long totalCount;

    @ApiModelProperty("总页数")
    private Long totalPage;

    @ApiModelProperty("数据集")
    private List dataList = new ArrayList<>();

    public PageList(IPage page){
        this.dataList.addAll(page.getRecords());
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
        this.pageSize = page.getSize();
        this.currentPage = page.getCurrent();
    }
}
