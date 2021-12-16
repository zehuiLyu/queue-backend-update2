package uofg.zehuilyu.queueapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "service_window")
@Data
@ApiModel(value = "service information")
@EntityListeners(AuditingEntityListener.class)
@TableName("service_window")
public class ServiceWindow {

    @Id
    @ApiModelProperty(value = "Service's primary key id", required = true)
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "Service Name", required = true)
    private String name;
    @ApiModelProperty(value = "service state", notes = "0->closed;1->open", required = true)
    private Integer status;
    @ApiModelProperty(value = "Service Creation time")
    private Date create_time;
    @ApiModelProperty(value = "Service Modification time")
    private Date update_time;

}
