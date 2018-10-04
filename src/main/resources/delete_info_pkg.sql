create or replace function game_info.delete_contractor(p_contractor_id int)
  returns text
as $func$
begin

  delete from game_info.init_contractor_game_mac_key icgmk
  where icgmk.contractor_games_id in
        (
          select g.contractor_games_id
          from game_info.contractor_games g
          where g.contractor_id = p_contractor_id
        );

  delete from game_info.generated_contractor_key gck
  where gck.contractor_games_id in
        (
          select g.contractor_games_id
          from game_info.contractor_games g
          where g.contractor_id = p_contractor_id
        );

  delete from game_info.contractor_games g
  where g.contractor_id = p_contractor_id;

  delete from game_info.contractor gg
  where gg.contractor_id = p_contractor_id;

  return null;
end;
$func$
LANGUAGE 'plpgsql';

-------------------------------------------------------------------

create or replace function game_info.delete_games(p_games_id int)
  returns text
as $func$
begin

  delete from game_info.init_contractor_game_mac_key icgmk
  where icgmk.contractor_games_id in
        (
          select g.contractor_games_id
          from game_info.contractor_games g
          where g.games_id = p_games_id
        );

  delete from game_info.generated_contractor_key gck
  where gck.contractor_games_id in
        (
          select g.contractor_games_id
          from game_info.contractor_games g
          where g.games_id = p_games_id
        );


  delete from game_info.contractor_games g
  where g.games_id = p_games_id;
  delete from game_info.games gg
  where gg.games_id = p_games_id;
  return null;
end;
$func$
LANGUAGE 'plpgsql';
-------------------------------------------------------------------

create or replace function game_info.delete_contractor_games(p_contractor_games_id int)
  returns text
as $func$
begin


  delete from game_info.init_contractor_game_mac_key icgmk
  where icgmk.contractor_games_id = p_contractor_games_id;

  delete from game_info.generated_contractor_key gck
  where gck.contractor_games_id = p_contractor_games_id;

  delete from game_info.contractor_games g
  where g.contractor_games_id = p_contractor_games_id;

  return null;
end;
$func$
LANGUAGE 'plpgsql';

-------------------------------------------------------------------