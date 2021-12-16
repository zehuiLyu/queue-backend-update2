package uofg.zehuilyu.queueapi.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uofg.zehuilyu.queueapi.entity.UserCustomer;

@Repository
public interface UserCustomerRepository extends JpaRepository<UserCustomer,Integer> , CrudRepository<UserCustomer,Integer> {
    @Query(value = "select id,username,password from user_customer  where `username` = :username and `password` = :password",nativeQuery = true)
    UserCustomer selectByUsername(@Param("username")String username, @Param("password")String password);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_customer(username,password) VALUES (:username, :password)", nativeQuery = true)
    int saveUser(@Param("username")String username, @Param("password")String password);
}
