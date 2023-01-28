package com.kevin.epacms.service;

import com.kevin.epacms.entity.Upload;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface UploadService extends IService<Upload> {

    /**
     * 上传文件
     * @param uploadFile 文件
     * @return 文件url
     */
    Result uploadFile(MultipartFile uploadFile);

    /**
     * 下载文件
     * @param fileUuid 文件Uuid
     * @param response 响应数据
     */
    void download(String fileUuid, HttpServletResponse response);

    /**
     * 根据id删除文件
     *
     * @param id id
     * @return {@link Result}
     */
    Result deleteFileById(Long id);

    /**
     * 批量逻辑删除
     *
     * @param idsStr ids
     * @return {@link Result}
     */
    Result deleteBatch(String idsStr);

    /**
     * 分页查询文件信息
     *
     * @param currentPage   当前页面
     * @param pageSize      页面大小
     * @param conditions    查新条件
     * @return {@link Result}
     */
    Result getFilesByPage(Integer currentPage, Integer pageSize, List<ConditionVo> conditions);

}
