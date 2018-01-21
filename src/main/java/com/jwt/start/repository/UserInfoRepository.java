package com.jwt.start.repository;

import com.jwt.start.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/1/21  15:36
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    UserInfo findUserInfoById(int id);

    List<UserInfo> findUserInfoByRole(String role);

    UserInfo findUserInfoByName(String name);

    @Query(value = "select * from t_user limit ?1", nativeQuery = true)
    List<UserInfo> findAllUsersByCount(int count);
}
