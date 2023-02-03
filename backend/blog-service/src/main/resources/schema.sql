CREATE TABLE users (
                           id INTEGER NOT NULL AUTO_INCREMENT,
                           email VARCHAR(255),
                           password VARCHAR(255),
                           firstName VARCHAR(255),
                           lastName VARCHAR(255),
                           registeredAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
);

CREATE TABLE favorites (
                        id INTEGER NOT NULL AUTO_INCREMENT,
                        userId INTEGER NOT NULL,
                        title VARCHAR(255),
                        blogName VARCHAR(255),
                        content VARCHAR(500),
                        thumbnail VARCHAR(255),
                        url VARCHAR(255),
                        urlHashCode INTEGER,
                        writtenAt TIMESTAMP,
                        createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
);


CREATE INDEX favorites ON favorites(userId);
CREATE INDEX urlHashCode ON favorites(userId, urlHashCode);