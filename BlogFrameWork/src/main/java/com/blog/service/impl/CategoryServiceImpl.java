package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Vo.CategoryVo;
import com.blog.contants.SystemConstants;
import com.blog.domain.Article;
import com.blog.domain.Category;
import com.blog.domain.ResponseResult;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.mapper.CategoryMapper;
import com.blog.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author xyxiaobiao
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2022-11-23 15:29:42
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，查询正常发布的文章
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);
        //获取文章分类id，并且去重
        Set<Long> categoryid = articleList.stream().map(article -> article.getCategoryId()).collect(Collectors.toSet());

        //查询分类表,并且查询有正常发布的文章状态
        List<Category> categories = listByIds(categoryid);
        categories=categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus())).collect(Collectors.toList());

        //封装Dto
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}




