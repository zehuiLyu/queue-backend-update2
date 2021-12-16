package uofg.zehuilyu.queueapi.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "queue_customer")
@Data
@ApiModel(value = "customer information")
@EntityListeners(AuditingEntityListener.class)
@TableName("queue_customer")
public class Customer {
    @ApiModelProperty(value = "Customer ID", required = true)
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "Customer level", notes = "1->Supreme, 2->Gold, 3->Common",required = true)
    private Integer customer_level;
    @ApiModelProperty(value = "customer name", required = true)
    private String name;
    @ApiModelProperty(value = "Customer creation time")
    private Date create_time;
    @ApiModelProperty(value = "Customer information modification time")
    private Date update_time;





    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }


}
