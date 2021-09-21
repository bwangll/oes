package top.oes.user.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.StringArrayType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author bwang
 * @version 1.0.0
 * @Description Role
 * @CreateTime 2021/9/12 7:35 下午
 */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@DynamicUpdate
@TypeDefs({
    @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
@Table(name = "tbl_role", schema = "public", catalog = "oes")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    @ApiModelProperty("角色名称")
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Type(type = "string-array")
    @Column(name = "permissions", length = -1)
    private String[] permissions;



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return roleId == role.roleId && Objects.equals(roleName, role.roleName) && Arrays.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, Arrays.hashCode(permissions));
    }

    @Override
    public String toString() {
        return "Role{" +
            "roleId=" + roleId +
            ", roleName='" + roleName + '\'' +
            ", permissions=" + Arrays.toString(permissions) +
            '}';
    }
}

