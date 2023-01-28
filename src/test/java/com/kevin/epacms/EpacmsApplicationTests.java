package com.kevin.epacms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kevin.epacms.entity.Leave;
import com.kevin.epacms.entity.User;
import com.kevin.epacms.mapper.LeaveMapper;
import com.kevin.epacms.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class EpacmsApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LeaveMapper leaveMapper;

    @Test
    void contextLoads() {
//        List<Leave> leaves = leaveMapper.leaveListByUid(23);
//        leaves.forEach(System.out::println);
//        IPage<User> page = userMapper.selectUserPageLeaveByWrapper(new Page<>(1, 20), new LambdaQueryWrapper<>());
//        page.getRecords().forEach(System.out::println);
    }
}
