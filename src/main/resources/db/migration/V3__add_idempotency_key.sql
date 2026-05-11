ALTER TABLE claim_records ADD COLUMN IF NOT EXISTS idempotency_key VARCHAR(64);
CREATE UNIQUE INDEX IF NOT EXISTS ux_claim_records_idem
    ON claim_records(idempotency_key)
    WHERE idempotency_key IS NOT NULL;