package com.ming;

import java.util.Collection;


/**脚本自动生成 mapper
 *
 * @author ming
 * @date 2018-04-22 20:08:49
 */
public interface  MyUserMapper{

    int insert(MyUser t);

    int insertAll(Collection<MyUser> ts);

    int update(MyUser t);

    MyUser findById(Integer id);

    Collection<MyUser> findAll();

    int deleteById(Integer id);

}