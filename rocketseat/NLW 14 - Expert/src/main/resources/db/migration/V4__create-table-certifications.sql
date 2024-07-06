CREATE TABLE certifications ( 
    id VARCHAR(36) PRIMARY KEY,
    technology VARCHAR(100) NOT NULL,
    grate INT NOT NULL,
    student_id VARCHAR(36) NOT NULL,    
    created_at TIMESTAMP NOT NULL,
    
    FOREIGN KEY(technology) REFERENCES questions(technology),
    FOREIGN KEY(student_id) REFERENCES students(id)
);