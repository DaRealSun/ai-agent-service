CREATE TABLE claim_records(
    id BIGSERIAL primary key,
    claim_number varchar(50) unique not null,
    patient_name varchar(255) not null,
    diagnosis_code varchar(10) ,
    amount decimal(12,2),
    status varchar(20) default 'PENDING',
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    updated_at TIMESTAMP

    );

