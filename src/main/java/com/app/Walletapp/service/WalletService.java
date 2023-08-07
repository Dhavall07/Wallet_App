package com.app.Walletapp.service;

import com.app.Walletapp.entity.Wallet;
import com.app.Walletapp.exception.WalletException;
import com.app.Walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository repo;

    public Wallet createOrUpdate(Wallet wallet){
        if(wallet.getId()==null){
            repo.save(wallet);
        }else{
            repo.save(wallet);
        }

        return wallet;
    }

    public List<Wallet> getAll(){
        return repo.findAllByOrderByPriority();
    }

    public Wallet getById(Long id){
        Optional<Wallet> wallet = repo.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }
        throw new WalletException("Wallet with "+id+" does not exists!");
    }


    public boolean delete(Long id){
        Optional<Wallet> wallet = repo.findById(id);
        if(wallet.isPresent()){
            repo.delete(wallet.get());
            return true;
        }
        throw new WalletException("Wallet with "+id+" does not exists!");
    }

}
