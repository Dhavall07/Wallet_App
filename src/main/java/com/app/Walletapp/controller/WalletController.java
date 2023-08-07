package com.app.Walletapp.controller;

import com.app.Walletapp.entity.Wallet;
import com.app.Walletapp.service.WalletService;
import com.app.Walletapp.service.validateErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
//to accept request from different ports we use cross origins
@CrossOrigin
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private validateErrorService validate;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result){
        ResponseEntity errors = validate.validate(result);
        if(errors!=null) return errors;

        Wallet savedWallet = walletService.createOrUpdate(wallet);
        return new  ResponseEntity<Wallet>(savedWallet,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<>(walletService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Wallet wallet, BindingResult result){
        ResponseEntity errors = validate.validate(result);
        if(errors!=null) return errors;
        wallet.setId(id);
        Wallet savedWallet = walletService.createOrUpdate(wallet);
        return new  ResponseEntity<Wallet>(savedWallet,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




}
