CREATE DATABASE IF NOT EXISTS `sample` CHARACTER SET 'utf8';
use `sample`;

CREATE TABLE IF NOT EXISTS `t_auth_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(191) NOT NULL COMMENT '角色名称',
  `memo` varchar(300) DEFAULT '' COMMENT '角色描述',
  `create_time` datetime COMMENT '创建时间',
  `modify_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_name` (`name`)
) ENGINE = InnoDB COMMENT = ' 角色表' ;

