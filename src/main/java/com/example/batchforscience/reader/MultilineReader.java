package com.example.batchforscience.reader;

import java.util.Set;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;

import com.example.batchforscience.domain.BankAccount;
import com.example.batchforscience.domain.Client;

public class MultilineReader extends SingleItemPeekableItemReader<Client> {

	@Override
	public Client read() throws Exception, UnexpectedInputException, ParseException {
        Client item = super.read();

        if (item == null) {
            return null;
        }
        
        while (true) {
        	Client possibleRelatedObject = peek();
            if (possibleRelatedObject == null) {
                return item;
            }

            //non client line will be without client id
            boolean matches = possibleRelatedObject.getId() == null; 

            if (matches) {
            	// add logic for locations too
            	Client withAditionalInfo = super.read();
            	Set<BankAccount> accounts = item.getAccounts();	
            	accounts.addAll(withAditionalInfo.getAccounts());
            	item.setAccounts(accounts);
            } else {
                return item;
            }
        }
	}

}
