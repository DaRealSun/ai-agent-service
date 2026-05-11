alter table claim_records add column idempotency_key varchar(64);
create unique index ux_claim_records_idem ON claim_records(idempotency_key) where idempotency_key is not null;