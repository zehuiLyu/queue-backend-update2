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
@Table(name = "user_customer")
@Data
@ApiModel(value = "customer")
@EntityListeners(AuditingEntityListener.class)
@TableName("user_customer")
public class UserCustomer {
    @Id
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
}
