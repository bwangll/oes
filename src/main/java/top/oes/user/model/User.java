package top.oes.user.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author bwang
 * @version 1.0.0
 * @Description User
 * @CreateTime 2021/9/12 7:35 下午
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tbl_user", schema = "public", catalog = "oes")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @ApiModelProperty("登陆名")
    @NotEmpty(message = "登陆名不可为空")
    @Column(name = "school_number", nullable = false, length = 100)
    private String schoolNumber;

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不可为空")
    @Column(name = "user_name", nullable = false)
    private String userName;

    @ApiModelProperty("用户密码")
    @NotEmpty
    @Column(name = "user_password", nullable = false, length = 1024)
    private String password;

    @ApiModelProperty("手机号")
    @Column(name = "user_mobile", length = 20)
    private String userMobile;

    @ApiModelProperty("邮箱")
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_level")
    private Integer userLevel;

    @Column(name = "user_icon", length = 1024)
    private String userIcon;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    @Column(name = "modify_date")
    private Timestamp modifyDate;

    @Column(name = "user_enable")
    private Boolean userEnable;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "role_id")
    private Long roleId;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinTable(name = "tbl_user_group_user", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "user_group_id"))
    private UserGroup userGroup;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(role.getPermissions()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, schoolNumber, userName, password, userMobile,
            userEmail, userLevel, userIcon, createDate, modifyDate, userEnable, lastLogin);
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", schoolNumber='" + schoolNumber + '\'' +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", userMobile='" + userMobile + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userLevel=" + userLevel +
            ", userIcon='" + userIcon + '\'' +
            ", createDate=" + createDate +
            ", modifyDate=" + modifyDate +
            ", userEnable=" + userEnable +
            ", lastLogin=" + lastLogin +
            ", role=" + role +
            ", userGroup=" + userGroup +
            '}';
    }
}
