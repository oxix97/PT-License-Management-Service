package com.example.gymservice.repository;

import com.example.gymservice.domain.userinfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
