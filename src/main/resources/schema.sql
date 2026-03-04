create or alter table teams(
    int id identity(1,1) primary key,
    name varchar(100) not null,
    manager_full_name varchar(100) not null,
    team_group char(1) not null
);

create or alter table players(
    id int identity(1,1) primary key,
    team_number int not null,
    full_name varchar(100) not null,
    team_id int not null,
    constraint fk_players_team foreign key (team_id) references teams(id)
);

create or alter table matches(
    id int identity(1,1) primary key,
    a_team_id int not null,
    b_team_id int not null,
    date DATE,
    home_team_goals int,
    away_team_goals int,

    constraint fk_matches_team_a
    foreign key (a_team_id) references teams(id),

    constraint chk_diffeent_teams
    check (a_team_id <> b_team_id)
);

create or alter table records(
    id int identity(1,1) primary key,
    player_id int not null,
    match_id int not null,
    from_minutes int not null,
    to_minutes int not null,
    constraint fk_records_player
    foreign key (player_id) references players(id),

    constraint fk_records_match
    foreign key (match_id) references matches(id)
);