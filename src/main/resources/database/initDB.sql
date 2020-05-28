CREATE TABLE IF NOT EXISTS items
(
    id    SERIAL PRIMARY KEY ,
    volume  INTEGER NOT NULL ,
    value INTEGER NOT NULL ,
    art BOOLEAN NOT NULL ,
    release date NOT NULL
);
CREATE TABLE IF NOT EXISTS locations
(
    id    SERIAL PRIMARY KEY ,
    storagerate  INTEGER NOT NULL ,
    name VARCHAR NOT NULL
);