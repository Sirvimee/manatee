CREATE TABLE IF NOT EXISTS CANDIDATE (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS INTERVIEW (
    id INT NOT NULL AUTO_INCREMENT,
    interview_time TIMESTAMP NOT NULL,
    interviewer VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS APPLICATION (
    id INT NOT NULL AUTO_INCREMENT,
    application_state VARCHAR(255) NOT NULL,
    updated_on TIMESTAMP NOT NULL,
    candidate_id INT NOT NULL,
    interview_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (candidate_id) REFERENCES CANDIDATE(id),
    FOREIGN KEY (interview_id) REFERENCES INTERVIEW(id)
);

insert into CANDIDATE (id, first_name, last_name) values (1, 'Marcela', 'Castagneto');
insert into CANDIDATE (id, first_name, last_name) values (2, 'Ginger', 'Leathley');
insert into CANDIDATE (id, first_name, last_name) values (3, 'Leslie', 'Meale');
insert into CANDIDATE (id, first_name, last_name) values (4, 'Seth', 'Goble');
insert into CANDIDATE (id, first_name, last_name) values (5, 'Adrea', 'McIlherran');
insert into CANDIDATE (id, first_name, last_name) values (6, 'Kristyn', 'Hanselmann');

insert into INTERVIEW (id, interview_time, interviewer) values (1, '2023-04-23T04:56:07', 'Phelia Chuck');
insert into INTERVIEW (id, interview_time, interviewer) values (2, '2023-05-23T04:56:07', 'Andres Kala');

insert into APPLICATION (id, application_state, updated_on, candidate_id, interview_id) values (1, 'INTERVIEW', '2023-01-23T04:56:07', 1, 1);
insert into APPLICATION (id, application_state, updated_on, candidate_id, interview_id) values (2, 'INTERVIEW', '2023-01-23T04:56:07', 2, 2);
insert into APPLICATION (id, application_state, updated_on, candidate_id) values (3, 'NEW', '2023-01-23T04:56:07', 3);
insert into APPLICATION (id, application_state, updated_on, candidate_id) values (4, 'NEW', '2023-01-23T04:56:07', 4);
insert into APPLICATION (id, application_state, updated_on, candidate_id) values (5, 'NEW', '2023-01-23T04:56:07', 5);
insert into APPLICATION (id, application_state, updated_on, candidate_id) values (6, 'NEW', '2023-01-23T04:56:07', 6);

