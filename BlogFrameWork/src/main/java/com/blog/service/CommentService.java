package com.blog.service;

import com.blog.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.ResponseResult;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xyxiaobiao
* @description 针对表【sg_comment(评论表)】的数据库操作Service
* @createDate 2022-11-29 16:40:33
*/
@Mapper
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}
