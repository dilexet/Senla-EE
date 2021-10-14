-- task 1
select count(*) from cd.facilities
-- task 2
select count(*) from cd.facilities
where guestcost >= 10
-- task 3
select recommendedby, count(recommendedby) from cd.members
where recommendedby is not null
group by recommendedby
order by recommendedby
-- task 4
select facid, sum(slots)
from cd.bookings
group by facid
order by facid
-- task 5
select facid, sum(slots)
from cd.bookings
where starttime >= '2012-09-01' and starttime < '2012-10-01'
group by facid
order by sum(slots)
-- task 6
select facid, extract(month from starttime) as month, sum(slots)
from cd.bookings
where starttime >= '2012-01-01' and starttime < '2012-12-31'
group by facid, month
order by facid, month
-- task 7
select count(distinct memid) from cd.bookings
-- task 8
select facid, sum(slots) from cd.bookings
group by facid
having sum(slots)>1000
order by facid
-- task 9
select f.name,
sum(slots*
	case
		when memid = 0 then f.guestcost
		else f.membercost
	end) as revenue
from cd.bookings b
join cd.facilities f
on f.facid=b.facid
group by f.name
order by revenue
-- task 10
select f.name,
sum(slots*
	case
		when memid = 0 then f.guestcost
		else f.membercost
	end) as revenue
from cd.bookings b
join cd.facilities f
on f.facid=b.facid
group by f.name
having sum(slots*
	case
		when memid = 0 then f.guestcost
		else f.membercost
	end) < 1000
order by revenue
-- task 11
select facid, sum(slots) from cd.bookings
group by facid
order by sum(slots) desc
limit 1
-- task 12
select facid, extract(month from starttime) as month, sum(slots)
from cd.bookings
where
		starttime >= '2012-01-01'
		and starttime < '2012-12-31'
group by rollup(facid, month)
order by facid, month
-- task 13
select f.facid, f.name, round(sum(b.slots)/2.0, 2) as total
	from cd.bookings b
	join cd.facilities f
		on f.facid = b.facid
	group by f.facid, f.name
order by f.facid;
-- task 14
select
	m.surname, m.firstname,
	m.memid, min(b.starttime)
from cd.members m
join cd.bookings b
on m.memid = b.memid
where b.starttime>'2012-09-01'
group by m.surname, m.firstname, m.memid
order by m.memid
-- task 15
select (select count(*) from cd.members) as count, firstname, surname
	from cd.members
order by joindate
-- task 16
select count(*) over(order by joindate), firstname, surname
	from cd.members
order by joindate
-- task 17
select facid, total from (
	select facid, total, rank() over (order by total desc) rank from (
		select facid, sum(slots) total from cd.bookings
		group by facid) as sumslots) as ranked
where rank = 1
-- task 18
select firstname, surname,
	((sum(b.slots)+10)/20)*10 as hours,
	rank() over (order by ((sum(b.slots)+10)/20)*10 desc) as rank
	from cd.bookings b
	join cd.members m
	on b.memid = m.memid
group by m.memid
order by rank, surname, firstname;
-- task 19
select name, rank from (
	select f.name as name, rank() over (order by sum(
		case
		  when memid = 0 then slots * f.guestcost
		  else slots * membercost
		end) desc) as rank
	from cd.bookings b
	join cd.facilities f
	on b.facid = f.facid
group by f.name) as subq
where rank <= 3
order by rank;
-- task 20
select name,
	case when classification=1 then 'high'
		when classification=2 then 'average'
		else 'low'
	end revenue
from (
  select f.name as name, ntile(3) over (order by sum(
			case
				when memid = 0 then slots * f.guestcost
				else slots * membercost
			end) desc) as classification
  from cd.bookings b
	join cd.facilities f
	on b.facid = f.facid
	group by f.name
) as subq
order by classification, name;
-- task 21
select 	f.name as name,
	f.initialoutlay/((sum(
		case
			when memid = 0 then slots * f.guestcost
			else slots * membercost
		end)/3) - f.monthlymaintenance) as months
from cd.bookings b
join cd.facilities f
on b.facid = f.facid
group by f.facid
order by name;
-- task 22
select 	dategen.date,
	(select sum(
		case
		  when memid = 0 then slots * f.guestcost
		  else slots * membercost
		end) as rev
	from cd.bookings b
	join cd.facilities f
	on b.facid = f.facid
	where b.starttime > dategen.date - interval '14 days'
			and b.starttime < dategen.date + interval '1 day'
	)/15 as revenue
	from (
		select 	cast(generate_series(timestamp '2012-08-01', '2012-08-31','1 day') as date) as date
	)  as dategen
order by dategen.date;
