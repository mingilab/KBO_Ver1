-- ½ÃÄö½º °Ë»ö
select sequence_name from user_sequences;
select * from PITCHER_STORAGE
select * from hitter_Storage
where userid = 'test01'

insert into ACCOUNT(USERID,USERPW) values('test1','1234');
insert into HITTER_STORAGE(USERID,PLAYERID,HITTERSEQ) values('test1',164,HITTER_STORAGE_SEQUNCE.nextval)
insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values('test1',506,'st1',PITCHER_STORAGE_SEQUNCE.nextval)

update account set krw = krw+100000 where userid='test01'

select PLAYERID,NAME,CLUB,GRADE,ERA,IP from PITCHER_INFORMATION
select PLAYERID,NAME,CLUB,GRADE,ERA,IP from PITCHER_INFORMATION where PLAYERID=261

select pitcherseq,playerid,pitcherlv,pitcherexp,position,NAME,GRADE,era,ip
from PITCHER_STORAGE
join PITCHER_INFORMATION using(PLAYERID)
where USERID = 'test01'
order by POSITION;	