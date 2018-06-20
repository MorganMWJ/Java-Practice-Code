package accounts;

import util.Model;
import accounts.controller.AccountController;
import accounts.model.Account;

public class BuildBankApp{
   public static void main(String [] args){
      Model account = new Account(0.0);
      new AccountController(account);
   }
}