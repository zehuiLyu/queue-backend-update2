package uofg.zehuilyu.queueapi.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uofg.zehuilyu.queueapi.entity.Customer;
import uofg.zehuilyu.queueapi.entity.UserCustomer;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>, CrudRepository<Customer,Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO queue_customer(customer_level,name) VALUES (:customer, :name)", nativeQuery = true)
    int saveUser(@Param("customer")String customer, @Param("name")String name);

    @Query(value = "select id,username,password from queue_customer  where `username` = :username and `password` = :password",nativeQuery = true)
    UserCustomer selectByUsername(@Param("username")String username, @Param("password")String password);

}
