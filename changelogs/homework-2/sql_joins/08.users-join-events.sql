explain analyse
select *
from users u
         left outer join users_events ue
                         on u.Id = ue.user_id
         left outer join events e
                         on e.id = ue.event_id