CREATE TABLE IF NOT EXISTS tbl_role
(
    role_id     BIGSERIAL
        CONSTRAINT tbl_role_pkey
            PRIMARY KEY,
    role_name   VARCHAR(255) NOT NULL,
    permissions CHARACTER VARYING[]
);

COMMENT ON TABLE tbl_role IS '角色表';

COMMENT ON COLUMN tbl_role.role_name IS '角色名称';

COMMENT ON COLUMN tbl_role.permissions IS '权限列表';

ALTER TABLE tbl_role
    OWNER TO postgres;

/**
  后续进行权限的补充
  1、目前实现：用户登陆注册功能、权限认证功能
 */
INSERT INTO tbl_role(role_name, permissions)
VALUES ('ADMIN', ARRAY ['ROLE_ADMIN', 'ROLE_USER']),
       ('TEACHER', ARRAY ['ROLE_USER']),
       ('STUDENT', ARRAY ['ROLE_NONE']);


CREATE TABLE IF NOT EXISTS tbl_user
(
    user_id       BIGSERIAL
        CONSTRAINT tbl_user_pkey
            PRIMARY KEY,
    school_number    VARCHAR(100)               NOT NULL,
    user_name     VARCHAR(255)               NOT NULL,
    user_password VARCHAR(1024)              NOT NULL,
    user_mobile   VARCHAR(20),
    user_email    VARCHAR(255),
    user_level    INTEGER,
    user_icon     VARCHAR(1024),
    create_date   TIMESTAMP(0) DEFAULT NOW() NOT NULL,
    modify_date   TIMESTAMP(0),
    user_enable   BOOLEAN      DEFAULT TRUE,
    last_login    TIMESTAMP(0),
    role_id       BIGINT
        CONSTRAINT tbl_user_role_id_fkey
            REFERENCES tbl_role
            ON UPDATE RESTRICT ON DELETE RESTRICT
);

COMMENT ON TABLE tbl_user IS '用户表';

COMMENT ON COLUMN tbl_user.school_number IS '学号';

COMMENT ON COLUMN tbl_user.user_name IS '用户名';

COMMENT ON COLUMN tbl_user.user_password IS '密码';

COMMENT ON COLUMN tbl_user.user_mobile IS '手机号';

COMMENT ON COLUMN tbl_user.user_email IS '邮箱';

COMMENT ON COLUMN tbl_user.user_level IS '年级';

COMMENT ON COLUMN tbl_user.user_icon IS '头像';

COMMENT ON COLUMN tbl_user.create_date IS '创建时间';

COMMENT ON COLUMN tbl_user.modify_date IS '修改时间';

COMMENT ON COLUMN tbl_user.user_enable IS '账户启用';

COMMENT ON COLUMN tbl_user.last_login IS '最后登陆时间';

COMMENT ON COLUMN tbl_user.role_id IS '角色ID';

ALTER TABLE tbl_user
    OWNER TO postgres;

--新增基础用户 admin管理员

INSERT INTO tbl_user(school_number, user_name, user_password, create_date, role_id)
VALUES ('admin', 'admin', '$2a$10$Q9meGlnwGaG9X7m1img2qOUUiz6vQzxAXxYUu7AYn0.Vnra5RNn0y', NOW(), 1);


CREATE TABLE IF NOT EXISTS tbl_user_group
(
    user_group_id   BIGSERIAL
        CONSTRAINT tbl_user_group_pkey
            PRIMARY KEY,
    user_group_name VARCHAR(255) NOT NULL,
    parent_id BIGINT NOT NULL
);

COMMENT ON TABLE tbl_user_group IS '用户组表';

COMMENT ON COLUMN tbl_user_group.user_group_name IS '用户组名';

ALTER TABLE tbl_user_group
    OWNER TO postgres;

--默认数据用于测试
INSERT INTO tbl_user_group(user_group_name, parent_id)
VALUES ('ROOT', 0),
       ('计算机学院', 1),
       ('计算机系', 2),
       ('软件工程一班', 3),
       ('软件工程二班', 2);


CREATE TABLE IF NOT EXISTS tbl_user_group_user
(
    id            BIGSERIAL
        CONSTRAINT tbl_user_group_user_pkey
            PRIMARY KEY,
    user_id       BIGINT NOT NULL
        CONSTRAINT tbl_user_group_user_user_id_fkey
            REFERENCES tbl_user,
    user_group_id BIGINT NOT NULL
        CONSTRAINT tbl_user_group_user_user_group_id_fkey
            REFERENCES tbl_user_group
);

COMMENT ON TABLE tbl_user_group_user IS '用户-用户组关联表';

COMMENT ON COLUMN tbl_user_group_user.user_id IS '用户id';

COMMENT ON COLUMN tbl_user_group_user.user_group_id IS '用户组id';

ALTER TABLE tbl_user_group_user
    OWNER TO postgres;

INSERT INTO tbl_user_group_user(user_id, user_group_id)
VALUES (1, 1);
