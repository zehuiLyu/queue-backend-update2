package uofg.zehuilyu.queueapi.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uofg.zehuilyu.queueapi.entity.Customer;
import uofg.zehuilyu.queueapi.entity.ServiceWindow;

@Repository
public interface WindowRepository extends JpaRepository<ServiceWindow,Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO service_window(name) VALUES (:name)", nativeQuery = true)
    int saveUser(@Param("name")String name);
}
