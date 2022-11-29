package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.User;
import com.blog.service.UserService;
import com.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xyxiaobiao
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-11-26 17:03:15
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




