package com.FinalExam.SirmaFinalExam.Repos;

import com.FinalExam.SirmaFinalExam.Models.Records;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainRepo extends JpaRepository<Records, Integer> {
    /* This sql request crushed me - no kidding. Here are some thoughts about the task:
    I shall leave it as it is here. The reason concerns the fact I haven't had enough information to deduce playing time
    of player which played in match with knocked out time precisely. We have matches which had penalties, but penalties come
    after at least 120 minutes of playing time. So. if I try to set playing time to 120 minutes of every match which
    ended with penalties, then I would have just longer matches, but I haven't precise info about which players played
    the whole match. For example - player x played in match with penalties, but he was substituted in 95-th minute.
    I can't know when he was substituted from given data.
    Also: If regular playing time is 90 minutes, this isn't accurate since almost every match has additional minutes
    given by the side referee. This fact can help us in finding which exact pair of players played the most time,
    since there are two teams in the final of euro 2024, but if any match of one team had more additional minutes
    this team should contains the winning pair of players.
    At next place: How can I decide which pair of players is winning pair if there are two teams which had exactly
    the same minutes of play time (exactly the same count of matches with penalties and exactly the same additional
    minutes). We haven't such criteria stated clearly. In football decision would be in favor of the winner (probably).

     */
    @Query(nativeQuery = true, value = """
    with match_details as (
        select 
            p1.full_name as player_1, 
            p2.full_name as player_2,
            r1.match_id,
            case when r1.to_minutes < r2.to_minutes 
                 then r1.to_minutes else r2.to_minutes end
            - case when r1.from_minutes > r2.from_minutes 
                 then r1.from_minutes else r2.from_minutes end as minutes_together
        from records r1
        join records r2 on r1.match_id = r2.match_id
        join players p1 on p1.id = r1.player_id
        join players p2 on p2.id = r2.player_id
        where r1.player_id < r2.player_id
          and r1.from_minutes < r2.to_minutes
          and r2.from_minutes < r1.to_minutes
    ),
    total_time as (
        select 
            player_1, 
            player_2,
            sum(minutes_together) as total_minutes
        from match_details
        group by player_1, player_2
    ),
    top_players_pair as (
        select top 1 player_1, player_2, total_minutes
        from total_time
        order by total_minutes desc
    )
    select 
        tp.player_1,
        tp.player_2,
        tp.total_minutes,
        md.match_id,
        md.minutes_together
    from top_players_pair tp
    join match_details md on md.player_1 = tp.player_1 and md.player_2 = tp.player_2
    order by md.minutes_together desc
""")
    List<Object[]> getPlayerPairsWithMostTime();


    @Query(nativeQuery = true, value = """
            with players_and_minutes as (
                select p1.full_name as player_1, p2.full_name as player_2, sum(
                dbo.getMinMinutes(r1.to_minutes, r2.to_minutes) -
                dbo.getMaxMinutes(r1.from_minutes, r2.from_minutes))
                as total_minutes
                from records r1
                JOIN records r2 on r1.match_id = r2.match_id
                join players p1 on p1.id = r1.player_id
                join players p2 on p2.id = r2.player_id
                where r1.player_id < r2.player_id AND
                r1.from_minutes < r2.to_minutes
                and r2.from_minutes < r1.to_minutes
                group by p1.full_name, p2.full_name
            )
            select top 1 * from players_and_minutes order by total_minutes desc;
            
            """)
    Object[] pairOfPlayers();


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        create or alter function getMaxMinutes(@first_minutes int, @second_minutes int) 
        returns int
        as
        begin
            if (@first_minutes < @second_minutes)
                return @second_minutes
            return @first_minutes
        end
    """)
    void createMaxMinutesFunction();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        create or alter function getMinMinutes(@first_minutes int, @second_minutes int)
        returns int
        as
        begin
            if (@first_minutes < @second_minutes)
                return @first_minutes
            return @second_minutes
        end
    """)
    void createMinMinutesFunction();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            drop function getMaxMinutes;
            drop function getMinMinutes;
            """)
    void dropCustomFunctions();
}
