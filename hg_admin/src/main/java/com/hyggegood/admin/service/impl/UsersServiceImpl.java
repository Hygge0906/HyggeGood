package com.hyggegood.admin.service.impl;

import com.hyggegood.admin.entity.Users;
import com.hyggegood.admin.mapper.UsersMapper;
import com.hyggegood.admin.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HyggeGood-YangDengYu
 * @since 2024-11-21
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
