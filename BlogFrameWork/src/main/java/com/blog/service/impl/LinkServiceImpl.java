package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Vo.LinkVo;
import com.blog.contants.SystemConstants;
import com.blog.domain.Link;
import com.blog.domain.ResponseResult;
import com.blog.service.LinkService;
import com.blog.mapper.LinkMapper;
import com.blog.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xyxiaobiao
* @description 针对表【sg_link(友链)】的数据库操作Service实现
* @createDate 2022-11-26 16:38:04
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

    @Override
    public ResponseResult getAllLink() {
        //查询所以审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = list(queryWrapper);
        //转换成Vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }
}




