DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `wallet`;
DROP TABLE IF EXISTS `transactionType`;
DROP TABLE IF EXISTS `transaction`;
DROP TABLE IF EXISTS `walletTransaction`;
DROP TABLE IF EXISTS `sport`;
DROP TABLE IF EXISTS `team`;
DROP TABLE IF EXISTS `betOdd`;


-- CREATE TABLE `role` (
-- 	`roleId` INT NOT NULL AUTO_INCREMENT,
-- 	`roleName` VARCHAR(30) NOT NULL,
-- 	PRIMARY KEY (`roleId`)
-- ) ENGINE=InnoDB;
--
-- /* walletStatus can be ACTIVE, BLOCKED, SUSPENDED, DELETED */
-- CREATE TABLE `wallet` (
-- 	`walletId` INT NOT NULL AUTO_INCREMENT,
-- 	`walletBalance` DOUBLE NOT NULL,
-- 	`walletStatus` VARCHAR(30) NOT NULL,
-- 	PRIMARY KEY (`walletId`)
-- ) ENGINE=InnoDB;

-- /* TRANSACTION TABLES
-- for later implementation (next 3 tables: transactionType, transaction, walletTransaction) */
-- CREATE TABLE `transactionType` (
-- 	`transactionTypeId` INT NOT NULL AUTO_INCREMENT,
-- 	`transactionTypeName` VARCHAR(30) NOT NULL,
-- 	PRIMARY KEY (`transactionTypeId`)
-- ) ENGINE=InnoDB;
--
-- /* transactionStatus can be OPEN, PAID, PAID OUT, FAILED, REFUNDED */
-- CREATE TABLE `transaction` (
-- 	`transactionId` INT NOT NULL AUTO_INCREMENT,
-- 	`transactionFromAccount` VARCHAR(30) NOT NULL,
-- 	`transactionToAccount` VARCHAR(30) NOT NULL,
-- 	`transactionDate` DATE NOT NULL,
-- 	`transactionTime` TIME NOT NULL,
-- 	`transactionTypeId` INT NOT NULL,
-- 	`transactionAmount` DOUBLE NOT NULL,
-- 	`transactionStatus` VARCHAR(30) NOT NULL,
-- 	PRIMARY KEY (`transactionId`),
-- 	CONSTRAINT `constr_transaction_transactionType_fk` FOREIGN KEY (`transactionTypeId`) REFERENCES `transactionType`(`transactionTypeId`)
-- ) ENGINE=InnoDB;

-- wallet id is added to transaction table
CREATE TABLE `walletTransaction` (
	`walletId` INT NOT NULL,
	`transactionId` INT NOT NULL,
	PRIMARY KEY (`walletId`,`transactionId`),
	CONSTRAINT `constr_walletTransaction_wallet_fk` FOREIGN KEY (`walletId`) REFERENCES `wallet`(`walletId`),
	CONSTRAINT `constr_walletTransaction_transaction_fk` FOREIGN KEY (`transactionId`) REFERENCES `transaction`(`transactionId`)
) ENGINE=InnoDB;

-- CREATE TABLE `sport` (
-- 	`sportId` INT NOT NULL AUTO_INCREMENT,
-- 	`sportName` VARCHAR(30) NOT NULL,
-- 	PRIMARY KEY (`sportId`)
-- ) ENGINE=InnoDB;

-- CREATE TABLE `team` (
-- 	`teamId` INT NOT NULL AUTO_INCREMENT,
-- 	`teamName` VARCHAR(40) NOT NULL,
-- 	`sportId`  INT NOT NULL,
-- 	PRIMARY KEY (`teamId`),
-- 	CONSTRAINT `constr_team_sport_fk` FOREIGN KEY (`sportId`) REFERENCES `sport`(`sportId`)
-- ) ENGINE=InnoDB;

-- /* later add betOddInfo not null*/
-- CREATE TABLE `betOdd` (
-- 	`betOddId` INT NOT NULL AUTO_INCREMENT,
-- 	`betOddName` VARCHAR(20) NOT NULL,
-- 	`betOddInfo` VARCHAR(100),
-- 	PRIMARY KEY (`betOddId`)
-- ) ENGINE=InnoDB;

-- /* eventStatus can be ACTIVE, FINISHED, UPCOMING */
-- CREATE TABLE `event` (
-- 	`eventId` INT NOT NULL AUTO_INCREMENT,
-- 	`eventName` VARCHAR(20) NOT NULL,
-- 	`sportId` INT NOT NULL,
-- 	`eventStatus` VARCHAR(20) NOT NULL,
-- 	PRIMARY KEY (`eventId`),
-- 	CONSTRAINT `constr_event_sport_fk` FOREIGN KEY (`sportId`) REFERENCES `sport`(`sportId`)
-- ) ENGINE=InnoDB;

-- /* matchStatus can be UPCOMING, FINISHED, HALFTIME, STARTED, EXTRA TIME */
-- CREATE TABLE `match` (
-- 	`matchId` INT NOT NULL AUTO_INCREMENT,
-- 	`eventId`INT NOT NULL,
-- 	`matchDate` DATE NOT NULL,
-- 	`matchTime` TIME NOT NULL,
-- 	`matchStatus` VARCHAR(30) NOT NULL,
-- 	`matchInfo` VARCHAR(100),
-- 	PRIMARY KEY (`matchId`),
-- 	CONSTRAINT `constr_match_event_fk` FOREIGN KEY (`eventId`) REFERENCES `event`(`eventId`)
-- ) ENGINE=InnoDB;

-- /* question teamscore should be null or not null or default 0 */
-- CREATE TABLE `matchTeam` (
-- 	`matchId` INT NOT NULL,
-- 	`teamId` INT NOT NULL,
-- 	`teamScore` INT,
-- 	`teamAtHome` INT,
-- 	PRIMARY KEY (`matchId`,`teamId`),
-- 	CONSTRAINT `constr_matchTeam_match_fk` FOREIGN KEY (`matchId`) REFERENCES `match`(`matchId`),
-- 	CONSTRAINT `constr_matchTeam_team_fk` FOREIGN KEY (`teamId`) REFERENCES `team`(`teamId`)
-- ) ENGINE=InnoDB;

-- CREATE TABLE `matchOdd` (
-- 	`matchId` INT NOT NULL,
-- 	`betOddId` INT NOT NULL,
-- 	`matchOddValue` DOUBLE NOT NULL,
-- 	`matchOddEnabled` BIT(1) NOT NULL,
-- 	PRIMARY KEY (`matchId`,`betOddId`),
-- 	CONSTRAINT `constr_matchOdd_match_fk` FOREIGN KEY (`matchId`) REFERENCES `match`(`matchId`),
-- 	CONSTRAINT `constr_matchTeam_betOdd_fk` FOREIGN KEY (`betOddId`) REFERENCES `betOdd`(`betOddId`)
-- ) ENGINE=InnoDB;

/* ticketStatus can be ACTIVE, FAIL, WIN */
CREATE TABLE `ticket` (
	`ticketId` INT NOT NULL AUTO_INCREMENT,
	`ticketDate` DATE NOT NULL,
	`ticketTime` TIME NOT NULL,
	`ticketTotalOdd` DOUBLE NOT NULL,
	`ticketTotalAmount` DOUBLE NOT NULL,
	`ticketStatus` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`ticketId`)
) ENGINE=InnoDB;

/* ticketMatchStatus can be ACTIVE, FAIL, WIN */
CREATE TABLE `ticketMatch` (
	`ticketId` INT NOT NULL,
	`matchId` INT NOT NULL,
	`ticketMatchBetOddValue` DOUBLE NOT NULL,
	`ticketMatchStatus` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`ticketId`,`matchId`),
	CONSTRAINT `constr_ticketMatch_ticket_fk` FOREIGN KEY (`ticketId`) REFERENCES `ticket`(`ticketId`),
	CONSTRAINT `constr_ticketMatch_match_fk` FOREIGN KEY (`matchId`) REFERENCES `match`(`matchId`)
) ENGINE=InnoDB;

-- /* userStatus can be CREATED, BLOCKED, SUSPENDED, DELETED, VERIFIED */
-- CREATE TABLE `user` (
-- 	`userId` INT NOT NULL AUTO_INCREMENT,
-- 	`userName` VARCHAR(30) NOT NULL,
-- 	`userPassword` VARCHAR(100) NOT NULL,
-- 	`userEmail` VARCHAR(100) NOT NULL,
-- 	`userFirstName` VARCHAR(50) NOT NULL,
-- 	`userLastName` VARCHAR(50) NOT NULL,
-- 	`userStatus` VARCHAR(30) NOT NULL,
-- 	PRIMARY KEY (`userId`)
-- ) ENGINE=InnoDB;

/* userTicketStatus can be ? */
CREATE TABLE `userTicket` (
	`userId` INT NOT NULL,
	`ticketId` INT NOT NULL,
	`userTicketStatus` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`userId`,`ticketId`),
	CONSTRAINT `constr_userTicket_user_fk` FOREIGN KEY (`userId`) REFERENCES `user`(`userId`),
	CONSTRAINT `constr_userTicket_ticket_fk` FOREIGN KEY (`ticketId`) REFERENCES `ticket`(`ticketId`)
) ENGINE=InnoDB;






