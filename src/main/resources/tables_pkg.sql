-- CREATE SCHEMA "game_info";

--------------------------------------------------------------

create table if not exists game_info.contractor
(
  contractor_id serial primary key,
  name          text,
  place         text
);
--------------------------------------------------------------

create table if not exists game_info.games
(
  games_id  serial primary key,
  game_name text
);

--------------------------------------------------------------

create table if not exists game_info.contractor_games
(
  contractor_games_id serial primary key,
  contractor_id       INTEGER REFERENCES game_info.contractor (contractor_id),
  games_id            INTEGER REFERENCES game_info.games (games_id),
  unique (contractor_id, games_id)
);

--------------------------------------------------------------

create table if not exists game_info.log_crash_game
(
  log_crash_game_id serial not null,
  game_start timestamp,
  player_ip text,
  server_ip text,
  log_sever text,
  log_player text,
  game_name varchar(30),
  mac_addr text
)
;

comment on table game_info.log_crash_game is 'Crash table'
;

comment on column game_info.log_crash_game.game_start is 'время начала игры'
;

comment on column game_info.log_crash_game.player_ip is 'id Player'
;

comment on column game_info.log_crash_game.server_ip is 'id Server'
;

comment on column game_info.log_crash_game.log_sever is 'log server'
;

comment on column game_info.log_crash_game.log_player is 'log client'
;



--------------------------------------------------------------

create table if not exists game_info.log_run_game
(
  log_run_game_id serial not null
    constraint log_run_game_pkey
    primary key,
  game_name       text,
  game_start       timestamp,
  game_end        text,
  play_start      timestamp,
  player_count    integer,
  server_ip       text,
  mac_addr        text
);

comment on column game_info.log_run_game.game_name
is 'имя сервера';

comment on column game_info.log_run_game.game_start
is 'время старта игры';

comment on column game_info.log_run_game.game_end
is 'конец игры';

comment on column game_info.log_run_game.play_start
is 'время начала игры';

comment on column game_info.log_run_game.player_count
is 'количество игроков';

comment on column game_info.log_run_game.server_ip
is 'id сервера с игрой';
--------------------------------------------------------------
create table if not exists game_info.generated_contractor_key
(
  id                  serial not null
    constraint init_place_gkey_pkey
    primary key,
  key_game            text,
  contractor_games_id integer
    constraint init_place_gkey_contractor_games_id_fkey
    references game_info.contractor_games,
  count               integer
);

comment on table game_info.generated_contractor_key
is 'таблица инициализированных ключей и привязка контрагента';

--------------------------------------------------------------

create table if not exists game_info.init_contractor_game_mac_key
(
  init_contractor_game_mac_key_id serial not null
    constraint init_contractor_game_mac_key_pkey
    primary key,
  key_game                        text,
  place_game                      text,
  mac_address                     text,
  contractor_games_id             integer
    constraint init_contractor_game_mac_key_contractor_games_contractor_games_
    references game_info.contractor_games
);
ALTER TABLE game_info.init_contractor_game_mac_key ADD isallowed boolean DEFAULT true;

create unique index if not exists init_contractor_game_mac_key_init_contractor_game_mac_key_id_ui
  on game_info.init_contractor_game_mac_key (init_contractor_game_mac_key_id);

ALTER TABLE game_info.init_contractor_game_mac_key
  ADD UNIQUE (mac_address, contractor_games_id)
--------------------------------------------------------------
