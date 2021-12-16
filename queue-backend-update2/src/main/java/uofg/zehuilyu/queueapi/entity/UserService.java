package uofg.zehuilyu.queueapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_service")
@Data
@ApiModel(value = "service provider")
@EntityListeners(AuditingEntityListener.class)
@TableName("user_service")
public class UserService {
    @Id
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
}
