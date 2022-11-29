package com.blog.service;

import com.blog.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.ResponseResult;

/**
* @author xyxiaobiao
* @description 针对表【sg_article(文章表)】的数据库操作Service
* @createDate 2022-11-22 15:51:16
*/
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
