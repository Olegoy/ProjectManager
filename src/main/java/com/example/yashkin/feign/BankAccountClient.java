package com.example.yashkin.feign;


import com.example.yashkin.config.BankAccountClientConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "AccountManager-service",
        url = "${account.api-base-url}",
        configuration = BankAccountClientConfiguration.class
)
public interface BankAccountClient {

    @Operation(summary = "Проверка наличия операции по имени владельца счета и описанию")
    @PostMapping("/check/")
    public ResponseEntity<Boolean> checkOperationByOwners(@RequestParam("ownerName") String ownerName, @RequestParam("description") String description);
}
