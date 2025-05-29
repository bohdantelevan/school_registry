CREATE TABLE IF NOT EXISTS schools (
                                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                       name VARCHAR(255) NOT NULL,
                                       edrpou INTEGER NOT NULL UNIQUE,
                                       region VARCHAR(255) NOT NULL,
                                       type VARCHAR(50) NOT NULL,
                                       is_active BOOLEAN DEFAULT TRUE
);