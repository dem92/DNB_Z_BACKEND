
/*Lit div. select all eller mer spesifikke selects */
select * from Bruker where Foedselsnummer=12019944431;
select * from Bankkonto where Kundenummer=1;
select * from Bruker where Fornavn = 'Henrik';
select * from Administrator;
select * from Bruker;
select * from Bankkonto;
select * from Passord_Bruker;

/* For å oppdatere en bankkonto */
update Bankkonto
set Kroner=2000
where Kundenummer=1;

/* Auto increment fungerer ikke */
/* for å lage en bankkonto Husk å fylle inn riktig info! kontonummer og kundenummer skal produseres automatisk. */
insert into Bankkonto
values(FLOOR(RAND() * (10000000000 - 99999999999 + 1)) + 10000000000, last_insert_id(),2,0,0,4,0,0);

/* For å slette bankkontoer */
delete from Bankkonto where Kundenummer=0 and Kontonummer=-19982987492;


