CREATE TABLE games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player1 VARCHAR(255) NOT NULL,
    player2 VARCHAR(255) NOT NULL,
    winner ENUM('player1', 'player2', 'draw') NOT NULL,
    gameDate DATETIME NOT NULL,
    player1LifePoints INT NOT NULL,
    player2LifePoints INT NOT NULL,
    rounds INT NOT NULL,
    usedCardNames TEXT NOT NULL,
    graveyardCardNames TEXT NOT NULL,
    bannedCardNames TEXT NOT NULL,
    endTime DATETIME
);
