package uofg.zehuilyu.queueapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uofg.zehuilyu.queueapi.entity.ServiceWindow;
import uofg.zehuilyu.queueapi.entity.UserService;

public interface WindowMapper extends BaseMapper<ServiceWindow> {

}
