package uofg.zehuilyu.queueapi.queuesystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class QueueDetail {

    private String queryName;  // queue name
    private Integer queueNo;  // customer number
    private String queryTime;  // Queue start time
    private String queryUserId;  // Queued customerId

    public QueueDetail() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.queryTime = sdf.format(new Date());
    }
}
