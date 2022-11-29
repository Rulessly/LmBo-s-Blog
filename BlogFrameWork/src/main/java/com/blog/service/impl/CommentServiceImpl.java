package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.Comment;
import com.blog.service.CommentService;
import com.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author xyxiaobiao
* @description 针对表【sg_comment(评论表)】的数据库操作Service实现
* @createDate 2022-11-29 16:40:33
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




