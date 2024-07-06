
CREATE TABLE questions(
    id VARCHAR(36) PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    technology VARCHAR(100) NOT NULL,
    created_at TIMESTAMP,
    
    INDEX idx_tech(technology)
);