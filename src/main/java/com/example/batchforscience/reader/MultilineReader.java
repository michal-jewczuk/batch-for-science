package com.example.batchforscience.reader;

import java.util.Set;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;

import com.example.batchforscience.domain.BankAccount;
import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.Location;

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
            if (possibleRelatedObject.getId() == null) {
            	Client withAdditionalInfo = super.read();
            	setAdditionalInfo(item, withAdditionalInfo);
            } else {
                return item;
            }
        }
	}

	// additional info can only be applied to business client
	private void setAdditionalInfo(Client item, Client withAdditionalInfo) {
		if (!item.isBusiness()) {
			return;
		}
		
		Set<BankAccount> accounts = withAdditionalInfo.getAccounts();
		Set<Location> locations = withAdditionalInfo.getLocations();
		
		if (!accounts.isEmpty()) {
			item.getAccounts().addAll(accounts);
		} else if (!locations.isEmpty()) {
			item.getLocations().addAll(locations);
		}
	}

}
