
CREATE TABLE alternatives (
    id VARCHAR(36) PRIMARY KEY,  
    question_id VARCHAR(36),
    description VARCHAR(255) NOT NULL,
    is_correct TINYINT(1) NOT NULL,
    created_at TIMESTAMP,
    
    INDEX idx_iscorrect (is_correct)
);