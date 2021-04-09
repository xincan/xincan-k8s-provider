package cn.com.xincan.xincanframework.k8s.server.service.impl;

import cn.com.xincan.xincanframework.k8s.server.dto.UserPatchDto;
import cn.com.xincan.xincanframework.k8s.server.dto.UserSaveDto;
import cn.com.xincan.xincanframework.k8s.server.dto.UserSearchDto;
import cn.com.xincan.xincanframework.k8s.server.entity.UserPo;
import cn.com.xincan.xincanframework.k8s.server.mapper.IUserMapper;
import cn.com.xincan.xincanframework.k8s.server.service.IUserService;
import cn.com.xincan.xincanframework.k8s.server.vo.UserSearchVo;
import cn.com.xincan.xincanframework.k8s.utils.orika.OrikaUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * copyright (C), 2020, 心灿基础架构
 *
 * @author Jiangxincan
 * @version 0.0.1
 * @program xincan-user
 * 用户信息接口实现类
 * @create 2020/3/19 14:36
 * <author>                <time>                  <version>                   <description>
 * Jiangxincan         2020/3/19 14:36             0.0.1                         用户信息接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {


    @Resource
    private IUserMapper userMapper;

    /**
     *  查询所有用户信息
     * @author Jiangxincan
     * @date 2020/7/22 9:47
     * @return java.util.List<cn.com.xincan.xincanframework.entity.user.dto.UserSaveDto>
     */
    @Override
    public List<UserSearchVo> findAll() {
        List<UserPo> lists =  userMapper.selectList(null);
        return OrikaUtils.mapAsList(lists, UserSearchVo.class);

    }

    /**
     *  根据用户ID，查询用户详细信息
     * @param id 用户ID
     * @author Jiangxincan
     * @date 2020/7/22 9:48
     * @return cn.com.xincan.xincanframework.entity.user.dto.UserSaveDto
     */
    @Override
    public UserSearchVo findUserById(String id) {
        UserPo user = userMapper.selectById(id);
        return OrikaUtils.map(user, UserSearchVo.class);
    }

    /**
     *  分页查询用户信息
     * @param userSearchDto 用户信息实体类
     * @author Jiangxincan
     * @date 2020/7/22 9:48
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.com.xincan.xincanframework.entity.user.dto.UserSearchDto>
     */
    @Override
    public Page<UserSearchVo> page(UserSearchDto userSearchDto){
        Page<UserPo> page = new Page<>(userSearchDto.getPage(), userSearchDto.getLimit());
        LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userSearchDto.getLoginName()), UserPo::getLoginName, userSearchDto.getLoginName());
        page = userMapper.selectPage(page, queryWrapper);
        Page<UserSearchVo> record = new Page<>();
        record.setRecords(OrikaUtils.mapAsList(page.getRecords(), UserSearchVo.class));
        record.setTotal(page.getTotal());
        return record;

    }

    /**
     *  新增用户信息
     * @param userSaveDto 新增用户信息实体类
     * @author Jiangxincan
     * @date 2020/7/22 9:49
     * @return cn.com.xincan.xincanframework.entity.user.dto.UserSaveDto
     */
    @Override
    public UserSearchVo save(UserSaveDto userSaveDto) {
        UserPo user = OrikaUtils.map(userSaveDto, UserPo.class);
        userMapper.insert(user);
        return OrikaUtils.map(user, UserSearchVo.class);
    }

    /**
     *  修改用户信息
     * @param userPatchDto 修改用户信息实体类
     * @author Jiangxincan
     * @date 2020/7/22 9:49
     * @return cn.com.xincan.xincanframework.entity.user.dto.UserSearchVo
     */
    @Override
    public UserSearchVo patch(UserPatchDto userPatchDto) {
        UserPo userPo = OrikaUtils.map(userPatchDto, UserPo.class);
        userMapper.updateById(userPo);
        return OrikaUtils.map(userPatchDto, UserSearchVo.class);
    }

    /**
     *  删除用户信息
     * @param id 用户ID
     * @author Jiangxincan
     * @date 2020/7/22 9:49
     * @return int
     */
    @Override
    public int delete(String id) {
        LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPo::getId, id);
        return userMapper.delete(queryWrapper);
    }
}
