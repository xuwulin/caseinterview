# service(centos6)
- 注册在系统中的标准化程序

- 有方便统一的管理方式（常用的方法）

  ​	service 服务名 start

  ​	service 服务名 stop

  ​	service 服务名 restart

  ​	service 服务名 reload

  ​	service 服务名 status

- 查看服务的方法：/etc/init.d/服务名

- 通过chkconfig命令设置自启动

  查看服务 chkconfig --list|grep xxx

  chkconfig --level 5 服务名 on



# systemctl(centos7)
- 注册在系统中的标准化程序

- 有方便统一的管理方式（常用的方法）

  systemctl start 服务名(xxx.service)

  systemctl restart 服务名(xxx.service)

  systemctl stop 服务名(xxx.service)

  systemctl reload 服务名(xxx.service)

  systemctl status 服务名(xxx.service)

- 查看服务的方法 /usr/lib/systemd/system

- 查看服务的命令

  systemctl list-unit-files

  systemctl --type service

- 通过systemctl命令设置自启动

  自启动systemctl enable service_name

  不自启动systemctl disable service_name