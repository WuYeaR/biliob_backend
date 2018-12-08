package com.jannchie.biliob.repository;

import com.jannchie.biliob.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author jannchie
 */
public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {

	/**
	 * 判断用户名是否已存在
	 *
	 * @param name 用户名
	 * @return 0代表不存在，1代表已存在
	 */
	Integer countByName(String name);

	/**
	 * 通过名称查询用户
	 *
	 * @param name 用户名
	 * @return 用户对象
	 */
	User findByName(String name);

	/**
	 * 获取用户信息（不包括密码）
	 *
	 * @param name 用户名
	 * @return 用户对象
	 */
	@Query(value = "{name:?0}", fields = "{password:0}")
	User getUserInfo(String name);
}