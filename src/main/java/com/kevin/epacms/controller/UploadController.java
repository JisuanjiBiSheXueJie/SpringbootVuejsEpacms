package com.kevin.epacms.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.kevin.epacms.entity.Upload;
import com.kevin.epacms.entity.dto.SearchDto;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.service.UploadService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  文件前端控制器
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Api(tags = "文件管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @Resource
    private UploadService uploadService;

    @ApiOperation("分页查询文件信息")
    @PostMapping("/page")
    public Result getFilesByPage(@RequestBody SearchDto searchConditions,
                                 @RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize){
        List<ConditionVo> conditions = searchConditions.getConditions();
        return uploadService.getFilesByPage(currentPage, pageSize, conditions);
    }

    @ApiOperation("上传文件")
    @PostMapping
    public Result uploadFile(@RequestParam("file") MultipartFile file){
        return uploadService.uploadFile(file);
    }

    @ApiOperation("下载文件")
    @GetMapping("/{fileUuid}")
    public void download(@PathVariable("fileUuid") String fileUuid, HttpServletResponse response){
        uploadService.download(fileUuid, response);
    }

    @ApiOperation("根据id逻辑删除文件")
    @DeleteMapping
    public Result deleteFileById(@RequestParam("id") Long id){
        return uploadService.deleteFileById(id);
    }

    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestParam("idsStr") String idsStr){
        return uploadService.deleteBatch(idsStr);
    }
}
