package com.blog.service;

import com.blog.domain.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.ResponseResult;

/**
* @author xyxiaobiao
* @description 针对表【sg_link(友链)】的数据库操作Service
* @createDate 2022-11-26 16:38:04
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
