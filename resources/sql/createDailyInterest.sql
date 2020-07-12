insert into pawn.tbldailyinterest (branchid,comid,tktid,date,intamount,lastupdate,lastupuserid)
select t.branchid,t.comid, t.ticketid,t.condate,

round((select d.dueamount from pawn.tblduefrom d where t.ticketid = d.ticketid and duetypeid = 2) -
(select sum(i.intamount) from pawn.tbldailyinterest i where t.ticketid = i.tktid),2),
t.condate,1 from pawn.tblticket t where t.stsid = 1