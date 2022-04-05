General structure:
==================

This project contains 3 entities:
    - Customer
    - Account
    - Transaction

Customer:
---------
They can have multiple accounts linked to them.
They should have an Equity account in order to add an initial balance.
For convenience the user balance does only takes Asset accounts

Account:
--------
They are linked to a customer.
They can have multiple transactions linked to them, divided in two sets:
    - Credit Transactions are amounts leaving the account;
    - Debit Transactions are amounts going into the account;

They can have multiple types, used for different purposes:
    - Equity holds amounts coming from outside of this scope.
    - Assets holds amounts in use in this scope.

The account balance is the sum of debit amounts - credit amounts

Transaction:
------------
It is defined as a transfer of amount between 2 accounts:
    - the Credited Account is the source account
    - the Debited Account is the destination account



Usage:
======

The project uses Spring Java, scaffolded from https://start.spring.io/
with dependecies : Spring Web, Spring Data JPA, H2 Database, Lombook

you can start the server with : ./mvnw clean spring-boot:run
you can use curl as a client

New account flow:
-----------------
Two different implementations are available for testing in the branch release/new-account:

# from the account perspective:
$ curl -X POST "localhost:8080/accounts/new" -H 'Content-type:application/json'  -d '{"nam
e": "test1", "initialCredit": 3, "customerId": 1}'  | jq
{
  "id": 3,
  "name": "test1",
  "customerPlainDto": {
    "id": 1,
    "name": "Bilbo",
    "surname": "Baggins"
  },
  "balance": 0,
  "type": "ASSET",
  "creditTransactionsPlainDto": [],
  "debitTransactionsPlainDto": []
}

# from the customer perspective
$ curl -X POST "localhost:8080/customers/2/account/create" -H 'Content-type:application/js
on'  -d '{"name": "test2", "initialCredit": 3}'  | jq
{
  "id": 2,
  "name": "Frodo",
  "surname": "Baggins",
  "accountsPlainDto": [
    {
      "id": 6,
      "name": "test2",
      "type": "ASSET"
    }
  ]
}


Customer Info Flow:
-------------------
already merged in master, available in all releases

$ curl localhost:8080/customers/1/info | jq
{
  "id": 1,
  "name": "Bilbo",
  "surname": "Baggins",
  "balance": 3,
  "accountsAssetDto": [
    {
      "id": 3,
      "name": "test2",
      "balance": 3,
      "type": "ASSET",
      "creditTransactionsPlainDto": [],
      "debitTransactionsPlainDto": [
        {
          "id": 5,
          "datetime": "2022-04-05T04:13:26.055234",
          "amount": 3
        }
      ]
    }
  ]
}

[WIP] Full REST API
-------------------
the branch release/rest contains basic CRUD controller routes.
It does not fully meet REST constrains so it is more a RPC interface for now.

