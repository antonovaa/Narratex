create or replace function game_info.get_contractor_game_place(p_contractor_games_id int)
  returns table(
    game_name        text,
    contractor_name  text,
    contractor_place text
  )
as $func$
begin
  return query
  select
    gg.game_name,
    gc.name,
    gc.place
  from game_info.contractor_games g
    join game_info.games gg on g.games_id = gg.games_id
    join game_info.contractor gc on g.contractor_id = gc.contractor_id
  where g.contractor_games_id = p_contractor_games_id;
end;
$func$
LANGUAGE 'plpgsql';

--------------------------------------------------------------
create or replace function game_info.get_init_contractor_game(p_contractor_id int)
  returns table(
    id              int,
    key_game        text,
    place_game      text,
    mac_address     text,
    isallowed       boolean,
    game_name       text,
    contragent_name text
  )
as $func$
begin
  return query
  select
    icgmk.init_contractor_game_mac_key_id,
    icgmk.key_game,
    icgmk.place_game,
    icgmk.mac_address,
    icgmk.isallowed,
    gg.game_name,
    gc.name
  from game_info.init_contractor_game_mac_key icgmk
    join game_info.contractor_games cg on icgmk.contractor_games_id = cg.contractor_games_id
    join game_info.games gg on cg.games_id = gg.games_id
    join game_info.contractor gc on cg.contractor_id = gc.contractor_id
    where p_contractor_id=0 or gc.contractor_id=p_contractor_id
  order by icgmk.init_contractor_game_mac_key_id;
end;
$func$
LANGUAGE 'plpgsql';

--------------------------------------------------------------
create or replace function game_info.is_allowed_this_pc_contractor_game(p_mac_address text,p_game_name text)
  returns table(isallowed boolean)
as $func$
begin
  return query
  select
    icgmk.isallowed
  from game_info.init_contractor_game_mac_key icgmk
    join game_info.contractor_games cg on icgmk.contractor_games_id = cg.contractor_games_id
    join game_info.games gg on cg.games_id = gg.games_id
  where upper(gg.game_name)=upper(p_game_name) and icgmk.mac_address=p_mac_address;
end;
$func$
LANGUAGE 'plpgsql';

--------------------------------------------------------------
create or replace function game_info.get_key_exist_init_game(p_mac_address text, p_game_name text)
  returns text
as $func$
declare
  l_key text :='';
begin
  select icgmk.key_game
  into l_key
  from game_info.init_contractor_game_mac_key icgmk
    join game_info.contractor_games cg on icgmk.contractor_games_id = cg.contractor_games_id
    join game_info.games gg on cg.games_id = gg.games_id
  where upper(gg.game_name) = upper(p_game_name)
        and upper(icgmk.mac_address) = upper(p_mac_address);
  return l_key;
end;
$func$
LANGUAGE 'plpgsql';


