package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserProfileGetRes findProfileUser (UserProfileGetReq req);
}
