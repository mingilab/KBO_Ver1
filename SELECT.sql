-- ������ �˻�
select sequence_name from user_sequences;
select * from PITCHER_STORAGE

insert into ACCOUNT(USERID,USERPW) values('test1','1234');
insert into HITTER_STORAGE(USERID,PLAYERID,HITTERSEQ) values('test1',164,HITTER_STORAGE_SEQUNCE.nextval)
insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values('test1',506,'st1',PITCHER_STORAGE_SEQUNCE.nextval)

select USERID,HITTERSEQ,PLAYERID,HITTERLV,HITTEREXP,POSITION,BATTINGORDER,
       NAME,GRADE,AVG,OPS
       from HITTER_STORAGE
       join HITTER_INFORMATION using(PLAYERID)
       where USERID = 'test03'
       --and POSITION = 'D'
       order by USERID, POSITION
