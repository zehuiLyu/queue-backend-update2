package uofg.zehuilyu.queueapi.queuesystem;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import uofg.zehuilyu.queueapi.controller.WebSocket;

import java.util.Iterator;
import java.util.Set;
@Service
public class QueueService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private  NumberManager numberManager;


    /**
     * Determines if there is an element in the Zset
     * @return
     */
    public synchronized boolean isExistZset(String zsetName,String value) {
        boolean result = false;
        if(redisTemplate.opsForZSet().score(zsetName,value) != null)
            result = true;
        return result;
    }
    /**
     * Get the queue Zset name of the store according to StoreId
     * @param storeId
     * @return
     */
    public synchronized String getQueryZsetName(String storeId) {
        return "query" + "-" + "zset" + "-" + storeId;
    }

    /**
     * Get the queued list name according to StoreId
     * @param storeId
     * @return
     */
    public synchronized String getQueryListName(String storeId) {
        return "query" + "-" + "list" + "-" + storeId;
    }
    /**
     * Get the total number of queue users
     * @param queryZsetName queue Zset name
     * @return total number of queue users
     */
    public long curQueryNumber(String queryZsetName) {
        return redisTemplate.opsForZSet().zCard(queryZsetName);
    }

    /**
     * The operation of entrance the queue
     * @param userId  Queued customerId
     * @return Real-time queuing status of users
     */
    public synchronized JSONObject push(String storeId, String userId) {
        String queryZsetName = getQueryZsetName(storeId);
        String queryListName = getQueryListName(storeId);

        QueueDetail queryDetail = null;
        QueueUser queryUser = null;
        JSONObject resultJson = new JSONObject();

        if(storeId != null && !storeId.equals("") &&userId != null && !userId.equals("")) {
            if(! isExistZset(queryZsetName,userId)) {
                int score=numberManager.generateNewNumber(Integer.parseInt(userId));
                if(score==-1) {resultJson.put("msg","the user is not exist!");return resultJson;}
                queryDetail = new QueueDetail();
                queryDetail.setQueryName(queryZsetName);
                queryDetail.setQueueNo((int) score);
                queryDetail.setQueryUserId(userId);
                String queryDetailStr =  JSONObject.toJSON(queryDetail).toString();
                redisTemplate.opsForList().rightPush(queryListName,queryDetailStr);
                redisTemplate.opsForZSet().add(queryZsetName,userId,score);
                queryUser = new QueueUser();
                queryUser.setQueueNo((int) score);
                queryUser.setTotalNum(String.valueOf(curQueryNumber(queryZsetName)));
                queryUser.setQueueIndex(String.valueOf(redisTemplate.opsForZSet().rank(queryZsetName,userId) + 1));
                queryUser.setBeforeNum(String.valueOf(redisTemplate.opsForZSet().rank(queryZsetName,userId)));
                resultJson = (JSONObject) JSONObject.toJSON(queryUser);
                return resultJson;
            } else {
                resultJson.put("msg","The customer is already in the queue. Do not queue again.");

                return  resultJson;
            }
        } else {
            resultJson.put("msg","storeId and userId cannot be null!");
            return  resultJson;
        }
    }

    /**
     * Out of line - Can be out of queue from any position
     * @param storeId
     * @param userId
     * @return Returns detailed queuing data for the user
     */
    public synchronized JSONObject exit(String storeId, String userId) {

        JSONObject resultJson = new JSONObject();
        if(storeId != null && !storeId.trim().equals("") && userId != null && !userId.trim().equals("")) {
            String queryZsetName = getQueryZsetName(storeId);
            String queryListName = getQueryListName(storeId);

            if(isExistZset(queryZsetName,userId)) {
                long currIndex = redisTemplate.opsForZSet().rank(queryZsetName,userId);
                String removeValue = (String) redisTemplate.opsForList().index(queryListName,currIndex);
                redisTemplate.opsForZSet().remove(queryZsetName,userId);  // Remove user queuing data from Zset
                redisTemplate.opsForList().remove(queryListName,1,removeValue); // Remove user queuing details from list
                resultJson = JSONObject.parseObject(removeValue);
                return resultJson;
            } else {
                resultJson.put("msg","The queuing status of the user is not found, please queue up first！");
                return resultJson;
            }
        } else {
            resultJson.put("msg","storeId and userId cannot be null！");
            return resultJson;
        }
    }

    /**
     * Get out at the front of the queue
     * @param storeId
     * @return
     */
    public synchronized JSONObject pop(String storeId) {

        JSONObject resultJson = new JSONObject();
        if(storeId != null && !storeId.trim().equals("")) {

            String queryZsetName = getQueryZsetName(storeId);
            String queryListName = getQueryListName(storeId);
            Set<String> set=redisTemplate.opsForZSet().range(queryZsetName,0,0);
            String userId=null;

            if(set!=null&&!set.isEmpty()) {
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    userId = iterator.next();

                }
                String removeValue = (String) redisTemplate.opsForList().index(queryListName,0);
                redisTemplate.opsForZSet().remove(queryZsetName,userId); // 移除Zset的用户排队数据
                redisTemplate.opsForList().remove(queryListName,1,removeValue); // 移除list中的用户排队详情
                resultJson = JSONObject.parseObject(removeValue);

            } else {
                resultJson.put("msg","No queue for this service, please queue first!");
            }
            WebSocket.sendOneMessage(userId,resultJson.toString());
            return resultJson;
        } else {
            resultJson.put("msg","storeId can not be null！");
            return resultJson;
        }
    }
    /**
     * Real-time queuing
     * @param storeId
     * @param userId
     * @return Queuing status of users
     */
    public synchronized JSONObject realTimeQuery(String storeId,String userId) {
        String queryZsetName = getQueryZsetName(storeId);
        QueueUser queryUser = null;
        JSONObject resultJson = new JSONObject();

        if(storeId != null && !storeId.equals("") && userId != null && !userId.equals("")) {

            if(isExistZset(queryZsetName,userId)) {
                queryUser = new QueueUser();
                double score = redisTemplate.opsForZSet().score(queryZsetName,userId);
                queryUser.setTotalNum(String.valueOf(curQueryNumber(queryZsetName)));
                queryUser.setQueueIndex(String.valueOf(redisTemplate.opsForZSet().rank(queryZsetName,userId) + 1));
                queryUser.setBeforeNum(String.valueOf(redisTemplate.opsForZSet().rank(queryZsetName,userId)));
                queryUser.setQueueNo((int) score);
                resultJson = (JSONObject) JSONObject.toJSON(queryUser);
                return resultJson;
            } else {
                resultJson.put("msg","Please wait in line before checking the queue status of the user.");
                return resultJson;
            }
        } else {
            resultJson.put("msg","Store ID and user ID cannot be empty!！");
            return  resultJson;
        }
    }
    /**
     * clear the queue of storeId
     * @param storeId
     * @return JSONObject of message
     */
    public synchronized JSONObject clear(String storeId){
        JSONObject resultJson = new JSONObject();
        if(storeId != null && !storeId.equals("")) {
            String queryZsetName = getQueryZsetName(storeId);
            String queryListName = getQueryListName(storeId);
            boolean result1 = redisTemplate.delete(queryZsetName);
            boolean result2 = redisTemplate.delete(queryListName);
            if(result1 && result2) {
                    resultJson.put("msg","Queue data cleared!");
                    return resultJson;
                } else {
                    resultJson.put("msg","The queue has been emptied! No need to empty again!");
                    return resultJson;
                }
            } else {
                resultJson.put("msg","storeId cannot be empty!");
                return resultJson;
            }

    }

}
