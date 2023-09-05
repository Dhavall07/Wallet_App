# Wallet_App

The project demonstrates monolitic architecture. It allows user to keep create and maintain different wallets
according to the priorities set by the user.Each wallet has different trensactions. Transactions can be added,
updated or removed. 

## Endpoints:

### GET
`All wallets ` [/wallet](#post-/wallet) <br/>
`Wallet By ID ` [/users/{id}](#post-/wallet/{id}) <br/>
`All transactions for a particular wallet` [/transaction/{walletId}](#post-/transaction/{walletId}) <br/>
`Transaction By ID for a particular wallet ` [/transaction/{walletId}/{id}](#post-/transaction/{walletId}/{id}) <br/>


### POST
`Create a Wallet `
curl --location --request POST 'http://localhost:000/wallet' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"",
    "description":"c",
    "priority":1
}'

`Create a Transaction  `
curl --location --request POST 'http://localhost:0000/transaction/{walletID}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount":,
    "description":"",
    "type":

}'


### PUT
` Update a Transaction `
curl --location --request PUT 'http://localhost:0000/transaction/{walletID}/{TransactionID}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": ,
    "description": "",
    "type": 
}'


### DELETE
` Delete a transaction `
curl --location --request DELETE 'http://localhost:0000/transaction/{walletID}/{TransactionID}'



