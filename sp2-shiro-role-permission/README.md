### sp2-webtoken-jjwt-shiro
springboot与shiro的整合, 使用这个是一个比较完整的使用了role, permission与user的关联关系的demo, user->role->permission, user->role(多), 
role->permission(多), 一个用户可以有多个角色, 一个角色可以有多个权限, 相比于sp2-shiro-authority, 这个对于user, role, permisssion三者是
分开的, 之间的联系使用user->role<-permission, 这样的关联, 有中间表作为联结, 这样可以单独管理权限与角色, 与sp2-shiro-authority相比更加合理,
sp2-shiro-authority是把所有的用户, 权限, 角色都放入到了同一个user表之中, 这样不方便管理。
