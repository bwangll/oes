package top.oes.user.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author bwang
 * @version 1.0.0
 * @Description UserGroup
 * @CreateTime 2021/9/12 7:35 下午
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tbl_user_group", schema = "public", catalog = "oes")
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id", nullable = false)
    private long userGroupId;

    @Column(name = "user_group_name", nullable = false)
    private String userGroupName;

    @Column(name = "parent_id")
    private long parentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserGroup userGroup = (UserGroup) o;
        return userGroupId == userGroup.userGroupId && parentId == userGroup.parentId && Objects.equals(userGroupName, userGroup.userGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, userGroupName, parentId);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
            "userGroupId=" + userGroupId +
            ", userGroupName='" + userGroupName + '\'' +
            ", parentId=" + parentId +
            '}';
    }
}
