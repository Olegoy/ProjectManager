package com.example.yashkin.feign;


import com.example.yashkin.config.BankAccountClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "AccountManager-service",
        url = "${account.api-base-url}",
        configuration = BankAccountClientConfiguration.class
)
public interface BankAccountClient {

    @PostMapping("/check/")
    public ResponseEntity<Boolean> checkOperationByOwners(@RequestParam("ownerName") String ownerName, @RequestParam("description") String description);
}
