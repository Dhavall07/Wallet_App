package com.app.Walletapp.service;

import com.app.Walletapp.entity.Transaction;
import com.app.Walletapp.entity.Wallet;
import com.app.Walletapp.exception.WalletException;
import com.app.Walletapp.repository.TransactionRepository;
import com.app.Walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repo;

    @Autowired
    private WalletRepository walletRepository;

    public Transaction createOrUpdate(Long walletId,Transaction transaction){
         Optional<Wallet> wallet = walletRepository.findById(walletId);
         if(wallet.isPresent()){
             transaction.setWallet(wallet.get());
             repo.save(transaction);
             return transaction;
         }
        throw new WalletException("Wallet with "+walletId+" does not exists!");
    }

    public List<Transaction> getAll(Long walletId){
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()){
            return repo.findByWallet(wallet.get());
        }
        return null;
    }

    public Transaction getById(Long wallet_id,Long id){
        Optional<Wallet> wallet = walletRepository.findById(wallet_id);
        if(wallet.isPresent()){
            Optional<Transaction> transaction = repo.findById(id);
            if(transaction.isPresent()){
                return transaction.get();
            }
        }

        throw new WalletException("Transaction with "+id+" does not exists!");
    }

    public boolean delete(Long wallet_id,Long id){
        Optional<Wallet> wallet = walletRepository.findById(wallet_id);
        if(wallet.isPresent()) {
            Optional<Transaction> transaction = repo.findById(id);
            if (transaction.isPresent()) {
                repo.delete(transaction.get());
                return true;
            }
        }
        throw new WalletException("Transaction with "+id+" does not exists!");
    }

}
