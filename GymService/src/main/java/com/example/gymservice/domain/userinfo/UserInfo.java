package com.example.gymservice.domain.userinfo;


import com.example.gymservice.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "user_info")
@Entity
public class UserInfo extends BaseEntity {
    @Id
    private String userId;
    private String userName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String phone;
    private String meta;

    protected UserInfo() {
    }

    private UserInfo(String userId, String userName, UserStatus status, String phone, String meta) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.phone = phone;
        this.meta = meta;
    }

    public static UserInfo of(
            String userId,
            String userName,
            UserStatus status,
            String phone,
            String meta
    ) {
        return new UserInfo(
                userId,
                userName,
                status,
                phone,
                meta
        );
    }
}
