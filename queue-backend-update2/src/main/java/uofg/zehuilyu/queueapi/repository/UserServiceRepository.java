package uofg.zehuilyu.queueapi.repository;


import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uofg.zehuilyu.queueapi.entity.UserCustomer;
import uofg.zehuilyu.queueapi.entity.UserService;

@Repository
public interface UserServiceRepository extends JpaRepository<UserService,Integer> {
    @Query(value = "select id,username,password from user_service where username = :username and password = :password",nativeQuery = true)
    UserService selectByUsername(@Param("username") String username, @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_service(username,password) VALUES (:username, :password)", nativeQuery = true)
    int saveUser(@Param("username")String username, @Param("password")String password);
}
