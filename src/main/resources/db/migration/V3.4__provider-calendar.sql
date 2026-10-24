CREATE TABLE provider_calendar (
    id BIGINT NOT NULL AUTO_INCREMENT,
    provider_id binary(16) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_provider_calendar_provider
        UNIQUE (provider_id),

    CONSTRAINT fk_provider_calendar_provider
        FOREIGN KEY (provider_id)
        REFERENCES service_providers (id)
        ON DELETE CASCADE
);