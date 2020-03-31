CREATE TABLE `jd_item` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`spu`  bigint(15) default NULL ,
`sku`  bigint(15)  default NULL ,
`title`  varchar(100) default NULL ,
`price`  bigint(10) default NULL ,
`pic`  varchar(200) default NULL ,
`url`  varchar(200) default NULL ,
`created`  datetime default NULL ,
`updated`  datetime default NULL ,
PRIMARY KEY (`id`)
)
;