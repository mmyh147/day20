package com.example.bankmanagement.Controller;


import com.example.bankmanagement.API.ApiResponse;
import com.example.bankmanagement.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    ArrayList<Customer> customers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Customer> getAll() {

        return customers;
    }

    @PostMapping("/post")
    public ApiResponse newCustomer(@RequestBody Customer customer) {

        customers.add(customer);
        return new ApiResponse("successfully added the Customer");
    }

    @PutMapping("update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        if (customers.size() - 1 >= index) {

            customers.set(index, customer);
            return new ApiResponse("Customer updated successfully");
        } else {
            return new ApiResponse("There no ID with number : " + index);
        }

    }


    @DeleteMapping("delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        if (customers.size() - 1 >= index) {

            customers.remove(index);
            return new ApiResponse("Customer removed successfully");
        } else {
            return new ApiResponse("there no ID with number: " + index);
        }
    }

    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index, @RequestBody Map<String, Double> requestBody) {
        double deposit = requestBody.get("deposit");
        if (customers.size() - 1 >= index) {
            customers.get(index).setBalance(customers.get(index).getBalance() + deposit);
            return new ApiResponse("successfully deposit " + deposit + " new balance " + customers.get(index).getBalance());
        } else {
            return new ApiResponse("there no ID with number: " + index);

        }
    }

    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index, @RequestBody Map<String, Double> requestBody) {
        double withdraw = requestBody.get("withdraw");
        if (customers.size() - 1 >= index) {
            if (withdraw <= customers.get(index).getBalance()){

                customers.get(index).setBalance(customers.get(index).getBalance() - withdraw);
                return new ApiResponse("successfully withdraw " + withdraw + " new balance " + customers.get(index).getBalance());

            }else{
                return new ApiResponse("You don't have the amount to withdraw! ");
            }
        } else {
            return new ApiResponse("there no ID with number: " + index);

        }
    }




}
