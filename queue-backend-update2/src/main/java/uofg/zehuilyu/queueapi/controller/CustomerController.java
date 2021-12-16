package uofg.zehuilyu.queueapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uofg.zehuilyu.queueapi.entity.Customer;
import uofg.zehuilyu.queueapi.repository.CustomerRepository;
import java.util.Date;

@Api(value = "Customer Detail Management", tags = "Customer Detail Management API", description = "Customer Detail")
@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    /**
     * Get all customers list.
     *
     * @return the list
     */
    @ApiOperation(value = "list all Customer Json", notes = "listCustomerJson", produces = "application/json")
    @GetMapping("/customers")
    public JSONArray getAllCustomers(){
        return (JSONArray) JSONObject.toJSON(customerRepository.findAll());
    }

    /**
     * Gets users by id.
     *
     * @param customerId the customer id
     * @return the JSONObject of customer
     * @throws ResourceNotFoundException the resource not found exception
     */
    @ApiOperation(value = "get customer Json by Id", notes = "getCustomerJson", produces = "application/json")
    @GetMapping("/customers/{id}")
    public ResponseEntity<JSONObject> getUsersById(@PathVariable(value = "id") Integer customerId)
            throws ResourceNotFoundException {
        Customer customer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + customerId));
        JSONObject res=(JSONObject) JSONObject.toJSON(customer);

        return ResponseEntity.ok().body(res);
    }

    /**
     * Create a new Customer
     * @param customerJSON the customer's JSONObject
     * @return the ResponseEntity the customer's JSONObject
     */
    @ApiOperation(value = "create Customer by Json", notes = "CreateCustomerJson", produces = "application/json")
    @PostMapping("/customers")
    public ResponseEntity<JSONObject> createUser(@Validated @RequestBody JSONObject customerJSON) {


        return ResponseEntity.ok().body(
                (JSONObject) JSONObject.toJSON(
                        customerRepository.save(
                                JSON.toJavaObject(customerJSON,Customer.class)
                        )
                )
        );
    }


    /**
     * Update Customer response entity.
     * @param customerId the customer id
     * @param customerDetailsJSON the customer details JSON send back by front
     * @return the ResponseEntity the customer's JSONObject
     * @throws ResourceNotFoundException
     */
    @ApiOperation(value = "update Customer Details by Json", notes = "UpdateCustomerJson", produces = "application/json")
    @PutMapping("/customers/{id}")
    public ResponseEntity<JSONObject> updateUser(
            @PathVariable(value = "id") Integer customerId, @Validated @RequestBody JSONObject customerDetailsJSON)
            throws ResourceNotFoundException {

        Customer customer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found on :: " + customerId));
        Customer customerDetails = JSONObject.toJavaObject(customerDetailsJSON,Customer.class);
        customer.setName(customerDetails.getName());
        customer.setCustomer_level(customerDetails.getCustomer_level());
        customer.setUpdate_time(new Date());
        return ResponseEntity.ok((JSONObject) JSONObject.toJSON(customerRepository.save(customer)));
    }

    /**
     * Delete user map.
     *
     * @param customerId the customer id
     * @return JSONObject, key "success" and value is Boolean true.
     * @throws Exception the exception
     */
    @ApiOperation(value = "Delete Customer Details by id", notes = "Delete Customer by id", produces = "application/json")
    @DeleteMapping("/customers/{id}")
    public JSONObject deleteUser(@PathVariable(value = "id") Integer customerId) throws Exception {
        Customer user =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + customerId));

        customerRepository.delete(user);
        JSONObject response=new JSONObject();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

