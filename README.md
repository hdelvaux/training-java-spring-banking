This project contains 3 entities:
    - Customer
    - Account
    - Transaction

Customer:
=========
They can have multiple accounts linked to them.
They should have an Equity account in order to add an initial balance.
For convenience the user balance does only takes Asset accounts

Account:
========
They are linked to a customer.
They can have multiple transactions linked to them, divided in two sets:
    - Credit Transactions are amounts leaving the account;
    - Debit Transactions are amounts going into the account;

They can have multiple types, used for different purposes:
    - Equity holds amounts coming from outside of this scope.
    - Assets holds amounts in use in this scope.

The account balance is the sum of debit amounts - credit amounts

Transaction:
============
It is defined as a transfer of amount between 2 accounts:
    - the Credited Account is the source account
    - the Debited Account is the destination account
