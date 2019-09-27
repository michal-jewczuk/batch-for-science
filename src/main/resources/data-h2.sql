INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(1, 101, 2000, true);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(2, 101, 3000, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(3, 101, 4000, false);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(4, 102, 500, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(5, 102, 250, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(6, 102, 170, false);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(7, 103, 3240, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(8, 103, 2770, true);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(9, 104, 50, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(10, 104, 210, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(11, 104, 130, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(12, 104, 240, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(13, 104, 70, true);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(14, 105, 1130, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(15, 105, 1240, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(16, 105, 1070, false);	
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(17, 211, 530, true);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(18, 211, 820, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(19, 211, 670, true);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(20, 212, 3530, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(21, 212, 1820, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(22, 212, 6670, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(23, 212, 9670, false);
		
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(24, 214, 170, false);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(25, 214, 230, false);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(26, 215, 1700, true);
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(27, 215, 2300, true);
	
INSERT INTO invoice(id, client_id, amount, is_paid)
	VALUES(28, 216, 9000, true);
	
INSERT INTO orders(id, client_id, ready_on)
	VALUES(1, 101, '2017-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(2, 101, '2018-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(3, 101, '2019-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(4, 101, '2020-01-01');
	
INSERT INTO orders(id, client_id, ready_on)
	VALUES(5, 105, '2020-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(6, 105, '2020-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(7, 105, '2019-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(8, 105, '2020-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(9, 105, '2020-01-01');

INSERT INTO orders(id, client_id, ready_on)
	VALUES(10, 211, '2018-01-01');
	
INSERT INTO orders(id, client_id, ready_on)
	VALUES(11, 212, '2020-01-01');
INSERT INTO orders(id, client_id, ready_on)
	VALUES(12, 212, '2019-01-01');
	
INSERT INTO orders(id, client_id, ready_on)
	VALUES(13, 215, '2021-01-01');
	
INSERT INTO orders(id, client_id, ready_on)
	VALUES(14, 216, '2020-03-01');
