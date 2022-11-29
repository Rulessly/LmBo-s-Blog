package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Vo.ArticleDetailVo;
import com.blog.Vo.ArticleListVo;
import com.blog.Vo.HotArticleVo;
import com.blog.Vo.PageVo;
import com.blog.contants.SystemConstants;
import com.blog.domain.Article;
import com.blog.domain.Category;
import com.blog.domain.ResponseResult;
import com.blog.service.ArticleService;
import com.blog.mapper.ArticleMapper;
import com.blog.service.CategoryService;
import com.blog.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author xyxiaobiao
* @description 针对表【sg_article(文章表)】的数据库操作Service实现
* @createDate 2022-11-22 15:51:16
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{



    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page=new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
//        List<HotArticleDto> hotArticleDtos=new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleDto hotArticleDto=new HotArticleDto();
//            BeanUtils.copyProperties(article,hotArticleDto);
//            hotArticleDtos.add(hotArticleDto);
//        }
        List<HotArticleVo> articleDtos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleDtos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

//        List<Article> articles = page.getRecords();
//        //查询categoryName
//        articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());
        //articleId去查询articleName进行设置
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }

        List<Article> articles = page.getRecords();
        articles=articles.stream().map(article ->{
            Long id = article.getCategoryId();
            String name = categoryService.getById(id).getName();
            article.setCategoryName(name);
            return article;
        }).collect(Collectors.toList());

//        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);

        //转换成Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category byId = categoryService.getById(categoryId);
        if(byId!=null){
            articleDetailVo.setCategoryName(byId.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }
}




