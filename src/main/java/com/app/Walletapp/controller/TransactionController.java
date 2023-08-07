package com.app.Walletapp.controller;

import com.app.Walletapp.entity.Transaction;
import com.app.Walletapp.entity.Wallet;
import com.app.Walletapp.service.TransactionService;
import com.app.Walletapp.service.WalletService;
import com.app.Walletapp.service.validateErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/transaction")
//to accept request from different ports we use cross origins
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private validateErrorService validate;

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> getAll(@PathVariable Long wallet_id){
        return new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }

    @GetMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> getById(@PathVariable Long wallet_id,@PathVariable Long id){
        return new ResponseEntity<>(transactionService.getById(wallet_id,id),HttpStatus.OK);
    }

    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result){
        ResponseEntity errors = validate.validate(result);
        if(errors!=null) return errors;

        Transaction savedTransaction = transactionService.createOrUpdate(wallet_id,transaction);
        return new  ResponseEntity<Transaction>(savedTransaction,HttpStatus.CREATED);
    }

    @PutMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> update(@PathVariable Long wallet_id,@PathVariable Long id,@Valid @RequestBody Transaction transaction, BindingResult result){
        ResponseEntity errors = validate.validate(result);
        if(errors!=null) return errors;
        transaction.setId(id);
        transaction.setTransactionDate(new Date());
        Transaction savedtransaction = transactionService.createOrUpdate(wallet_id,transaction);
        return new  ResponseEntity<Transaction>(savedtransaction,HttpStatus.OK);
    }


    @DeleteMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long wallet_id,@PathVariable Long id){
        transactionService.delete(wallet_id,id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
