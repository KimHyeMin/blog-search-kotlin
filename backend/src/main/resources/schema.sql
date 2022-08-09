CREATE TABLE users (
                       id INTEGER NOT NULL AUTO_INCREMENT,
                       firstName VARCHAR(255) NOT NULL,
                       lastName VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       registeredAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY(id)
);
ALTER TABLE users ADD CONSTRAINT EMAIL_UNIQUE UNIQUE(email);



CREATE TABLE favorites (
                        id INTEGER NOT NULL AUTO_INCREMENT,
                        userId INTEGER NOT NULL,
                        title VARCHAR(255),
                        blogName VARCHAR(255),
                        content VARCHAR(500),
                        thumbnail VARCHAR(255),
                        url VARCHAR(255),
                        writtenAt TIMESTAMP,
                        createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
);


ALTER TABLE favorites ADD FOREIGN KEY (userId) REFERENCES users(id);