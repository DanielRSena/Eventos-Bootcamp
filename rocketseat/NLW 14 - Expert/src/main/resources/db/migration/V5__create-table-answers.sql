CREATE TABLE answers ( 
    id VARCHAR(36) PRIMARY KEY,
    certification_id VARCHAR(36),
    student_id VARCHAR(36),
    question_id VARCHAR(36),
    answer_id VARCHAR(36),
    is_correct TINYINT(1),
    created_at TIMESTAMP,
    
    FOREIGN KEY (certification_id) REFERENCES certifications(id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (is_correct) REFERENCES alternatives(is_correct)
);