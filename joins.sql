-- task 1
select
  b.starttime
from
  cd.bookings b
join
  cd.members m
on
	m.memid=b.memid
where
	m.firstname='David' and m.surname='Farrell';

-- task 2
select
  b.starttime as start, f.name
from
  cd.bookings b
join
  cd.facilities f
on
  f.facid=b.facid
where
	b.starttime >= '2012-09-21'
and
	b.starttime < '2012-09-22'
and
	f.name like '%Tennis Court%'
order by
  b.starttime;

-- task 3
select distinct
  mr.firstname, mr.surname
from
  cd.members m
join
  cd.members mr
on
  	mr.memid=m.recommendedby
order by
    surname, firstname;

-- task 4
select
  m.firstname as memfname, m.surname as memsname,
  r.firstname as recfname, r.surname as recsname
from
  cd.members m
left outer join
  cd.members r
on
  	r.memid=m.recommendedby
order by
    memsname, memfname;

-- task 5
select distinct
	m.firstname||' '||m.surname as member,
	f.name as facility
from
	cd.members m
inner join
	cd.bookings b
on
	m.memid=b.memid
join
	cd.facilities f
on
	b.facid=f.facid
where
	f.name like '%Tennis Court%'
order by member, facility;

-- task 6
select m.firstname || ' ' || m.surname as member,
	f.name as facility,
case
  when m.memid = 0 then
    b.slots*f.guestcost
  else
    b.slots*f.membercost
end as cost

from
  cd.members as m
inner join
  cd.bookings as b
on
  m.memid = b.memid
join
  cd.facilities f
on
  b.facid=f.facid
where
  b.starttime >= '2012-09-14' and
  b.starttime < '2012-09-15' and
  ((m.memid = 0 and b.slots*f.guestcost > 30) or (m.memid != 0 and b.slots*f.membercost > 30))
order by cost desc

-- task 7
select distinct m.firstname||' '||m.surname as member,
	(select r.firstname||' '||r.surname as recommender
		from cd.members r
			where r.memid=m.recommendedby)
from cd.members m
order by member

-- task 8
select member, facility, cost from (
	select m.firstname || ' ' || m.surname as member, f.name as facility,
		case
			when m.memid = 0 then
				b.slots*f.guestcost
			else
				b.slots*f.membercost
		end as cost
		from cd.members m
			join cd.bookings b on m.memid = b.memid
			join cd.facilities f on b.facid = f.facid
		where	b.starttime >= '2012-09-14' and b.starttime < '2012-09-15'
	) as bookings
	where cost > 30
order by cost desc;
