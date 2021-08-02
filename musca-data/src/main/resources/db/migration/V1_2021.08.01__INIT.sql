# create schema if not exists `musca` default charset utf8mb4 collate utf8mb4_unicode_ci;
# create user `musca`@'%' identified by 'musca';
# grant all on musca.* to musca@'%';
# use `musca`;

create table if not exists `t_attitude` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `type`       int unsigned     not null default 0 comment '类型',
    `score`      int unsigned     not null default 0 comment '程度',
    `user_code`  varchar(16)      not null default '' comment '用户编码',
    `target`     int unsigned     not null default 0 comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '态度表';

create unique index `unq_attitude_code` on `t_attitude`(`code`);
create index `idx_attitude_user_code` on `t_attitude`(`user_code`);
create index `idx_attitude_target_user_code` on `t_attitude`(`target_code`, `user_code`, `target`);

create table if not exists `t_comment` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `type`       int unsigned     not null default 0 comment '模块 类型',
    `content`    varchar(1024)    not null default '' comment '备注，说明等',
    `user_code`  varchar(16)      not null default '' comment '用户编码',
    `target`     int unsigned     not null default 0 comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '评论表';

create unique index `unq_comment_code` on `t_comment`(`code`);
create index `idx_comment_user_code` on `t_comment`(`user_code`);
create index `idx_comment_target_user_code` on `t_comment`(`target_code`, `user_code`, `target`);

create table if not exists `t_follow` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `type`       int unsigned     not null default 0 comment '类型',
    `status`     int unsigned     not null default 0 comment '1关注0取关',
    `user_code`  varchar(16)      not null default '' comment '用户编码',
    `target`     int unsigned     not null default 0  comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '关注表';

create unique index `unq_follow_code` on `t_follow`(`code`);
create index `idx_follow_user_code` on `t_follow`(`user_code`);
create index `idx_follow_target_user_code` on `t_follow`(`target_code`, `user_code`, `target`);


create table if not exists `t_mention` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `type`       int unsigned     not null default 0 comment '模块 类型',
    `user_code`  varchar(16)      not null default '' comment '用户编码',
    `target`     int unsigned     not null default 0 comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `where`     int unsigned      not null default 0 comment '对象类型',
    `where_code`varchar(16)      not null default '' comment '对象编码',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '提及表';

create unique index `unq_mention_code` on `t_mention`(`code`);
create index `idx_mention_user_code` on `t_mention`(`user_code`);
create index `idx_mention_target_user_code` on `t_mention`(`target_code`, `user_code`, `target`);
create index `idx_mention_where_user_code` on `t_mention`(`where_code`, `user_code`, `where`);

create table if not exists `t_prosecute` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `type`       int unsigned     not null default 0 comment '类型',
    `status`     int unsigned     not null default 0 comment '状态',
    `cause`      int unsigned     not null default 0 comment ' 默认为0',
    `reason`     int unsigned     not null default 0 comment ' 默认为0',
    `user_code`  varchar(16)      not null default '' comment '用户编码',
    `target`     int unsigned     not null default 0 comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `description`varchar(1024)    not null default '' comment '备注，说明等',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '举报表';

create unique index `unq_prosecute_code` on `t_prosecute`(`code`);
create index `idx_prosecute_user_code` on `t_prosecute`(`user_code`);
create index `idx_prosecute_target_user_code` on `t_prosecute`(`target_code`, `user_code`, `target`);


create table if not exists `t_shared` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `type`       int unsigned     not null default 0 comment '类型',
    `user_code`  varchar(16)      not null default '' comment '用户编码',
    `target`     int unsigned     not null default 0 comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `platform`   varchar(64)     not null default 0 comment '平台',
    `platform_code`varchar(16)      not null default '' comment '平台编码',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '分享表';

create unique index `unq_shared_code` on `t_shared`(`code`);
create index `idx_shared_user_code` on `t_shared`(`user_code`);
create index `idx_shared_target_user_code` on `t_shared`(`target_code`, `user_code`, `target`);
create index `idx_shared_where_user_code` on `t_shared`(`platform_code`, `user_code`, `platform`);


create table if not exists `t_topic` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '话题编码',
    `name`       varchar(64)      not null default '' comment '话题名称',
    `type`       int unsigned     not null default 0 comment '话题类型',
    `description`varchar(1024)    not null default '' comment '备注，说明等',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '话题表';

create unique index `unq_topic_code` on `t_topic`(`code`);

create table if not exists `t_topic_used` (
    `id`         bigint unsigned  not null auto_increment comment 'id for index',
    `code`       varchar(16)      not null default '' comment '编码',
    `topic_code` varchar(16)      not null default '' comment '话题编码',
    `target`     int unsigned     not null default 0 comment '对象类型',
    `target_code`varchar(16)      not null default '' comment '对象编码',
    `deleted`    tinyint unsigned not null default 0 comment '软删除标示:0否1是',
    `created_at` datetime         not null default current_timestamp comment '创建时间',
    `updated_at` datetime         not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`) using btree
) engine = InnoDB comment '话题使用表';

create unique index `unq_topic_used_code` on `t_topic_used`(`code`);
create index `idx_topic_used_topic_code` on `t_topic_used`(`topic_code`);
create index `idx_topic_used_target_topic_code` on `t_topic_used`(`target_code`, `topic_code`, `target`);
