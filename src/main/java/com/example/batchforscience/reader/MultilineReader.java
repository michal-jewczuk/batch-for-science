package com.example.batchforscience.reader;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;

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

            //logic to determine if next line in file relates to same object
            boolean matches = false; 

            if (matches) {
                //item.addRelatedInfo(super.read());
            } else {
                return item;
            }
        }
	}

}
