CREATE TABLE `login_qrcode` (
  `qrcode`      VARCHAR(50) NOT NULL,
  `state`       INT(11)     NOT NULL DEFAULT '0'
  COMMENT '0：默认创建待扫描\n1：扫描成功登录\n2：超时被刷新',
  `crate_time`  TIMESTAMP   NOT NULL DEFAULT current_timestamp,
  `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`qrcode`),
  UNIQUE KEY `login_qrcode_qrcode_uindex` (`qrcode`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '扫码登录二维码随机部分';

# INSERT  INTO login_qrcode ()

# 演示中用的是付费版吗？MyBatisCodeHelper-Pro插件的免费版支持mapper中数据库表字段提示吗
#
# 是通过插件实现的 mapper中数据库表字段提示和表字段修改其他地方同时修改吗？