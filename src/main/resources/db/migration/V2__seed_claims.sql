INSERT INTO claim_records (claim_number, patient_name, diagnosis_code, amount, status, created_at)
VALUES
    ('CLM-001', 'John Doe',    'J06.9', 1500.00, 'PENDING',       CURRENT_TIMESTAMP),
    ('CLM-002', 'Jane Smith',  'M54.5', 3200.50, 'APPROVED',      CURRENT_TIMESTAMP),
    ('CLM-003', 'Bob Johnson', 'K21.0',  850.00, 'DENIED',        CURRENT_TIMESTAMP),
    ('CLM-004', 'Alice Brown', 'I10',   2100.75, 'UNDER_REVIEW',  CURRENT_TIMESTAMP),
    ('CLM-005', 'Tom Wilson',  'E11.9', 4500.00, 'PENDING',       CURRENT_TIMESTAMP);