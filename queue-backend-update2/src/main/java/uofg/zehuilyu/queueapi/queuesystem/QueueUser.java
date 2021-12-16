package uofg.zehuilyu.queueapi.queuesystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Customer queuing information")
public class QueueUser {
    @ApiModelProperty(value = "Customers line up to get their numbers", notes = "1xxxxx->supreme;2xxxxx->gold;3xxxxx->common",required = true)
    private Integer queueNo;// The number of the user
    @ApiModelProperty(value = "Customer current Location")
    private String queueIndex;// the index of customer in the queue
    @ApiModelProperty(value = "Total number of customers in the queue")
    private String totalNum;  // Total queued customers
    @ApiModelProperty(value = "The number of people before customers",required = true)
        private String beforeNum;  // The number of people before customers
}
