CREATE TABLE url_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    original_url VARCHAR(255) NOT NULL,
    short_url VARCHAR(255) NOT NULL
);

CREATE TABLE url_mapping_click (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    url_click_time TIMESTAMP,
    url_mapping_id BIGINT,
    FOREIGN KEY (url_mapping_id) REFERENCES url_mapping(id)
);

