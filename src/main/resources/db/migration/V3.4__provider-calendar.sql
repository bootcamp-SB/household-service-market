CREATE TABLE provider_calendar (
    id BIGINT NOT NULL AUTO_INCREMENT,
    provider_id binary(16) NOT NULL,
    calendar_date  DATE NOT NULL,

    status ENUM(
        'BUSY',
        'UNAVAILABLE'
    ) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_provider_calendar_provider
        FOREIGN KEY (provider_id)
        REFERENCES service_providers(id),

    CONSTRAINT uk_provider_calendar_date
        UNIQUE (provider_id, calendar_date)
);