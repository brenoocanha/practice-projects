package dev.obreno.accounts.service;

import dev.obreno.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Account details based on a given mobileNumber
     */
    CustomerDto fetchAccount(String mobileNumber);
}
